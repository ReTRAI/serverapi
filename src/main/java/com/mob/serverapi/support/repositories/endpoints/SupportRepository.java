package com.mob.serverapi.support.repositories.endpoints;

import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.support.base.Support;
import com.mob.serverapi.support.base.SupportAssociation;
import com.mob.serverapi.support.base.Ticket;
import com.mob.serverapi.support.base.TicketDetail;
import com.mob.serverapi.support.database.*;
import com.mob.serverapi.support.repositories.database.*;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserRole;
import com.mob.serverapi.users.database.tUserType;
import com.mob.serverapi.users.repositories.database.tUserLogRepository;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.users.repositories.database.tUserRoleRepository;
import com.mob.serverapi.users.repositories.database.tUserTypeRepository;
import com.mob.serverapi.utils.SupportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class SupportRepository implements ISupportRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tSupportRepository supportRepository = new tSupportRepository();
    @Autowired
    protected tUserRepository userRepository = new tUserRepository();
    @Autowired
    protected tUserTypeRepository userTypeRepository = new tUserTypeRepository();
    @Autowired
    protected tUserRoleRepository userRoleRepository = new tUserRoleRepository();
    @Autowired
    protected tUserLogRepository userLogRepository = new tUserLogRepository();
    @Autowired
    protected tSupportLogRepository supportLogRepository = new tSupportLogRepository();
    @Autowired
    protected tTicketRepository ticketRepository = new tTicketRepository();
    @Autowired
    protected tSupportAssociationRepository supportAssociationRepository = new tSupportAssociationRepository();
    @Autowired
    protected tSupportAssociationLogRepository supportAssociationLogRepository = new tSupportAssociationLogRepository();
    @Autowired
    protected tTicketStatusRepository ticketStatusRepository = new tTicketStatusRepository();
    @Autowired
    protected tTicketDetailRepository ticketDetailRepository = new tTicketDetailRepository();
    @Autowired
    protected tTicketLogRepository ticketLogRepository = new tTicketLogRepository();

    @Override
    public Support getSupportById(UUID supportId) {

        Support supportToReturn = new Support();

        try {
            tSupport u = supportRepository.findById(supportId);

            if (u != null) {
                supportToReturn = SupportUtils.transformSupport(u);
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_SUPPORT_BY_ID", ex.getMessage()));
        }

        return supportToReturn;
    }

    @Override
    public Support getSupportByUserId(UUID userId) {

        Support supportToReturn = new Support();

        try {
            tSupport u = supportRepository.findByUserId(userId);

            if (u != null) {
                supportToReturn = SupportUtils.transformSupport(u);
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_SUPPORT_BY_USER_ID", ex.getMessage()));
        }

        return supportToReturn;
    }

    @Override
    public Support setSupport(UUID userId, UUID actionUserId) {

        Support support = new Support();

        try {
            tUser associatedUser = userRepository.findById(userId);
            tUser actionUser = userRepository.findById(actionUserId);

            if (associatedUser != null && actionUser != null) {

                tUserType userTypeVal = userTypeRepository.
                        findUserTypeByDescription(tUserType.UserTypeEnum.SUPPORT.name());
                long nRole = userRoleRepository.countByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());

                if (nRole == 0) {
                    tSupport supportToCreate = new tSupport();
                    supportToCreate.setUser(associatedUser);
                    supportToCreate.setCreationDate(LocalDateTime.now());

                    tSupport saved = supportRepository.saveSupport(supportToCreate);

                    if (saved != null) {

                        tUserRole role = new tUserRole();
                        role.setUser(associatedUser);
                        role.setUserType(userTypeVal);

                        userRoleRepository.saveUserRole(role);
                        userLogRepository.insertUserLog(actionUser, associatedUser, "ADD_SUPPORT_ROLE", "SUPPORT_ID:" + saved.getSupportId());
                        supportLogRepository.insertSupportLog(actionUser, saved, "ADD_SUPPORT", "");
                    }

                    if (saved != null)
                        support = SupportUtils.transformSupport(saved);
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("USER_IS_ALREADY_SUPPORT", ""));
                }

            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_SUPPORT", ex.getMessage()));
        }

        return support;
    }

    @Override
    public boolean removeSupport(UUID supportId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                tSupport supportValidation = supportRepository.findById(supportId);

                if (supportValidation != null) {

                    tUserType userTypeVal = userTypeRepository.
                            findUserTypeByDescription(tUserType.UserTypeEnum.SUPPORT.name());
                    tUser associatedUser = userRepository.findById(supportValidation.getUser().getUserId());
                    tUserRole role = userRoleRepository.findByUserIdAndUserTypeId(supportValidation.getUser().getUserId(), userTypeVal.getUserTypeId());


                    userRoleRepository.deleteUserRoleById(role.getUserRoleId());
                    supportLogRepository.deleteSupportLogBySupportId(supportValidation.getSupportId());
                    supportRepository.deleteSupportById(supportValidation.getSupportId());

                    userLogRepository.insertUserLog(actionUser, associatedUser, "REMOVE_SUPPORT_ROLE", "");

                    val = true;


                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXIST", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("REMOVE_SUPPORT", ex.getMessage()));
        }

        return val;
    }


    @Override
    public List<Support> getSupportFiltered(@Nullable String supportId, @Nullable String supportName,
                                            boolean onlyChildren, @Nullable String field,
                                            @Nullable String orderField, int offset, int numberRecords) {

        List<Support> returnList = new ArrayList<>();

        try {


            UUID localSupportId = supportId.equals("") ? null : UUID.fromString(supportId);
            String localSupportName = supportName.equals("") ? null : supportName;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;


            List<tSupport> support = supportRepository.getSupportFiltered(localSupportId, localSupportName,
                    onlyChildren, localField, localOrderField, offset, numberRecords);

            if (support != null) {
                returnList = SupportUtils.transformSupportList(support);

            } else {
                throw new ServiceFaultException("WARNING", new ServiceFault("EMPTY_SUPPORT_LIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_SUPPORT_FILTERED", ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountSupportFiltered(@Nullable String supportId, @Nullable String supportName,
                                        boolean onlyChildren) {

        try {


            UUID localSupportId = supportId.equals("") ? null : UUID.fromString(supportId);
            String localSupportName = supportName.equals("") ? null : supportName;


            long countSupport = supportRepository.getCountSupportFiltered(localSupportId, localSupportName,
                    onlyChildren);

            return countSupport;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_COUNT_SUPPORT_FILTERED", ex.getMessage()));
        }
    }


    @Override
    public List<Ticket> getTicketFiltered(@Nullable String ticketId, @Nullable String status,
                                          @Nullable String startCreationDate, @Nullable String endCreationDate,
                                          @Nullable String field, @Nullable String orderField,
                                          int offset, int numberRecords) {

        List<Ticket> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            String localStatus = status.equals("") ? null : status;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter).plusDays(1);

            List<tTicket> ticket = ticketRepository.getTicketFiltered(localTicketId, localStatus,
                    localStartCreationDate, localEndCreationDate,
                    localField, localOrderField, offset, numberRecords);

            if (ticket != null) {
                returnList = SupportUtils.transformTicketList(ticket);

            } else {
                throw new ServiceFaultException("WARNING", new ServiceFault("EMPTY_TICKET_LIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_TICKET_FILTERED", ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountTicketFiltered(@Nullable String ticketId, @Nullable String status,
                                       @Nullable String startCreationDate, @Nullable String endCreationDate) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            String localStatus = status.equals("") ? null : status;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter).plusDays(1);


            long countSupport = ticketRepository.getCountTicketFiltered(localTicketId, localStatus,
                    localStartCreationDate, localEndCreationDate);

            return countSupport;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_COUNT_TICKET_FILTERED", ex.getMessage()));
        }
    }

    @Override
    public List<TicketDetail> getTicketDetailFiltered(@Nullable String ticketId, @Nullable String startCreationDate, @Nullable String endCreationDate,
                                                      @Nullable String field, @Nullable String orderField,
                                                      int offset, int numberRecords) {
        List<TicketDetail> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter).plusDays(1);

            List<tTicketDetail> ticket = ticketDetailRepository.getTicketDetailFiltered(localTicketId,
                    localStartCreationDate, localEndCreationDate,
                    localField, localOrderField, offset, numberRecords);

            if (ticket != null) {
                returnList = SupportUtils.transformTicketDetailList(ticket);

            } else {
                throw new ServiceFaultException("WARNING", new ServiceFault("EMPTY_TICKET_DETAIL_LIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_TICKET_DETAIL_FILTERED", ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountTicketDetailFiltered(@Nullable String ticketId, @Nullable String startCreationDate, @Nullable String endCreationDate) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter).plusDays(1);


            long countSupport = ticketDetailRepository.getCountTicketDetailFiltered(localTicketId,
                    localStartCreationDate, localEndCreationDate);

            return countSupport;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_COUNT_TICKET_DETAIL_FILTERED", ex.getMessage()));
        }
    }

    @Override
    public boolean setSupportAssociation(UUID parentSupportId, UUID childSupportId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                tSupport parent = supportRepository.findById(parentSupportId);
                tSupport child = supportRepository.findById(childSupportId);

                if (parent != null && child != null) {
                    long exists = supportAssociationRepository.countAssociationByChildSupportId(childSupportId);

                    if (exists == 0) {


                        tSupportAssociation assoc = new tSupportAssociation();
                        assoc.setParentSupport(parent);
                        assoc.setChildSupport(child);

                        tSupportAssociation saved = supportAssociationRepository.saveSupportAssociation(assoc);

                        supportLogRepository.insertSupportLog(actionUser, parent, "ADD_SUPPORT_ASSOCIATION", "ADD CHILD ID: " + child.getSupportId());
                        supportLogRepository.insertSupportLog(actionUser, child, "ADD_SUPPORT_ASSOCIATION", "ADD PARENT ID: " + parent.getSupportId());
                        supportAssociationLogRepository.insertSupportAssociationLog(actionUser, saved, "ADD_SUPPORT_ASSOCIATION",
                                "ADD CHILD ID: " + child.getSupportId() + " TO PARENT ID: " + parent.getSupportId());

                        val = true;

                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("CHILD_SUPPORT_ALREADY_ASSOCIATED", ""));
                    }
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXISTS", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_SUPPORT_ASSOCIATION", ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean removeSupportAssociation(UUID parentSupportId, UUID childSupportId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                tSupport parent = supportRepository.findById(parentSupportId);
                tSupport child = supportRepository.findById(childSupportId);

                if (parent != null && child != null) {
                    tSupportAssociation assoc = supportAssociationRepository.getAssociation(parentSupportId, childSupportId);

                    if (assoc != null) {

                        supportAssociationLogRepository.deleteSupportAssociationLogBySupportAssociationId(assoc.getSupportAssociationId());
                        supportAssociationRepository.deleteById(assoc.getSupportAssociationId());

                        supportLogRepository.insertSupportLog(actionUser, parent, "REMOVE_SUPPORT_ASSOCIATION", "ADD CHILD ID: " + child.getSupportId());
                        supportLogRepository.insertSupportLog(actionUser, child, "REMOVE_SUPPORT_ASSOCIATION", "ADD PARENT ID: " + parent.getSupportId());

                        val = true;

                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("ASSOCIATION_DONT_EXIST", ""));
                    }
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXISTS", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_SUPPORT_ASSOCIATION", ex.getMessage()));
        }
        return val;
    }

    @Override
    public SupportAssociation getSupportAssociation(UUID parentSupportId, UUID childSupportId) {
        SupportAssociation assoc = new SupportAssociation();

        try {

            tSupport parent = supportRepository.findById(parentSupportId);
            tSupport child = supportRepository.findById(childSupportId);

            if (parent != null && child != null) {

                tSupportAssociation saved = supportAssociationRepository.getAssociation(parentSupportId, childSupportId);

                if (saved != null) {

                    assoc = SupportUtils.transformSupportAssociation(saved);
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("ASSOCIATION_DONT_EXISTS", ""));
                }


            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("SUPPORT_DONT_EXISTS", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_SUPPORT_ASSOCIATION", ex.getMessage()));
        }
        return assoc;
    }

    @Override
    public Ticket setTicket(String message, UUID creationUserId) {
        Ticket ticket = new Ticket();

        try {
            tUser creationUser = userRepository.findById(creationUserId);
            if (creationUser != null) {

                tTicketStatus ticketStatusVal = ticketStatusRepository
                        .findTicketStatusByDescription(tTicketStatus.TicketStatusEnum.OPEN.name());

                tTicket ticketToSave = new tTicket();
                ticketToSave.setOpenUser(creationUser);
                ticketToSave.setOpenDate(LocalDateTime.now());
                ticketToSave.setTicketStatus(ticketStatusVal);

                tTicket saved = ticketRepository.saveTicket(ticketToSave);

                if (saved != null) {

                    ticket = SupportUtils.transformTicket(saved);


                    tTicketDetail tickedDetailToSave = new tTicketDetail();
                    tickedDetailToSave.setTicket(saved);
                    tickedDetailToSave.setDetail(message);
                    tickedDetailToSave.setDetailDate(LocalDateTime.now());
                    tickedDetailToSave.setUser(creationUser);
                    tickedDetailToSave.setOriginalMessage(true);

                    ticketDetailRepository.saveTicketDetail(tickedDetailToSave);
                    ticketLogRepository.insertTicketLog(creationUser, saved, "TICKET CREATED", "TICKET ID: " + saved.getTicketId());
                }

            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_TICKET", ex.getMessage()));

        }

        return ticket;
    }

    @Override
    public boolean updateTicket(UUID ticketId, @Nullable String status, @Nullable String assignedUserId, UUID actionUserId) {
        boolean val = false;
        try {

            tTicket ticket = ticketRepository.findById(ticketId);

            if (ticket != null) {
                tUser actionUser = userRepository.findById(actionUserId);

                if (actionUser != null) {

                    UUID localAssignedUserId = assignedUserId.equals("") ? null : UUID.fromString(assignedUserId);
                    tTicketStatus localStatus = status.equals("") ? null : ticketStatusRepository
                            .findTicketStatusByDescription(status);

                    if (localAssignedUserId != null) {

                        tUser assignedUser = userRepository.findById(localAssignedUserId);

                        if (assignedUser != null) {
                            ticket.setAssignedUser(assignedUser);
                            ticketRepository.saveTicket(ticket);
                            ticketLogRepository.insertTicketLog(actionUser, ticket, "TICKET ALTERED",
                                    "ALTERED ASSIGNED USER TO : " + localStatus.getDescription());
                        } else {
                            throw new ServiceFaultException("ERROR", new ServiceFault("ASSIGNED_USER_DONT_EXIST", ""));
                        }
                    }
                    if (localStatus != null) {

                        ticket.setTicketStatus(localStatus);
                        ticketRepository.saveTicket(ticket);
                        ticketLogRepository.insertTicketLog(actionUser, ticket, "TICKET ALTERED",
                                "ALTERED STATUS TO : " + localStatus.getDescription());

                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("STATUS_DONT_EXIST", ""));
                    }

                    val = true;

                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("TICKET_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("UPDATE_TICKET_ASSOCIATION", ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean setTicketDetail(UUID ticketId, String message, UUID actionUserId) {
        boolean val = false;

        try {

            tTicket ticket = ticketRepository.findById(ticketId);

            if(ticket != null) {
                tUser actionUser = userRepository.findById(actionUserId);

                if (actionUser != null) {

                    tTicketDetail tickedDetailToSave = new tTicketDetail();
                    tickedDetailToSave.setTicket(ticket);
                    tickedDetailToSave.setDetail(message);
                    tickedDetailToSave.setDetailDate(LocalDateTime.now());
                    tickedDetailToSave.setUser(actionUser);
                    tickedDetailToSave.setOriginalMessage(false);

                    ticketDetailRepository.saveTicketDetail(tickedDetailToSave);
                    ticketLogRepository.insertTicketLog(actionUser, ticket, "ADD TICKET DETAIL", "TICKET ID: " + ticket.getTicketId());

                    val= true;
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
                }
            }else {
                throw new ServiceFaultException("ERROR", new ServiceFault("TICKET_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_TICKET_DETAIL", ex.getMessage()));

        }

        return val;
    }
}

