package com.mob.serverapi.users.repositories.endpoints;

import com.mob.serverapi.users.base.User;


interface IUserRepository {

    User getUserById(int userId);

    User userLogin(int userId);

    User setUser(String userName, String userEmail, String userPassword, String userType);

    boolean unblockUser(int userId);

    boolean changeUserPw(int userId);

    boolean inactivateUser(int userId);

    boolean changeLangPreference(int userId, String lang);

    boolean changeThemePreference(int userId, String theme);


}
