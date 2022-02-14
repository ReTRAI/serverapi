package com.mob.serverapi.users.repositories.endpoints;

import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.database.*;
import com.mob.serverapi.users.repositories.database.*;
import com.mob.serverapi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;


public class UserRepository implements IUserRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tUserRepository userRepository = new tUserRepository();
    @Autowired
    protected tUserTypeRepository userTypeRepository = new tUserTypeRepository();
    @Autowired
    protected tUserStatusRepository userStatusRepository = new tUserStatusRepository();
    @Autowired
    protected tUserLogRepository userLogRepository = new tUserLogRepository();
    @Autowired
    protected tUserLoginLogRepository userLoginLogRepository = new tUserLoginLogRepository();

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(int id) {

        User userToReturn = new User();

        try {
            tUser u = userRepository.findById(id);

            if (u != null)
                userToReturn = UserUtils.transformUser(u);
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("GET_USER_BY_ID", ex.getMessage()));
        }

        return userToReturn;
    }

    @Override
    public User userLogin(String userEmail, String userPassword) {
        User validateUser = new User();

        try{

            tUser user = userRepository.findByEmail(userEmail);

            if(user != null) {
                tUserStatus.UserStatusEnum status = tUserStatus.UserStatusEnum.valueOf(user.getUserStatus().getDescription());

                if (UserUtils.verifyHash(userPassword, user.getPasswordSalt(), user.getPasswordHash())) {

                    switch (status) {
                        case INACTIVE:
                            throw new ServiceFaultException("WARNING",
                                    new ServiceFault("USER_INACTIVE", ""));
                        case BLOCKED:
                            throw new ServiceFaultException("WARNING",
                                    new ServiceFault("USER_BLOCKED", ""));
                        case CHANGEPW:
                            throw new ServiceFaultException("WARNING",
                                    new ServiceFault("USER_CHANGE_PW", ""));

                        case ACTIVE:

                            validateUser = UserUtils.transformUser(user);

                            userLoginLogRepository.insertUserLoginLog(user,true,"");
                            userLogRepository.insertUserLog(user,user,"VALID_USER", "SUCCESSFUL LOGIN");

                    }
                } else {

                   tUserLoginLog lastValid = userLoginLogRepository.getLastValid(user.getUserId(),true);

                   if(lastValid != null) {

                       long countFailed = userLoginLogRepository.countFailedLogins(user.getUserId(),false,lastValid.getLoginDate());

                       if(countFailed>2)
                           blockUser(user.getUserId());
                   }

                    userLoginLogRepository.insertUserLoginLog(user,false,"WRONG CREDENTIALS");
                    userLogRepository.insertUserLog(user,user,"WRONG_CREDENTIALS", "WRONG CREDENTIALS");
                    throw new ServiceFaultException("ERROR", new ServiceFault("WRONG_CREDENTIALS", ""));
                }
            }
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("LOGIN_FAILED", ex.getMessage()));
        }
        return validateUser;
    }

    @Override
    public User setUser(String userName, String userEmail, String userPassword, String userType, int actionUserId) {
        User createdUser = new User();

        try {

            tUser userExist = userRepository.findByEmail(userEmail);

            if(userExist == null) {
                tUser actionUser = userRepository.findById(actionUserId);

                if (actionUserId == 0 || actionUser != null) {

                    String langPref = "EN";
                    String themePref = "L";
                    LocalDateTime creationDate = LocalDateTime.now();
                    tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(userType);
                    tUserStatus userStatusVal = userStatusRepository.
                            findUserStatusByDescription(tUserStatus.UserStatusEnum.CHANGEPW.name());
                    byte[] passwdSalt = UserUtils.createPasswordSalt();
                    byte[] passwdHash = UserUtils.hashPassword(passwdSalt, userPassword);

                    tUser userToCreate = new tUser();
                    userToCreate.setUserName(userName);
                    userToCreate.setUserEmail(userEmail);
                    userToCreate.setPasswordSalt(passwdSalt);
                    userToCreate.setPasswordHash(passwdHash);
                    userToCreate.setCreationDate(creationDate);
                    userToCreate.setThemePreference(themePref);
                    userToCreate.setLanguagePreference(langPref);

                    userToCreate.setUserType(userTypeVal);
                    userToCreate.setUserStatus(userStatusVal);

                    tUser saved = userRepository.savetUser(userToCreate);

                    if (saved != null)
                        createdUser = UserUtils.transformUser(saved);

                    if (actionUserId == 0) {
                        actionUserId = saved.getUserId();
                        actionUser = userRepository.findById(actionUserId);
                    }


                    userLogRepository.insertUserLog(actionUser, saved, "CREATE", "CREATE NEW USER");
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("INVALID_ACTION_USER", ""));
                }
            }
            else {
                throw new ServiceFaultException("ERROR", new ServiceFault("EMAIL ALREADY EXISTS", ""));

            }
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("SET_USER", ex.getMessage()));
        }
        return createdUser;
    }

    @Override
    public boolean unblockUser(int userId, int actionUserId) {
        boolean val = false;
        try{
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);

            if(getUser.getUserStatus().getDescription().equals(tUserStatus.UserStatusEnum.BLOCKED.name()))
            {
                tUserStatus userStatusVal = userStatusRepository
                        .findUserStatusByDescription(tUserStatus.UserStatusEnum.ACTIVE.name());


            getUser.setUserStatus(userStatusVal);

            tUser saved = userRepository.savetUser(getUser);

            userLogRepository.insertUserLog(actionUser,saved,"UNBLOCKED", "UNBLOCK USER");

            val=true;
            }
            else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER NOT BLOCKED", ""));
            }
        }

        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("UNBLOCK_USER", ex.getMessage()));
        }
        return val;
    }

    @Override
    //NOT IN ENDPOINT
    public boolean blockUser(int userId) {
        boolean val = false;
        try{
            tUser getUser = userRepository.findById(userId);
            tUserStatus userStatusVal = userStatusRepository
                    .findUserStatusByDescription(tUserStatus.UserStatusEnum.BLOCKED.name());

            getUser.setUserStatus(userStatusVal);

            tUser saved = userRepository.savetUser(getUser);

            userLogRepository.insertUserLog(saved,saved,"BLOCKED", "BLOCKED BY EXCEEDING MAX ATTEMPTS");
            val=true;
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("UNBLOCK_USER", ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean changeUserPw(int userId, String newPassword, int actionUserId) {
        boolean val = false;
        try{
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);
            byte[] passwdSalt = UserUtils.createPasswordSalt();
            byte[] passwdHash = UserUtils.hashPassword(passwdSalt,newPassword);

            getUser.setPasswordSalt(passwdSalt);
            getUser.setPasswordHash(passwdHash);

            tUser saved = userRepository.savetUser(getUser);

            userLogRepository.insertUserLog(actionUser,saved,"CHANGEPW", "CHANGE PASSWORD FOR USER");

            val=true;
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("CHANGE_USER_PW", ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean inactivateUser(int userId, int actionUserId) {
        boolean val = false;
        try{
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);
            tUserStatus userStatusVal = userStatusRepository
                     .findUserStatusByDescription(tUserStatus.UserStatusEnum.INACTIVE.name());

            getUser.setUserStatus(userStatusVal);
            getUser.setInactivationDate(LocalDateTime.now());

            tUser saved = userRepository.savetUser(getUser);

            userLogRepository.insertUserLog(actionUser,saved,"INACTIVATE", "INACTIVATE USER");

            val=true;
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("INACTIVATE_USER", ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean changeLangPreference(int userId, String lang, int actionUserId) {
        boolean val = false;
        try{
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);
            getUser.setLanguagePreference(lang);

            tUser saved = userRepository.savetUser(getUser);

            userLogRepository.insertUserLog(actionUser,saved,"CHANGELANG", "CHANGE LANGUAGE FOR USER");
            val=true;
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("CHANGE_LANG", ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean changeThemePreference(int userId, String theme, int actionUserId) {
        boolean val = false;
        try{
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);
            getUser.setThemePreference(theme);

            tUser saved = userRepository.savetUser(getUser);

            userLogRepository.insertUserLog(actionUser,saved,"CHANGETHEME", "CHANGE THEME FOR USER");
            val=true;
        }
        catch (ServiceFaultException se) {
            throw se;
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("CHANGE_THEME", ex.getMessage()));
        }
        return val;
    }


}

