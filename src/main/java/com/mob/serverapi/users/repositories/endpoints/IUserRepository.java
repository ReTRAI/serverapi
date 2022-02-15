package com.mob.serverapi.users.repositories.endpoints;

import com.mob.serverapi.users.base.User;


interface IUserRepository {

    User getUserById(int userId);

    User userLogin(String userEmail, String userPassword);

    User setUser(String userName, String userEmail, String userPassword, int actionUserId);

    boolean unblockUser(int userId, int actionUserId);

    boolean blockUser(int userId);

    boolean changeUserPw(int userId, String newPassword, int actionUserId);

    boolean inactivateUser(int userId, int actionUserId);

    boolean changeLangPreference(int userId, String lang, int actionUserId);

    boolean changeThemePreference(int userId, String theme, int actionUserId);


}
