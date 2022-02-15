package com.mob.serverapi.reseller.repositories.endpoints;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.repositories.database.tDeviceRepository;
import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.repositories.database.tResellerLogRepository;
import com.mob.serverapi.reseller.repositories.database.tResellerRepository;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserRole;
import com.mob.serverapi.users.database.tUserType;
import com.mob.serverapi.users.repositories.database.tUserLogRepository;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.users.repositories.database.tUserRoleRepository;
import com.mob.serverapi.users.repositories.database.tUserTypeRepository;
import com.mob.serverapi.utils.ResellerUtils;
import com.mob.serverapi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class ResellerRepository implements IResellerRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tResellerRepository resellerRepository = new tResellerRepository();
    @Autowired
    protected tResellerLogRepository resellerLogRepository = new tResellerLogRepository();
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
    public Reseller getResellerById(int resellerId) {

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
    public Reseller setReseller(int userId, int actionUserId) {

        Reseller reseller = new Reseller();

        try {
            tUser associatedUser = userRepository.findById(userId);
            tUser actionUser = userRepository.findById(actionUserId);

            if (associatedUser != null && actionUser != null) {

                tUserType userTypeVal = userTypeRepository.
                        findUserTypeByDescription(tUserType.UserTypeEnum.RESELLER.name());
                long nRole = userRoleRepository.countByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());

                if(nRole == 0) {
                    tReseller resellerToCreate = new tReseller();
                    resellerToCreate.setUserId(associatedUser);
                    resellerToCreate.setCurrentBalance(0);

                    tReseller saved = resellerRepository.saveReseller(resellerToCreate);

                    if (saved != null) {

                        tUserRole role = new tUserRole();
                        role.setUser(associatedUser);
                        role.setUserType(userTypeVal);

                        userRoleRepository.saveUserRole(role);
                        userLogRepository.insertUserLog(associatedUser, actionUser, "ADD_RESELLER_ROLE", "RESELLER_ID:" + saved.getResellerId());
                        resellerLogRepository.insertResellerLog(associatedUser, saved, "ADD_RESELLER", "" );
                    }

                    if (saved != null)
                        reseller = ResellerUtils.transformReseller(saved);
                }
                else {
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
    public boolean removeReseller(int resellerId, int actionUserId) {
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

                        resellerRepository.deleteResellerById(resellerValidation.getResellerId());
                        userLogRepository.insertUserLog(associatedUser, actionUser, "REMOVE_RESELLER_ROLE", "");

                        val=true;
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
}

