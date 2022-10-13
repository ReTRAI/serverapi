package com.mob.serverapi.notification.repositories.endpoints;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceNotification;
import com.mob.serverapi.device.repositories.database.tDeviceLogRepository;
import com.mob.serverapi.device.repositories.database.tDeviceNotificationRepository;
import com.mob.serverapi.device.repositories.database.tDeviceRepository;
import com.mob.serverapi.notification.base.DeviceNotification;
import com.mob.serverapi.notification.base.UserNotification;
import com.mob.serverapi.servicefault.FaultMapping;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.base.User;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserNotification;
import com.mob.serverapi.users.repositories.database.tUserLogRepository;
import com.mob.serverapi.users.repositories.database.tUserNotificationRepository;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.utils.NotificationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class NotificationRepository implements INotificationRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tDeviceNotificationRepository deviceNotificationRepository = new tDeviceNotificationRepository();
    @Autowired
    protected tUserNotificationRepository userNotificationRepository = new tUserNotificationRepository();
    @Autowired
    protected tDeviceRepository deviceRepository = new tDeviceRepository();
    @Autowired
    protected tDeviceLogRepository deviceLogRepository = new tDeviceLogRepository();
    @Autowired
    protected tUserRepository userRepository = new tUserRepository();
    @Autowired
    protected tUserLogRepository userLogRepository = new tUserLogRepository();

    @Override
    public DeviceNotification setDeviceNotification(UUID deviceId, String detail,String info,  UUID actionUserId) {

        DeviceNotification dn = new DeviceNotification();
        try {

            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                tDevice u = deviceRepository.findById(deviceId);

                if (u != null) {

                    tDeviceNotification deviceNotification = new tDeviceNotification();
                    deviceNotification.setDevice(u);
                    deviceNotification.setDetail(detail);
                    deviceNotification.setCreationDate(LocalDateTime.now());
                    deviceNotification.setChecked(false);
                    deviceNotification.setInfo(info);

                    tDeviceNotification saved = deviceNotificationRepository.saveDeviceNotification(deviceNotification);

                    if (saved != null) {
                        deviceLogRepository.insertDeviceLog(actionUser, u, "CREATE DEVICE NOTIFICATION", "DEVICE ID: "
                                + saved.getDevice().getDeviceId());

                        dn = NotificationUtils.transformDeviceNotification(saved);

                    }

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setDeviceNotification.label, ex.getMessage()));
        }
        return dn;
    }

    @Override
    public DeviceNotification setDeviceNotificationChecked(UUID deviceNotificationId, UUID actionUserId) {

        DeviceNotification dn = new DeviceNotification();
        try {

            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                tDeviceNotification deviceNotification = deviceNotificationRepository.findById(deviceNotificationId);

                if(deviceNotification!= null){

                    deviceNotification.setChecked(true);
                    deviceNotification.setCheckedDate(LocalDateTime.now());

                    tDeviceNotification saved = deviceNotificationRepository.saveDeviceNotification(deviceNotification);

                    if(saved!=null){
                        deviceLogRepository.insertDeviceLog(actionUser, saved.getDevice(), "CHECKED DEVICE NOTIFICATION", "DEVICE ID: "
                                + saved.getDevice().getDeviceId() + " DEVICE NOTIFICATION ID: " + saved.getDeviceNotificationId());

                        dn = NotificationUtils.transformDeviceNotification(saved);
                    }


                }else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.notificationNotExist.label, ""));
                }

            }else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setDeviceNotificationChecked.label, ex.getMessage()));
        }

        return dn;
    }

    @Override
    public List<DeviceNotification> getDeviceNotificationFiltered(@Nullable String deviceId, @Nullable String startCreationDate,
                                                                  @Nullable String endCreationDate, @Nullable String checked,
                                                                  @Nullable String startCheckedDate, @Nullable String endCheckedDate,
                                                                  @Nullable String field, @Nullable String orderField,
                                                                  int offset, int numberRecords) {

        List<DeviceNotification> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localDeviceId = deviceId.equals("") ? null : UUID.fromString(deviceId);
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter);
            String localChecked = checked.equals("") ? null : checked;
            LocalDateTime localStartCheckedDate = startCheckedDate.equals("") ? null :
                    LocalDateTime.parse(startCheckedDate, formatter);
            LocalDateTime localEndCheckedDate = endCheckedDate.equals("") ? null :
                    LocalDateTime.parse(endCheckedDate, formatter);


            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;

            List<tDeviceNotification> listNotification = deviceNotificationRepository.getDeviceNotificationFiltered(localDeviceId,
                    localStartCreationDate, localEndCreationDate, localChecked, localStartCheckedDate, localEndCheckedDate, localField,
                    localOrderField, offset, numberRecords);

            if (listNotification != null)
                returnList = NotificationUtils.transformDeviceNotificationList(listNotification);


        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getDeviceNotificationFiltered.label, ex.getMessage()));
        }

        return returnList;
    }

    @Override
    public long getCountDeviceNotificationFiltered(@Nullable String deviceId, @Nullable String startCreationDate,
                                                   @Nullable String endCreationDate, @Nullable String checked,
                                                   @Nullable String startCheckedDate, @Nullable String endCheckedDate) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localDeviceId = deviceId.equals("") ? null : UUID.fromString(deviceId);
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter);
            String localChecked = checked.equals("") ? null : checked;
            LocalDateTime localStartCheckedDate = startCheckedDate.equals("") ? null :
                    LocalDateTime.parse(startCheckedDate, formatter);
            LocalDateTime localEndCheckedDate = endCheckedDate.equals("") ? null :
                    LocalDateTime.parse(endCheckedDate, formatter);


            long countNotification = deviceNotificationRepository.getCountDeviceFiltered(localDeviceId,
                    localStartCreationDate, localEndCreationDate, localChecked, localStartCheckedDate, localEndCheckedDate);

            return countNotification;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getCountDeviceNotificationFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public UserNotification setUserNotification(UUID userId, String detail, String info, UUID actionUserId) {

        UserNotification dn = new UserNotification();
        try {

            tUser actionUser = userRepository.findById(actionUserId);
            tUser u = userRepository.findById(userId);

            if (actionUser != null && u != null) {

                tUserNotification userNotification = new tUserNotification();
                userNotification.setUser(u);
                userNotification.setDetail(detail);
                userNotification.setCreationDate(LocalDateTime.now());
                userNotification.setChecked(false);
                userNotification.setInfo(info);


                tUserNotification saved = userNotificationRepository.saveUserNotification(userNotification);

                if (saved != null) {
                    userLogRepository.insertUserLog(actionUser, u, "CREATE USER NOTIFICATION", "USER ID: "
                            + saved.getUser().getUserId());

                    dn = NotificationUtils.transformUserNotification(saved);

                }

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setUserNotification.label, ex.getMessage()));
        }
        return dn;
    }

    @Override
    public UserNotification setUserNotificationChecked(UUID userNotificationId, UUID actionUserId) {

        UserNotification un = new UserNotification();
        try {

            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                tUserNotification userNotification = userNotificationRepository.findById(userNotificationId);

                if(userNotification!= null){

                    userNotification.setChecked(true);
                    userNotification.setCheckedDate(LocalDateTime.now());

                    tUserNotification saved = userNotificationRepository.saveUserNotification(userNotification);

                    if(saved!=null){
                        userLogRepository.insertUserLog(actionUser, saved.getUser(), "CHECKED USER NOTIFICATION", "USER ID: "
                                + saved.getUser().getUserId() + " USER NOTIFICATION ID: " + saved.getUserNotificationId());

                        un = NotificationUtils.transformUserNotification(saved);
                    }


                }else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.notificationNotExist.label, ""));
                }

            }else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setUserNotificationChecked.label, ex.getMessage()));
        }

        return un;
    }

    @Override
    public List<UserNotification> getUserNotificationFiltered(@Nullable String userId, @Nullable String startCreationDate,
                                                              @Nullable String endCreationDate, @Nullable String checked,
                                                              @Nullable String startCheckedDate, @Nullable String endCheckedDate,
                                                              @Nullable String field, @Nullable String orderField,
                                                              int offset, int numberRecords) {

        List<UserNotification> returnList = new ArrayList<>();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localUserId = userId.equals("") ? null : UUID.fromString(userId);
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter);
            String localChecked = checked.equals("") ? null : checked;
            LocalDateTime localStartCheckedDate = startCheckedDate.equals("") ? null :
                    LocalDateTime.parse(startCheckedDate, formatter);
            LocalDateTime localEndCheckedDate = endCheckedDate.equals("") ? null :
                    LocalDateTime.parse(endCheckedDate, formatter);


            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;

            List<tUserNotification> listNotification = userNotificationRepository.getUserNotificationFiltered(localUserId,
                    localStartCreationDate, localEndCreationDate, localChecked, localStartCheckedDate, localEndCheckedDate, localField,
                    localOrderField, offset, numberRecords);

            if (listNotification != null)
                returnList = NotificationUtils.transformUserNotificationList(listNotification);

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getUserNotificationFiltered.label, ex.getMessage()));
        }

        return returnList;
    }

    @Override
    public long getCountUserNotificationFiltered(@Nullable String userId, @Nullable String startCreationDate,
                                                 @Nullable String endCreationDate, @Nullable String checked,
                                                 @Nullable String startCheckedDate, @Nullable String endCheckedDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            UUID localUserId = userId.equals("") ? null : UUID.fromString(userId);
            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter);
            String localChecked = checked.equals("") ? null : checked;
            LocalDateTime localStartCheckedDate = startCheckedDate.equals("") ? null :
                    LocalDateTime.parse(startCheckedDate, formatter);
            LocalDateTime localEndCheckedDate = endCheckedDate.equals("") ? null :
                    LocalDateTime.parse(endCheckedDate, formatter);


            long listNotification = userNotificationRepository.getCountUserFiltered(localUserId,
                    localStartCreationDate, localEndCreationDate, localChecked, localStartCheckedDate, localEndCheckedDate);


            return listNotification;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getCountUserNotificationFiltered.label, ex.getMessage()));
        }
    }
}

