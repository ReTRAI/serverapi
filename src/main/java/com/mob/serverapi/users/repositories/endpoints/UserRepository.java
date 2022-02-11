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

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(int id) {

        User userToReturn = new User();

        try {
            tUser u = userRepository.findById(id);

            if (u != null)
                userToReturn = UserUtils.transformUser(u);
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("GETUSERBYID", ex.getMessage()));
        }

        return userToReturn;
    }

    @Override
    public User userLogin(int userId) {

//        if(u.getUserStatus().getDescription().equals("BLOCKED") ){
//            throw new ServiceFaultException("WARNING",new ServiceFault(
//                    "USER_BLOCKED", ""));
//        }
//        else if(u.getUserStatus().getDescription().equals("INACTIVE") ){
//            throw new ServiceFaultException("WARNING",new ServiceFault(
//                    "USER_INACTIVE", ""));
//        }
        return null;
    }

    @Override
    public User setUser(String userName, String userEmail, String userPassword, String userType) {
        User createdUser = new User();

        try {
            String langPref = "EN";
            String themePref = "L";
            LocalDateTime creationDate = LocalDateTime.now();
            tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(userType);
            tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription("CHANGEPW");

            tUser userToCreate = new tUser();
            userToCreate.setUserName(userName);
            userToCreate.setUserEmail(userEmail);
            userToCreate.setPassword(userPassword);
            userToCreate.setCreationDate(creationDate);
            userToCreate.setThemePreference(themePref);
            userToCreate.setLanguagePreference(langPref);

            userToCreate.setUserType(userTypeVal);
            userToCreate.setUserStatus(userStatusVal);

            tUser saved = userRepository.savetUser(userToCreate);

            if (saved != null)
                createdUser = UserUtils.transformUser(saved);
        }
        catch (Exception ex){
            throw new ServiceFaultException("ERROR",new ServiceFault("SETUSER", ex.getMessage()));
        }
        return createdUser;
    }

    @Override
    public boolean unblockUser(int userId) {
        return false;
    }

    @Override
    public boolean changeUserPw(int userId) {
        return false;
    }

    @Override
    public boolean inactivateUser(int userId) {
        return false;
    }

    @Override
    public boolean changeLangPreference(int userId) {
        return false;
    }

    @Override
    public boolean changeThemePreference(int userId) {
        return false;
    }


}

