package com.mob.serverapi.users.repositories.endpoints;

import com.mob.serverapi.servicefault.FaultMapping;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.base.UserRole;
import com.mob.serverapi.users.database.*;
import com.mob.serverapi.users.repositories.database.*;
import com.mob.serverapi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserRepository implements IUserRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tUserRepository userRepository = new tUserRepository();
    @Autowired
    protected tUserRoleRepository userRoleRepository = new tUserRoleRepository();
    @Autowired
    protected tUserStatusRepository userStatusRepository = new tUserStatusRepository();
    @Autowired
    protected tUserTypeRepository userTypeRepository = new tUserTypeRepository();
    @Autowired
    protected tUserLogRepository userLogRepository = new tUserLogRepository();
    @Autowired
    protected tUserLoginLogRepository userLoginLogRepository = new tUserLoginLogRepository();

    @Override
    public User getUserById(UUID id) {

        User userToReturn = new User();

        try {
            tUser u = userRepository.findById(id);

            if (u != null) {
                userToReturn = UserUtils.transformUser(u);
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.getUserById.label, ex.getMessage()));
        }

        return userToReturn;
    }

    @Override
    public User userLogin(String userEmail, String userPassword) {
        User validateUser = new User();

        try {

            tUser user = userRepository.findByEmail(userEmail);

            if (user != null) {
                tUserStatus.UserStatusEnum status = tUserStatus.UserStatusEnum.valueOf(user.getUserStatus().getDescription());

                if (UserUtils.verifyHash(userPassword, user.getPasswordSalt(), user.getPasswordHash())) {

                    switch (status) {
                        case INACTIVE:
                            throw new ServiceFaultException(FaultMapping.FaultType.warning.label, new ServiceFault(FaultMapping.RepoFault.userInactive.label, ""));
                        case BLOCKED:
                            throw new ServiceFaultException(FaultMapping.FaultType.warning.label, new ServiceFault(FaultMapping.RepoFault.userBlocked.label, ""));

                        case CHANGEPW:
                        case ACTIVE:

                            validateUser = UserUtils.transformUser(user);

                            userLoginLogRepository.insertUserLoginLog(user, true, "");
                            userLogRepository.insertUserLog(user, user, "VALID_USER", "SUCCESSFUL LOGIN");

                    }
                } else {

                    tUserLoginLog lastValid = userLoginLogRepository.getLastValid(user.getUserId(), true);

                    if (lastValid != null) {

                        long countFailed = userLoginLogRepository.countFailedLogins(user.getUserId(), false, lastValid.getLoginDate());

                        if (countFailed > 2) blockUser(user.getUserId());
                    }

                    userLoginLogRepository.insertUserLoginLog(user, false, FaultMapping.RepoFault.wrongCredentials.label);
                    userLogRepository.insertUserLog(user, user, FaultMapping.RepoFault.wrongCredentials.label, FaultMapping.RepoFault.wrongCredentials.label);
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.wrongCredentials.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.wrongCredentials.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.loginFailed.label, ex.getMessage()));
        }
        return validateUser;
    }

    @Override
    public User setUser(String userName, String userEmail, String userPassword, UUID actionUserId) {
        User createdUser = new User();

        try {

            tUser userExistByName = userRepository.findByUserName(userName);
            if (userExistByName == null) {
                tUser userExist = userRepository.findByEmail(userEmail);

                if (userExist == null) {
                    tUser actionUser = userRepository.findById(actionUserId);

                    if (actionUserId.equals("") || actionUser != null) {

                        String langPref = "EN";
                        String themePref = "L";
                        LocalDateTime creationDate = LocalDateTime.now();
                        tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription(tUserStatus.UserStatusEnum.CHANGEPW.name());
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

                        userToCreate.setUserStatus(userStatusVal);

                        tUser saved = userRepository.saveUser(userToCreate);

                        if (saved != null) createdUser = UserUtils.transformUser(saved);

                        if (actionUserId.equals("")) {
                            actionUserId = saved.getUserId();
                            actionUser = userRepository.findById(actionUserId);
                        }


                        userLogRepository.insertUserLog(actionUser, saved, "CREATE", "CREATE NEW USER");
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.invalidActionUser.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.emailExist.label, ""));

                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.usernameExist.label, ""));

            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.setUser.label, ex.getMessage()));
        }
        return createdUser;
    }

    @Override
    public boolean unblockUser(UUID userId, UUID actionUserId) {
        boolean val = false;
        try {
            tUser actionUser = userRepository.findById(actionUserId);
            tUser getUser = userRepository.findById(userId);

            if (actionUser != null && getUser != null) {
                if (getUser.getUserStatus().getDescription().equals(tUserStatus.UserStatusEnum.BLOCKED.name())) {
                    tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription(tUserStatus.UserStatusEnum.CHANGEPW.name());


                    getUser.setUserStatus(userStatusVal);

                    tUser saved = userRepository.saveUser(getUser);

                    userLogRepository.insertUserLog(actionUser, saved, "UNBLOCKED", "UNBLOCK USER");

                    val = true;
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotBlocked.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.unblockUser.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    //NOT IN ENDPOINT
    public boolean blockUser(UUID userId) {
        boolean val = false;
        try {
            tUser getUser = userRepository.findById(userId);

            if (getUser != null) {
                tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription(tUserStatus.UserStatusEnum.BLOCKED.name());

                getUser.setUserStatus(userStatusVal);

                tUser saved = userRepository.saveUser(getUser);

                userLogRepository.insertUserLog(saved, saved, "BLOCKED", "BLOCKED BY EXCEEDING MAX ATTEMPTS");
                val = true;
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.blockUser.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean changeUserPw(UUID userId, String newPassword, UUID actionUserId) {
        boolean val = false;
        try {
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);

            if (actionUser != null && getUser != null) {
                byte[] passwdSalt = UserUtils.createPasswordSalt();
                byte[] passwdHash = UserUtils.hashPassword(passwdSalt, newPassword);
                tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription(tUserStatus.UserStatusEnum.ACTIVE.name());

                getUser.setUserStatus(userStatusVal);
                getUser.setPasswordSalt(passwdSalt);
                getUser.setPasswordHash(passwdHash);

                tUser saved = userRepository.saveUser(getUser);

                userLogRepository.insertUserLog(actionUser, saved, "CHANGEPW", "CHANGE PASSWORD FOR USER");

                val = true;
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.changeUserPassword.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean inactivateUser(UUID userId, UUID actionUserId) {
        boolean val = false;
        try {
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);

            if (actionUser != null && getUser != null) {
                tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription(tUserStatus.UserStatusEnum.INACTIVE.name());

                getUser.setUserStatus(userStatusVal);
                getUser.setInactivationDate(LocalDateTime.now());

                tUser saved = userRepository.saveUser(getUser);

                userLogRepository.insertUserLog(actionUser, saved, "INACTIVATE", "INACTIVATE USER");

                val = true;
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.inactivateUser.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean activateUser(UUID userId, UUID actionUserId) {
        boolean val = false;
        try {
            tUser actionUser = userRepository.findById(actionUserId);

            tUser getUser = userRepository.findById(userId);

            if (actionUser != null && getUser != null) {
                tUserStatus userStatusVal = userStatusRepository.findUserStatusByDescription(tUserStatus.UserStatusEnum.ACTIVE.name());

                getUser.setUserStatus(userStatusVal);
                getUser.setInactivationDate(LocalDateTime.now());

                tUser saved = userRepository.saveUser(getUser);

                userLogRepository.insertUserLog(actionUser, saved, "ACTIVATE", "ACTIVATE USER");

                val = true;
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.activateUser.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean changeLangPreference(UUID userId, String lang, UUID actionUserId) {
        boolean val = false;
        try {
            tUser actionUser = userRepository.findById(actionUserId);
            tUser getUser = userRepository.findById(userId);

            if (actionUser != null && getUser != null) {
                getUser.setLanguagePreference(lang);

                tUser saved = userRepository.saveUser(getUser);

                userLogRepository.insertUserLog(actionUser, saved, "CHANGELANG", "CHANGE LANGUAGE FOR USER");
                val = true;
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.changeLang.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean changeThemePreference(UUID userId, String theme, UUID actionUserId) {
        boolean val = false;
        try {
            tUser actionUser = userRepository.findById(actionUserId);
            tUser getUser = userRepository.findById(userId);

            if (actionUser != null && getUser != null) {
                getUser.setThemePreference(theme);

                tUser saved = userRepository.saveUser(getUser);

                userLogRepository.insertUserLog(actionUser, saved, "CHANGETHEME", "CHANGE THEME FOR USER");
                val = true;
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.changeTheme.label, ex.getMessage()));
        }
        return val;
    }

    @Override
    public boolean existUserName(String userName) {
        boolean val = false;
        try {

            boolean exist = userRepository.userExistsUserName(userName);

            if (exist) val = true;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.existUserName.label, ex.getMessage()));
        }
        return val;

    }

    @Override
    public boolean existUserEmail(String userEmail) {
        boolean val = false;
        try {

            boolean exist = userRepository.userExistsUserEmail(userEmail);

            if (exist) val = true;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.existUserEmail.label, ex.getMessage()));
        }
        return val;

    }

    @Override
    public List<UserRole> getUserRolesByUserById(UUID userId) {
        List<UserRole> userRoles = new ArrayList<>();

        try {
            tUser user = userRepository.findById(userId);

            if (user != null) {
                List<tUserRole> role = userRoleRepository.findAllRolesByUserId(userId);
                userRoles = UserUtils.transformUserRoleList(role);
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.getUserRoleByUser.label, ex.getMessage()));
        }
        return userRoles;
    }

    @Override
    public List<User> getUserFiltered(@Nullable String userId, @Nullable String userName, @Nullable String userStatus, @Nullable String userEmail, @Nullable String startCreationDate, @Nullable String endCreationDate, @Nullable String field, @Nullable String orderField, int offset, int numberRecords) {

        List<User> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localUserId = userId.equals("") ? null : UUID.fromString(userId);
            String localUserName = userName.equals("") ? null : userName;
            String localUserStatus = userStatus.equals("") ? null : userStatus;
            String localUserEmail = userEmail.equals("") ? null : userEmail;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null : LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null : LocalDateTime.parse(endCreationDate, formatter).plusDays(1);

            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;


            List<tUser> users = userRepository.getUserFiltered(localUserId, localUserName, localUserStatus, localUserEmail, localStartCreationDate, localEndCreationDate, localField, localOrderField, offset, numberRecords);

            if (users != null)
                returnList = UserUtils.transformUserList(users);


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.getUserFiltered.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountUserFiltered(@Nullable String userId, @Nullable String userName, @Nullable String userStatus, @Nullable String userEmail, @Nullable String startCreationDate, @Nullable String endCreationDate) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localUserId = userId.equals("") ? null : UUID.fromString(userId);
            String localUserName = userName.equals("") ? null : userName;
            String localUserStatus = userStatus.equals("") ? null : userStatus;
            String localUserEmail = userEmail.equals("") ? null : userEmail;
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null : LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null : LocalDateTime.parse(endCreationDate, formatter).plusDays(1);

            long countUsers = userRepository.getCountUserFiltered(localUserId, localUserName, localUserStatus, localUserEmail, localStartCreationDate, localEndCreationDate);

            return countUsers;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.getCountUserFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public boolean setUserAdmin(UUID userId, UUID actionUserId) {

        boolean val = false;

        try {
            tUser associatedUser = userRepository.findById(userId);
            tUser actionUser = userRepository.findById(actionUserId);

            if (associatedUser != null && actionUser != null) {

                tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(tUserType.UserTypeEnum.ADMIN.name());
                long nRole = userRoleRepository.countByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());

                if (nRole == 0) {

                    tUserRole role = new tUserRole();
                    role.setUser(associatedUser);
                    role.setUserType(userTypeVal);

                    tUserRole saved = userRoleRepository.saveUserRole(role);
                    userLogRepository.insertUserLog(actionUser, associatedUser, "ADD_ADMIN_ROLE", "");


                    if (saved != null) val = true;

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userIsAdmin.label, ""));
                }

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.setAdminRole.label, ex.getMessage()));
        }

        return val;
    }

    @Override
    public boolean removeUserAdmin(UUID userId, UUID actionUserId) {
        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);
            tUser user = userRepository.findById(userId);

            if (actionUser != null && user != null) {

                tUserType userTypeVal = userTypeRepository.findUserTypeByDescription(tUserType.UserTypeEnum.ADMIN.name());

                long nRole = userRoleRepository.countByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());

                if (nRole > 0) {

                    tUserRole role = userRoleRepository.findByUserIdAndUserTypeId(userId, userTypeVal.getUserTypeId());
                    userRoleRepository.deleteUserRoleById(role.getUserRoleId());
                    userLogRepository.insertUserLog(actionUser, user, "REMOVE_ADMIN_ROLE", "");
                    val = true;

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.roleNotExist.label, ""));
                }


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.UserGeneralRepoFault.removeAdminRole.label, ex.getMessage()));
        }

        return val;
    }

}

