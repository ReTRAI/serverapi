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
import com.mob.serverapi.reseller.repositories.database.*;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserRole;
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
import java.util.ArrayList;
import java.util.List;
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


    @Override
    public Reseller getResellerById(UUID resellerId) {

        Reseller resellerToReturn = new Reseller();

        try {
            tReseller u = resellerRepository.findById(resellerId);

            if (u != null) {
                resellerToReturn = ResellerUtils.transformReseller(u);
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_RESELLER_BY_ID", ex.getMessage()));
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
                throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_RESELLER_BY_USER_ID", ex.getMessage()));
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
                        throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXIST", ""));
                    }
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("DEVICE_DONT_EXIST", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USERDEVICE_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_RESELLER_BY_ID", ex.getMessage()));
        }

        return resellerToReturn;
    }

    @Override
    public List<Reseller> getResellerFiltered(@Nullable String resellerId, @Nullable String resellerName,
                                              boolean onlyChildren, @Nullable String field,
                                              @Nullable String orderField, int offset, int numberRecords) {

        List<Reseller> returnList = new ArrayList<>();

        try {


            UUID localResellerId = resellerId.equals("") ? null : UUID.fromString(resellerId);
            String localResellerName = resellerName.equals("") ? null : resellerName;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;


            List<tReseller> resellers = resellerRepository.getResellerFiltered(localResellerId, localResellerName,
                    onlyChildren, localField, localOrderField, offset, numberRecords);

            if (resellers != null) {
                returnList = ResellerUtils.transformResellerList(resellers);

            } else {
                throw new ServiceFaultException("WARNING", new ServiceFault("EMPTY_RESELLER_LIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_RESELLER_FILTERED", ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountResellerFiltered(@Nullable String resellerId, @Nullable String resellerName,
                                         boolean onlyChildren) {

        try {


            UUID localResellerId = resellerId.equals("") ? null : UUID.fromString(resellerId);
            String localResellerName = resellerName.equals("") ? null : resellerName;


            long countResellers = resellerRepository.getCountResellerFiltered(localResellerId, localResellerName,
                    onlyChildren);

            return countResellers;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_COUNT_RESELLER_FILTERED", ex.getMessage()));
        }
    }

    @Override
    public Reseller setReseller(UUID userId, UUID actionUserId) {

        Reseller reseller = new Reseller();

        try {
            tUser associatedUser = userRepository.findById(userId);
            tUser actionUser = userRepository.findById(actionUserId);

            if (associatedUser != null && actionUser != null) {

                tUserType userTypeVal = userTypeRepository.
                        findUserTypeByDescription(tUserType.UserTypeEnum.RESELLER.name());
                long nRole = userRoleRepository.countByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());

                if (nRole == 0) {
                    tReseller resellerToCreate = new tReseller();
                    resellerToCreate.setUserId(associatedUser);
                    resellerToCreate.setCurrentBalance(0);
                    resellerToCreate.setTotalDevices(0);
                    resellerToCreate.setActiveDevices(0);
                    resellerToCreate.setFreeDevices(0);
                    resellerToCreate.setInactiveDevices(0);
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

                    if (saved != null)
                        reseller = ResellerUtils.transformReseller(saved);
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("USER_IS_ALREADY_RESELLER", ""));
                }

            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_RESELLER", ex.getMessage()));
        }

        return reseller;
    }

    @Override
    public boolean setResellerBalanceMovement(UUID resellerId, String debitCredit, float movementValue,
                                              UUID actionUserId) {

        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                LocalDateTime localMovementDate = LocalDateTime.now();

                tReseller u = resellerRepository.findById(resellerId);

                if (u != null) {

                    tResellerBalance rBalance = new tResellerBalance();
                    rBalance.setReseller(u);
                    rBalance.setMovementDate(localMovementDate);
                    rBalance.setDebitCredit(debitCredit);
                    rBalance.setMovementValue(movementValue);

                    resellerBalanceRepository.saveResellerBalance(rBalance);

                    u.setCurrentBalance(resellerBalanceRepository.getCurrentBalance(resellerId));
                    resellerRepository.saveReseller(u);

                    resellerLogRepository.insertResellerLog(actionUser, u, "ADD_RESELLER_BALANCE_MOVEMENT", "");

                    val = true;

                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXIST", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("ADD_RESELLER_BALANCE_MOVEMENT", ex.getMessage()));
        }

        return val;

    }

    @Override
    public List<ResellerBalance> getResellerBalanceMovements(UUID resellerId) {

        List<ResellerBalance> returnList = new ArrayList<>();

        try {
            tReseller u = resellerRepository.findById(resellerId);

            if (u != null) {

                List<tResellerBalance> listBalance = resellerBalanceRepository.getAllBalanceMovements(resellerId);

                if (listBalance != null) {
                    returnList = ResellerUtils.transformResellerBalanceList(listBalance);
                }

            } else {
                throw new ServiceFaultException("WARNING", new ServiceFault("RESELLER_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_RESELLER_BALANCE_MOVEMENTS", ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public boolean removeReseller(UUID resellerId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                tReseller resellerValidation = resellerRepository.findById(resellerId);

                if (resellerValidation != null) {

                    tUserType userTypeVal = userTypeRepository.
                            findUserTypeByDescription(tUserType.UserTypeEnum.RESELLER.name());
                    tUser associatedUser = userRepository.findById(resellerValidation.getUserId().getUserId());
                    tUserRole role = userRoleRepository.findByUserIdAndUserTypeId(resellerValidation.getUserId().getUserId(), userTypeVal.getUserTypeId());

                    List<tDevice> associatedDevices = deviceRepository.getAllDevicesByResellerId(resellerId);

                    if (associatedDevices.isEmpty()) {

                        userRoleRepository.deleteUserRoleById(role.getUserRoleId());
                        resellerLogRepository.deleteResellerLogByResellerId(resellerValidation.getResellerId());
                        resellerRepository.deleteResellerById(resellerValidation.getResellerId());

                        userLogRepository.insertUserLog(actionUser, associatedUser, "REMOVE_RESELLER_ROLE", "");

                        val = true;
                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("RESSELER_HAS_DEVICES", ""));
                    }

                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("RESSELER_DONT_EXIST", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("REMOVE_RESELLER", ex.getMessage()));
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


                        tResellerAssociation assoc = new tResellerAssociation();
                        assoc.setParentReseller(parent);
                        assoc.setChildReseller(child);

                        tResellerAssociation saved = resellerAssociationRepository.saveResellerAssociation(assoc);

                        resellerLogRepository.insertResellerLog(actionUser, parent, "ADD_RESELLER_ASSOCIATION", "ADD CHILD ID: " + child.getResellerId());
                        resellerLogRepository.insertResellerLog(actionUser, child, "ADD_RESELLER_ASSOCIATION", "ADD PARENT ID: " + parent.getResellerId());
                        resellerAssociationLogRepository.insertResellerAssociationLog(actionUser, saved, "ADD_RESELLER_ASSOCIATION",
                                "ADD CHILD ID: " + child.getResellerId() + " TO PARENT ID: " + parent.getResellerId());

                        val = true;

                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("CHILD_RESELLER_ALREADY_ASSOCIATED", ""));
                    }
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXISTS", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_RESELLER_ASSOCIATION", ex.getMessage()));
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

                        resellerAssociationLogRepository.deleteResellerAssociationLogByResellerId(assoc.getResellerAssociationId());
                        resellerAssociationRepository.deleteAssociationById(assoc.getResellerAssociationId());

                        resellerLogRepository.insertResellerLog(actionUser, parent, "REMOVE_RESELLER_ASSOCIATION", "ADD CHILD ID: " + child.getResellerId());
                        resellerLogRepository.insertResellerLog(actionUser, child, "REMOVE_RESELLER_ASSOCIATION", "ADD PARENT ID: " + parent.getResellerId());

                        val = true;

                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("ASSOCIATION_DONT_EXIST", ""));
                    }
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXISTS", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_RESELLER_ASSOCIATION", ex.getMessage()));
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
                    throw new ServiceFaultException("ERROR", new ServiceFault("ASSOCIATION_DONT_EXISTS", ""));
                }


            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("RESELLER_DONT_EXISTS", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_RESELLER_ASSOCIATION", ex.getMessage()));
        }
        return assoc;
    }
}

