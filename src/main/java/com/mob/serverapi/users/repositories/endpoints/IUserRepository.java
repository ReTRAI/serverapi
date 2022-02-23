package com.mob.serverapi.users.repositories.endpoints;

import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.base.UserRole;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


interface IUserRepository {

    User getUserById(UUID userId);

    User userLogin(String userEmail, String userPassword);

    User setUser(String userName, String userEmail, String userPassword, UUID actionUserId);

    boolean unblockUser(UUID userId, UUID actionUserId);

    boolean blockUser(UUID userId);

    boolean changeUserPw(UUID userId, String newPassword, UUID actionUserId);

    boolean activateUser(UUID userId, UUID actionUserId);

    boolean inactivateUser(UUID userId, UUID actionUserId);

    boolean changeLangPreference(UUID userId, String lang, UUID actionUserId);

    boolean changeThemePreference(UUID userId, String theme, UUID actionUserId);

    boolean existUserName(String userName);

    boolean existUserEmail(String userEmail);

    List<UserRole> getUserRolesByUserById (UUID userId);

    List<User> getUserFiltered(@Nullable String userId, @Nullable String userName,
                               @Nullable String userStatus, @Nullable String userEmail,
                               @Nullable String startCreationDate, @Nullable String endCreationDate,
                               @Nullable String field,
                               @Nullable String orderField, int offset, int numberRecords);

    long getCountUserFiltered(@Nullable String userId, @Nullable String userName,
                                     @Nullable String userStatus, @Nullable String userEmail,
                              @Nullable String startCreationDate, @Nullable String endCreationDate);

}
