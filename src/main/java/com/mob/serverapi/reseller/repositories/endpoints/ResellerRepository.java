package com.mob.serverapi.reseller.repositories.endpoints;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceUser;
import com.mob.serverapi.device.repositories.database.tDeviceRepository;
import com.mob.serverapi.device.repositories.database.tDeviceUserRepository;
import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.base.ResellerAssociation;
import com.mob.serverapi.reseller.base.ResellerBalance;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerBalance;
import com.mob.serverapi.reseller.database.tResellerMovementType;
import com.mob.serverapi.reseller.repositories.database.*;
import com.mob.serverapi.servicefault.FaultMapping;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserRole;
import com.mob.serverapi.users.database.tUserStatus;
import com.mob.serverapi.users.database.tUserType;
import com.mob.serverapi.users.repositories.database.tUserLogRepository;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.users.repositories.database.tUserRoleRepository;
import com.mob.serverapi.users.repositories.database.tUserTypeRepository;
import com.mob.serverapi.utils.ResellerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class ResellerRepository implements IResellerRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tResellerRepository resellerRepository = new tResellerRepository();
    @Autowired
    protected tResellerBalanceRepository resellerBalanceRepository = new tResellerBalanceRepository();
    @Autowired
    protected tResellerLogRepository resellerLogRepository = new tResellerLogRepository();
    @Autowired
    protected tResellerAssociationRepository resellerAssociationRepository = new tResellerAssociationRepository();
    @Autowired
    protected tResellerAssociationLogRepository resellerAssociationLogRepository = new tResellerAssociationLogRepository();
    @Autowired
    protected tUserRepository userRepository = new tUserRepository();
    @Autowired
    protected tUserRoleRepository userRoleRepository = new tUserRoleRepository();
    @Autowired
    protected tUserTypeRepository userTypeRepository = new tUserTypeRepository();
    @Autowired
    protected tUserLogRepository userLogRepository = new tUserLogRepository();
    @Autowired
    protected tDeviceRepository deviceRepository = new tDeviceRepository();
    @Autowired
    protected tDeviceUserRepository deviceUserRepository = new tDeviceUserRepository();
    @Autowired
    protected tResellerMovementTypeRepository resellerMovementTypeRepository = new tResellerMovementTypeRepository();


    @Override
    public Reseller getResellerById(UUID resellerId) {

        Reseller resellerToReturn = new Reseller();

        try {
            tReseller u = resellerRepository.findById(resellerId);

            if (u != null) {
                resellerToReturn = ResellerUtils.transformReseller(u);
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerById.label, ex.getMessage()));
        }

        return resellerToReturn;
    }

    @Override
    public Reseller getResellerByUserId(UUID userId) {

        Reseller resellerToReturn = new Reseller();

        try {
            tReseller u = resellerRepository.findByUserId(userId);

            if (u != null) {
                resellerToReturn = ResellerUtils.transformReseller(u);
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerByUserId.label, ex.getMessage()));
        }

        return resellerToReturn;
    }

    @Override
    public Reseller getResellerByUserDeviceName(String userDeviceName) {

        Reseller resellerToReturn = new Reseller();

        try {

            tDeviceUser du = deviceUserRepository.findByNickname(userDeviceName);
            if (du != null) {

                tDevice d = deviceRepository.findById(du.getDevice().getDeviceId());
                if (du != null) {

                    tReseller r = resellerRepository.findById(d.getReseller().getResellerId());
                    if (r != null) {

                        resellerToReturn = ResellerUtils.transformReseller(r);

                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userDeviceNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerByUserDeviceName.label, ex.getMessage()));
        }

        return resellerToReturn;
    }

    @Override
    public List<Reseller> getResellerFiltered(@Nullable String resellerId, @Nullable String resellerName, boolean onlyChildren, @Nullable String field, @Nullable String orderField, int offset, int numberRecords) {

        List<Reseller> returnList = new ArrayList<>();

        try {


            UUID localResellerId = resellerId.equals("") ? null : UUID.fromString(resellerId);
            String localResellerName = resellerName.equals("") ? null : resellerName;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;


            List<tReseller> resellers = resellerRepository.getResellerFiltered(localResellerId, localResellerName, onlyChildren, localField, localOrderField, offset, numberRecords);

            if (resellers != null)
                returnList = ResellerUtils.transformResellerList(resellers);


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerFiltered.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountResellerFiltered(@Nullable String resellerId, @Nullable String resellerName, boolean onlyChildren) {

        try {


            UUID localResellerId = resellerId.equals("") ? null : UUID.fromString(resellerId);
            String localResellerName = resellerName.equals("") ? null : resellerName;


            long countResellers = resellerRepository.getCountResellerFiltered(localResellerId, localResellerName, onlyChildren);

            return countResellers;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getCountResellerFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public Reseller setReseller(UUID userId, UUID actionUserId) {

        Reseller reseller = new Reseller();

        try {
            tUser associatedUser = userRepository.findById(userId);
            tUser actionUser = userRepository.findById(actionUserId);

            if (associatedUser != null && actionUser != null) {

                tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(tUserType.UserTypeEnum.RESELLER.name());
                long nRole = userRoleRepository.countByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());

                if (nRole == 0) {
                    tReseller resellerToCreate = new tReseller();
                    resellerToCreate.setUser(associatedUser);
                    resellerToCreate.setCurrentBalance(0);
                    resellerToCreate.setCreationDate(LocalDateTime.now());

                    tReseller saved = resellerRepository.saveReseller(resellerToCreate);

                    if (saved != null) {

                        tUserRole role = new tUserRole();
                        role.setUser(associatedUser);
                        role.setUserType(userTypeVal);

                        userRoleRepository.saveUserRole(role);
                        userLogRepository.insertUserLog(actionUser, associatedUser, "ADD_RESELLER_ROLE", "RESELLER_ID:" + saved.getResellerId());
                        resellerLogRepository.insertResellerLog(actionUser, saved, "ADD_RESELLER", "");
                    }

                    if (saved != null) reseller = ResellerUtils.transformReseller(saved);
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userAlreadyHasRole.label, ""));
                }

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.setReseller.label, ex.getMessage()));
        }

        return reseller;
    }

    @Override
    public boolean setResellerBalanceMovement(UUID resellerId, String debitCredit, float movementValue, String movementType,
                                              String movementDetail, UUID actionUserId) {

        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                LocalDateTime localMovementDate = LocalDateTime.now();

                tReseller u = resellerRepository.findById(resellerId);

                if (u != null) {

                    tResellerMovementType movementTypeVal = resellerMovementTypeRepository
                            .findResellerMovementTypeByDescription(movementType);

                    if(movementTypeVal!= null) {
                        tResellerBalance rBalance = new tResellerBalance();
                        rBalance.setReseller(u);
                        rBalance.setMovementDate(localMovementDate);
                        rBalance.setDebitCredit(debitCredit.toUpperCase());
                        rBalance.setMovementValue(movementValue);
                        rBalance.setResellerMovementType(movementTypeVal);
                        rBalance.setMovementDetail(movementDetail);

                        resellerBalanceRepository.saveResellerBalance(rBalance);

                        u.setCurrentBalance(resellerBalanceRepository.getCurrentBalance(resellerId));
                        resellerRepository.saveReseller(u);

                        resellerLogRepository.insertResellerLog(actionUser, u, "ADD_RESELLER_BALANCE_MOVEMENT", "");

                        val = true;
                    } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.invalidStatus.label, ""));
                }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.setResellerBalanceMovement.label, ex.getMessage()));
        }

        return val;

    }

    @Override
    public List<ResellerBalance> getResellerBalanceMovements(UUID resellerId, @Nullable String startMovementDate,
                                                             @Nullable String endMovementDate, @Nullable String minValue,
                                                             @Nullable String maxValue, @Nullable String debitCredit,
                                                             @Nullable String movementType,
                                                             @Nullable String field, @Nullable String orderField,
                                                             int offset, int numberRecords) {

        List<ResellerBalance> returnList = new ArrayList<>();

        try {
            tReseller u = resellerRepository.findById(resellerId);

            if (u != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                String localMinValue = minValue.equals("") ? null : minValue;
                String localMaxValue = maxValue.equals("") ? null : maxValue;
                String localDebitCredit = debitCredit.equals("") ? null : debitCredit;
                String localMovementType = movementType.equals("") ? null : movementType;
                LocalDateTime localStartMovementDate = startMovementDate.equals("") ? null :
                        LocalDateTime.parse(startMovementDate, formatter);
                LocalDateTime localEndMovementDate = endMovementDate.equals("") ? null :
                        LocalDateTime.parse(endMovementDate, formatter).plusDays(1);

                String localField = field.equals("") ? null : field;
                String localOrderField = orderField.equals("") ? null : orderField;

                List<tResellerBalance> listBalance = resellerBalanceRepository.getResellerBalanceFiltered(resellerId,
                        localStartMovementDate, localEndMovementDate, localMinValue, localMaxValue, localDebitCredit,
                        localMovementType, localField, localOrderField, offset, numberRecords);

                if (listBalance != null)
                    returnList = ResellerUtils.transformResellerBalanceList(listBalance);


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerBalanceMovement.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountResellerBalanceMovements(UUID resellerId, @Nullable String startMovementDate,
                                                 @Nullable String endMovementDate, @Nullable String minValue,
                                                 @Nullable String maxValue, @Nullable String debitCredit,
                                                 @Nullable String movementType) {


        try {
            tReseller u = resellerRepository.findById(resellerId);

            if (u != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                String localMinValue = minValue.equals("") ? null : minValue;
                String localMaxValue = maxValue.equals("") ? null : maxValue;
                String localDebitCredit = debitCredit.equals("") ? null : debitCredit;
                String localMovementType = movementType.equals("") ? null : movementType;

                LocalDateTime localStartMovementDate = startMovementDate.equals("") ? null :
                        LocalDateTime.parse(startMovementDate, formatter);
                LocalDateTime localEndMovementDate = endMovementDate.equals("") ? null :
                        LocalDateTime.parse(endMovementDate, formatter).plusDays(1);


                long nBalance = resellerBalanceRepository.getCountResellerFiltered(resellerId,
                        localStartMovementDate, localEndMovementDate, localMinValue, localMaxValue, localDebitCredit,
                        localMovementType);

                return nBalance;

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getCountResellerBalanceMovement.label, ex.getMessage()));
        }
    }

    @Override
    public boolean removeReseller(UUID resellerId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                tReseller resellerValidation = resellerRepository.findById(resellerId);

                if (resellerValidation != null) {

                    tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(tUserType.UserTypeEnum.RESELLER.name());
                    tUser associatedUser = userRepository.findById(resellerValidation.getUser().getUserId());

                    if (!resellerAssociationRepository.existInResellerAssociation(resellerId, resellerId)) {

                        List<tDevice> associatedDevices = deviceRepository.getAllDevicesByResellerId(resellerId);

                        if (associatedDevices.isEmpty()) {

                            long nRole = userRoleRepository.countByUserIdAndUserTypeId(resellerValidation.getUser().getUserId(), userTypeVal.getUserTypeId());

                            if (nRole > 0) {
                                tUserRole role = userRoleRepository.findByUserIdAndUserTypeId(resellerValidation.getUser().getUserId(), userTypeVal.getUserTypeId());

                                userRoleRepository.deleteUserRoleById(role.getUserRoleId());
                                resellerLogRepository.deleteResellerLogByResellerId(resellerValidation.getResellerId());
                                resellerRepository.deleteResellerById(resellerValidation.getResellerId());

                                userLogRepository.insertUserLog(actionUser, associatedUser, "REMOVE_RESELLER_ROLE", "");

                                val = true;

                            } else {
                                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.roleNotExist.label, ""));
                            }
                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerHasDevices.label, ""));
                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.hasAssoc.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.removeReseller.label, ex.getMessage()));
        }

        return val;
    }

    @Override
    public boolean setResellerAssociation(UUID parentResellerId, UUID childResellerId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                tReseller parent = resellerRepository.findById(parentResellerId);
                tReseller child = resellerRepository.findById(childResellerId);

                if (parent != null && child != null) {
                    long exists = resellerAssociationRepository.countAssociationByChildResellerId(childResellerId);

                    if (exists == 0) {

                        if (!resellerRepository.isCircularAssociation(parentResellerId, childResellerId)) {

                            tResellerAssociation assoc = new tResellerAssociation();
                            assoc.setParentReseller(parent);
                            assoc.setChildReseller(child);

                            tResellerAssociation saved = resellerAssociationRepository.saveResellerAssociation(assoc);

                            resellerLogRepository.insertResellerLog(actionUser, parent, "ADD_RESELLER_ASSOCIATION", "ADD CHILD ID: " + child.getResellerId());
                            resellerLogRepository.insertResellerLog(actionUser, child, "ADD_RESELLER_ASSOCIATION", "ADD PARENT ID: " + parent.getResellerId());
                            resellerAssociationLogRepository.insertResellerAssociationLog(actionUser, saved, "ADD_RESELLER_ASSOCIATION", "ADD CHILD ID: " + child.getResellerId() + " TO PARENT ID: " + parent.getResellerId());

                            val = true;

                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.hasAssoc.label, ""));
                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.childHasParent.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.setResellerAssoc.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean removeResellerAssociation(UUID parentResellerId, UUID childResellerId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                tReseller parent = resellerRepository.findById(parentResellerId);
                tReseller child = resellerRepository.findById(childResellerId);

                if (parent != null && child != null) {
                    tResellerAssociation assoc = resellerAssociationRepository.getAssociation(parentResellerId, childResellerId);

                    if (assoc != null) {

                        resellerAssociationLogRepository.deleteResellerAssociationLogByAssociationId(assoc.getResellerAssociationId());
                        resellerAssociationRepository.deleteAssociationById(assoc.getResellerAssociationId());

                        resellerLogRepository.insertResellerLog(actionUser, parent, "REMOVE_RESELLER_ASSOCIATION", "REMOVE CHILD ID: " + child.getResellerId());
                        resellerLogRepository.insertResellerLog(actionUser, child, "REMOVE_RESELLER_ASSOCIATION", "REMOVE PARENT ID: " + parent.getResellerId());

                        val = true;

                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.assocNotExist.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.removeResellerAssoc.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public ResellerAssociation getResellerAssociation(UUID parentResellerId, UUID childResellerId) {
        ResellerAssociation assoc = new ResellerAssociation();

        try {

            tReseller parent = resellerRepository.findById(parentResellerId);
            tReseller child = resellerRepository.findById(childResellerId);

            if (parent != null && child != null) {

                tResellerAssociation saved = resellerAssociationRepository.getAssociation(parentResellerId, childResellerId);

                if (saved != null) {

                    assoc = ResellerUtils.transformResellerAssociation(saved);
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.assocNotExist.label, ""));
                }


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerAssoc.label, ex.getMessage()));
        }
        return assoc;
    }


    @Override
    public Reseller getResellerParentByChildId(UUID childResellerId) {
        Reseller assoc = new Reseller();

        try {

            tReseller child = resellerRepository.findById(childResellerId);

            if (child != null) {

                tResellerAssociation saved = resellerAssociationRepository.getAssociationByChildResellerId(childResellerId);

                if (saved != null) {
                    tReseller parent = resellerRepository.findById(saved.getParentReseller().getResellerId());
                    assoc = ResellerUtils.transformReseller(parent);

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.assocNotExist.label, ""));
                }


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getResellerParentByChildId.label, ex.getMessage()));
        }
        return assoc;
    }

    @Override
    public List<Reseller> getAvailableResellerParent(UUID resellerId, int offset, int numberRecords) {

        List<Reseller> parentsList = new ArrayList<>();

        try {

            tReseller reseller = resellerRepository.findById(resellerId);

            if (reseller != null) {


                List<UUID> childrenIds = getAllIdsInHierachy(resellerId);

                List<tReseller> available = resellerRepository.findByResellerIdNotIn(childrenIds);

                if (available != null)
                    parentsList = ResellerUtils.transformResellerList(available);


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getAvailableResellerParent.label, ex.getMessage()));
        }
        return parentsList;
    }

    @Override
    public long getCountAvailableResellerParent(UUID resellerId) {

        long size = 0;

        try {

            tReseller reseller = resellerRepository.findById(resellerId);

            if (reseller != null) {

                List<UUID> childrenIds = getAllIdsInHierachy(resellerId);

                List<tReseller> available = resellerRepository.findByResellerIdNotIn(childrenIds);

                if (available != null) size = available.size();


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.getCountAvailableResellerParent.label, ex.getMessage()));
        }

        return size;
    }

    @Override
    public boolean isHierarchyValid(UUID resellerId, UUID childResellerId) {

        boolean val = false;
        try {

            tReseller reseller = resellerRepository.findById(resellerId);
            tReseller child = resellerRepository.findById(childResellerId);

            if (reseller != null && child != null) {

                List<UUID> childrenIds = getAllIdsInHierachy(resellerId);

                if (childrenIds.contains(childResellerId))
                    val = true;

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.ResellerGeneralRepoFault.isHierarchyValid.label, ex.getMessage()));
        }
        return val;
    }

    public List<UUID> getAllIdsInHierachy(UUID resellerId) {

        List<tReseller> allChildren = resellerRepository.getAllLevelChildrenByParentId(resellerId);
        List<UUID> resellerIds = new ArrayList<>();
        resellerIds.add(resellerId);

        if (allChildren != null) allChildren.forEach(r -> resellerIds.add(r.getResellerId()));

        return resellerIds;
    }
}

