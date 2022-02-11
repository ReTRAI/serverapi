package com.mob.serverapi.users.repositories.endpoints;

import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.utils.UserUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;


public class UserRepository implements IUserRepository {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(int id) {

        User userToReturn = new User();
        tUser u= (tUser) entityManager.createNativeQuery("SELECT * FROM user u " +
                        "WHERE u.user_id = :userId", tUser.class)
                .setParameter("userId", id)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

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

        int val = entityManager.createNativeQuery("INSERT INTO user (user_name, user_email, password, " +
                        "creation_date, language_preference, theme_preference, user_status_id, user_type_id) " +
                        "VALUES (:userName,:userEmail, :password, :creationDate, " +
                        ":langPreference, :themePreference, :userStatusId, :userTypeId)")

                .setParameter("userName", userName)
                .setParameter("userEmail", userEmail)
                .setParameter("password", userPassword)
                .setParameter("creationDate", creationDate)
                .setParameter("langPreference", langPref)
                .setParameter("themePreference", themePref)
                .setParameter("userStatusId", 1)
                .setParameter("userTypeId", 1)

                .executeUpdate();

        return null;
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

