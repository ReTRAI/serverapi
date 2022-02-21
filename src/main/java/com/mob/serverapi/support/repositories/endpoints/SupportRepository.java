package com.mob.serverapi.support.repositories.endpoints;

import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.support.base.Support;
import com.mob.serverapi.support.database.tSupport;
import com.mob.serverapi.support.repositories.database.tSupportLogRepository;
import com.mob.serverapi.support.repositories.database.tSupportRepository;
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
                    supportLogRepository.deleteResellerLogBySupportId(supportValidation.getSupportId());
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


            UUID localResellerId = supportId.equals("") ? null : UUID.fromString(supportId);
            String localResellerName = supportName.equals("") ? null : supportName;
            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;


            List<tSupport> support = supportRepository.getSupportFiltered(localResellerId, localResellerName,
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


            UUID localResellerId = supportId.equals("") ? null : UUID.fromString(supportId);
            String localResellerName = supportName.equals("") ? null : supportName;


            long countResellers = supportRepository.getCountSupportFiltered(localResellerId, localResellerName,
                    onlyChildren);

            return countResellers;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_COUNT_SUPPORT_FILTERED", ex.getMessage()));
        }
    }
}

