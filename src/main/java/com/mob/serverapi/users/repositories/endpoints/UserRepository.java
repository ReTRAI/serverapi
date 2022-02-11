package com.mob.serverapi.users.repositories.endpoints;

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

        tUser u = userRepository.findById(id);

        if(u != null)
            userToReturn = UserUtils.transformUser(u);

        return userToReturn;
    }

    @Override
    public User userLogin(int userId) {
        return null;
    }

    @Override
    public User setUser(String userName, String userEmail, String userPassword, String userType) {
        User createdUser = new User();


        String langPref = "EN";
        String themePref ="L";
        LocalDateTime creationDate = LocalDateTime.now();
        tUserType userTypeVal= userTypeRepository.findUserTypeByDescription(userType);
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

        if(saved != null)
            createdUser = UserUtils.transformUser(saved);

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

//    @Override
//    @Transactional
//    public boolean setUser(String userName, String userCode, String userEmail) {
//
//        int val = entityManager.createNativeQuery("INSERT INTO user (user_name, user_code, user_email) " +
//                        "VALUES (:userName,:userCode,:userEmail)")
//                .setParameter("userName", userName)
//                .setParameter("userCode", userCode)
//                .setParameter("userEmail", userEmail)
//                .executeUpdate();
//
//        return val==1 ?true:false;
//    }
}

