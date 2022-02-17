package com.mob.serverapi.reseller.repositories.endpoints;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.repositories.database.tDeviceRepository;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.repositories.database.tResellerAssociationLogRepository;
import com.mob.serverapi.reseller.repositories.database.tResellerAssociationRepository;
import com.mob.serverapi.reseller.repositories.database.tResellerLogRepository;
import com.mob.serverapi.reseller.repositories.database.tResellerRepository;
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
import com.mob.serverapi.reseller.base.*;

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
                        userLogRepository.insertUserLog(associatedUser, actionUser, "ADD_RESELLER_ROLE", "RESELLER_ID:" + saved.getResellerId());
                        resellerLogRepository.insertResellerLog(associatedUser, saved, "ADD_RESELLER", "");
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

                        userLogRepository.insertUserLog(associatedUser, actionUser, "REMOVE_RESELLER_ROLE", "");

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
                    long exists = resellerAssociationRepository.countAssociationByChildResellerId(parentResellerId);

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
                        resellerAssociationRepository.deleteAssociation(parentResellerId, childResellerId);

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

