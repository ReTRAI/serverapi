package com.mob.serverapi.support.repositories.endpoints;

import com.mob.serverapi.servicefault.FaultMapping;
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
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getSupportById.label, ex.getMessage()));
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
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getSupportByUserId.label, ex.getMessage()));
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

                tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(tUserType.UserTypeEnum.SUPPORT.name());
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

                    if (saved != null) support = SupportUtils.transformSupport(saved);
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userAlreadyHasRole.label, ""));
                }

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.setSupport.label, ex.getMessage()));
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

                    if (!supportAssociationRepository.existInSupportAssociation(supportId, supportId)) {

                        tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(tUserType.UserTypeEnum.SUPPORT.name());
                        tUser associatedUser = userRepository.findById(supportValidation.getUser().getUserId());

                        long nRole = userRoleRepository.countByUserIdAndUserTypeId(supportValidation.getUser().getUserId(), userTypeVal.getUserTypeId());

                        if (nRole > 0) {
                            tUserRole role = userRoleRepository.findByUserIdAndUserTypeId(supportValidation.getUser().getUserId(), userTypeVal.getUserTypeId());

                            userRoleRepository.deleteUserRoleById(role.getUserRoleId());
                            supportLogRepository.deleteSupportLogBySupportId(supportValidation.getSupportId());
                            supportRepository.deleteSupportById(supportValidation.getSupportId());

                            userLogRepository.insertUserLog(actionUser, associatedUser, "REMOVE_SUPPORT_ROLE", "");

                            val = true;

                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.roleNotExist.label, ""));
                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.hasAssoc.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.removeSupport.label, ex.getMessage()));
        }

        return val;
    }


    @Override
    public List<Support> getSupportFiltered(@Nullable String supportId, @Nullable String supportName, boolean onlyChildren, @Nullable String field, @Nullable String orderField, int offset, int numberRecords) {

        List<Support> returnList = new ArrayList<>();

        try {


            UUID localSupportId = supportId.equals("") ? null : UUID.fromString(supportId);
            String localSupportName = supportName.equals("") ? null : supportName;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;


            List<tSupport> support = supportRepository.getSupportFiltered(localSupportId, localSupportName,
                    onlyChildren, localField, localOrderField, offset, numberRecords);

            if (support != null)
                returnList = SupportUtils.transformSupportList(support);


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getSupportFiltered.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountSupportFiltered(@Nullable String supportId, @Nullable String supportName, boolean onlyChildren) {

        try {


            UUID localSupportId = supportId.equals("") ? null : UUID.fromString(supportId);
            String localSupportName = supportName.equals("") ? null : supportName;


            long countSupport = supportRepository.getCountSupportFiltered(localSupportId, localSupportName, onlyChildren);

            return countSupport;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getCountSupportFiltered.label, ex.getMessage()));
        }
    }


    @Override
    public List<Ticket> getTicketFiltered(@Nullable String ticketId, @Nullable String status,
                                          @Nullable String startCreationDate, @Nullable String endCreationDate,
                                          @Nullable String openUserId, @Nullable String assignedUserId,
                                          @Nullable String field, @Nullable String orderField, int offset, int numberRecords) {

        List<Ticket> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            String localStatus = status.equals("") ? null : status;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null : LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null : LocalDateTime.parse(endCreationDate, formatter).plusDays(1);
            UUID localOpenUserId = openUserId.equals("") ? null : UUID.fromString(openUserId);
            UUID localAssignedUserId = assignedUserId.equals("") ? null : UUID.fromString(assignedUserId);

            List<tTicket> ticket = ticketRepository.getTicketFiltered(localTicketId, localStatus,
                    localStartCreationDate, localEndCreationDate, localOpenUserId, localAssignedUserId, localField,
                    localOrderField, offset, numberRecords);

            if (ticket != null)
                returnList = SupportUtils.transformTicketList(ticket);


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getTicketFiltered.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountTicketFiltered(@Nullable String ticketId, @Nullable String status, @Nullable String startCreationDate,
                                       @Nullable String endCreationDate,
                                       @Nullable String openUserId, @Nullable String assignedUserId) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            String localStatus = status.equals("") ? null : status;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null : LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null : LocalDateTime.parse(endCreationDate, formatter).plusDays(1);
            UUID localOpenUserId = openUserId.equals("") ? null : UUID.fromString(openUserId);
            UUID localAssignedUserId = assignedUserId.equals("") ? null : UUID.fromString(assignedUserId);

            long countSupport = ticketRepository.getCountTicketFiltered(localTicketId, localStatus,
                    localStartCreationDate, localEndCreationDate, localOpenUserId, localAssignedUserId);

            return countSupport;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getCountTicketFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public List<TicketDetail> getTicketDetailFiltered(@Nullable String ticketId, @Nullable String startCreationDate,
                                                      @Nullable String endCreationDate, @Nullable String responseUserId,
                                                      @Nullable String field,
                                                      @Nullable String orderField, int offset, int numberRecords) {
        List<TicketDetail> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null : LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null : LocalDateTime.parse(endCreationDate, formatter).plusDays(1);
            UUID localResponseUserId = responseUserId.equals("") ? null : UUID.fromString(responseUserId);


            List<tTicketDetail> ticket = ticketDetailRepository.getTicketDetailFiltered(localTicketId,
                    localStartCreationDate, localEndCreationDate, localResponseUserId,
                    localField, localOrderField, offset, numberRecords);

            if (ticket != null)
                returnList = SupportUtils.transformTicketDetailList(ticket);


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getTicketDetailFiltered.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountTicketDetailFiltered(@Nullable String ticketId, @Nullable String startCreationDate,
                                             @Nullable String endCreationDate, @Nullable String responseUserId) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localTicketId = ticketId.equals("") ? null : UUID.fromString(ticketId);
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null : LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null : LocalDateTime.parse(endCreationDate, formatter).plusDays(1);
            UUID localResponseUserId = responseUserId.equals("") ? null : UUID.fromString(responseUserId);


            long countSupport = ticketDetailRepository.getCountTicketDetailFiltered(localTicketId,
                    localStartCreationDate, localEndCreationDate, localResponseUserId);

            return countSupport;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getCountTicketDetailFiltered.label, ex.getMessage()));
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

                        if (!supportRepository.isCircularAssociation(parentSupportId, childSupportId)) {

                            tSupportAssociation assoc = new tSupportAssociation();
                            assoc.setParentSupport(parent);
                            assoc.setChildSupport(child);

                            tSupportAssociation saved = supportAssociationRepository.saveSupportAssociation(assoc);

                            supportLogRepository.insertSupportLog(actionUser, parent, "ADD_SUPPORT_ASSOCIATION", "ADD CHILD ID: " + child.getSupportId());
                            supportLogRepository.insertSupportLog(actionUser, child, "ADD_SUPPORT_ASSOCIATION", "ADD PARENT ID: " + parent.getSupportId());
                            supportAssociationLogRepository.insertSupportAssociationLog(actionUser, saved, "ADD_SUPPORT_ASSOCIATION", "ADD CHILD ID: " + child.getSupportId() + " TO PARENT ID: " + parent.getSupportId());

                            val = true;

                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.circularAssoc.label, ""));
                        }

                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.childHasParent.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.setSupportAssoc.label, ex.getMessage()));
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
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.assocNotExist.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.removeSupportAssoc.label, ex.getMessage()));
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
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.assocNotExist.label, ""));
                }


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getSupportAssoc.label, ex.getMessage()));
        }
        return assoc;
    }

    @Override
    public Support getSupportParentByChildId(UUID childSupportId) {
        Support assoc = new Support();

        try {

            tSupport child = supportRepository.findById(childSupportId);

            if (child != null) {

                tSupportAssociation saved = supportAssociationRepository.getAssociationByChildId(childSupportId);


                if (saved != null) {
                    tSupport parent = supportRepository.findById(saved.getParentSupport().getSupportId());
                    assoc = SupportUtils.transformSupport(parent);

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.assocNotExist.label, ""));
                }

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getSupportParentByChildId.label, ex.getMessage()));
        }
        return assoc;
    }

    @Override
    public Ticket setTicket(String title, String message, String attachPath, UUID creationUserId) {
        Ticket ticket = new Ticket();

        try {
            tUser creationUser = userRepository.findById(creationUserId);
            if (creationUser != null) {

                tTicketStatus ticketStatusVal = ticketStatusRepository.findTicketStatusByDescription(tTicketStatus.TicketStatusEnum.OPEN.name());

                tTicket ticketToSave = new tTicket();
                ticketToSave.setTitle(title);
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

                    if (attachPath != null)
                        tickedDetailToSave.setAttachPath(attachPath);

                    ticketDetailRepository.saveTicketDetail(tickedDetailToSave);
                    ticketLogRepository.insertTicketLog(creationUser, saved, "TICKET CREATED", "TICKET ID: " + saved.getTicketId());
                }

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.setTicket.label, ex.getMessage()));

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

                    if (localAssignedUserId != null) {

                        tUser assignedUser = userRepository.findById(localAssignedUserId);
                        if (assignedUser != null) {

                            if (ticket.getOpenUser().getUserId().equals(assignedUser.getUserId())) {

                                ticket.setAssignedUser(ticket.getOpenUser());

                            } else {
                                if (assignedUser.getUserStatus().getUserStatusId().equals
                                        (ticket.getOpenUser().getUserStatus().getUserStatusId())) {

                                    assignedUser.setUserStatus(ticket.getOpenUser().getUserStatus());
                                }
                                ticket.setAssignedUser(assignedUser);
                            }

                            ticketRepository.saveTicket(ticket);
                            ticketLogRepository.insertTicketLog(actionUser, ticket, "TICKET ALTERED", "ALTERED ASSIGNED USER TO : " + assignedUserId);
                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
                        }
                    }

                    if (!status.equals("")) {

                        tTicketStatus localStatus = ticketStatusRepository.findTicketStatusByDescription(status);

                        if (localStatus != null) {
                            ticket.setTicketStatus(localStatus);
                            ticketRepository.saveTicket(ticket);
                            ticketLogRepository.insertTicketLog(actionUser, ticket, "TICKET ALTERED", "ALTERED STATUS TO : " + localStatus.getDescription());
                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.statusNotExist.label, ""));

                        }
                    }


                    val = true;

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.ticketNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.updateTicket.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean setTicketDetail(UUID ticketId, String message, String attachPath, UUID actionUserId) {
        boolean val = false;

        try {

            tTicket ticket = ticketRepository.findById(ticketId);

            if (ticket != null) {
                tUser actionUser = userRepository.findById(actionUserId);

                if (actionUser != null) {

                    tTicketDetail tickedDetailToSave = new tTicketDetail();
                    tickedDetailToSave.setTicket(ticket);
                    tickedDetailToSave.setDetail(message);
                    tickedDetailToSave.setDetailDate(LocalDateTime.now());
                    tickedDetailToSave.setUser(actionUser);
                    tickedDetailToSave.setOriginalMessage(false);

                    if (attachPath != null)
                        tickedDetailToSave.setAttachPath(attachPath);

                    ticketDetailRepository.saveTicketDetail(tickedDetailToSave);
                    ticketLogRepository.insertTicketLog(actionUser, ticket, "ADD TICKET DETAIL", "TICKET ID: " + ticket.getTicketId());

                    val = true;
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.ticketNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.setTicketDetail.label, ex.getMessage()));

        }

        return val;
    }

    @Override
    public List<Support> getAvailableSupportParent(UUID supportId, int offset, int numberRecords) {

        List<Support> parentsList = new ArrayList<>();

        try {

            tSupport support = supportRepository.findById(supportId);

            if (support != null) {


                List<UUID> childrenIds = getAllIdsInHierachy(supportId);

                List<tSupport> available = supportRepository.findBySupportIdNotIn(childrenIds);

                if (available != null)
                    parentsList = SupportUtils.transformSupportList(available);


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getAvailableSupportTicket.label, ex.getMessage()));
        }
        return parentsList;
    }

    @Override
    public long getCountAvailableSupportParent(UUID supportId) {

        long size = 0;

        try {

            tSupport support = supportRepository.findById(supportId);

            if (support != null) {

                List<UUID> childrenIds = getAllIdsInHierachy(supportId);

                List<tSupport> available = supportRepository.findBySupportIdNotIn(childrenIds);

                if (available != null) size = available.size();


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.getCountAvailableSupportTicket.label, ex.getMessage()));
        }

        return size;
    }

    @Override
    public boolean isHierarchyValid(UUID supportId, UUID childSupportId) {

        boolean val = false;
        try {

            tSupport support = supportRepository.findById(supportId);
            tSupport child = supportRepository.findById(childSupportId);

            if (support != null && child != null) {

                List<UUID> childrenIds = getAllIdsInHierachy(supportId);

                if (childrenIds.contains(childSupportId))
                    val = true;

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.supportNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.SupportGeneralRepoFault.isHierarchyValid.label, ex.getMessage()));
        }
        return val;
    }

    public List<UUID> getAllIdsInHierachy(UUID supportId) {

        List<tSupport> allChildren = supportRepository.getAllLevelChildrenByParentId(supportId);
        List<UUID> supportIds = new ArrayList<>();
        supportIds.add(supportId);

        if (allChildren != null) allChildren.forEach(r -> supportIds.add(r.getSupportId()));

        return supportIds;
    }
}

