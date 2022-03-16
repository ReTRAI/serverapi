package com.mob.serverapi.notification.repositories.endpoints;

import com.mob.serverapi.notification.base.DeviceNotification;
import com.mob.serverapi.notification.base.UserNotification;
import com.mob.serverapi.servicefault.FaultMapping;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;


public class NotificationRepository implements INotificationRepository {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public DeviceNotification setDeviceNotification(UUID deviceId, String detail, UUID actionUserId) {
        try {


            return null;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setDeviceNotification.label, ex.getMessage()));
        }
    }

    @Override
    public DeviceNotification setDeviceNotificationChecked(UUID deviceNotificationId, UUID actionUserId) {
        try {

            return null;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setDeviceNotificationChecked.label, ex.getMessage()));
        }
    }

    @Override
    public List<DeviceNotification> getDeviceNotificationFiltered(String deviceId, String startCreationDate, String endCreationDate, String checked, String startCheckedDate, String endCheckedDate, String field, String orderField, int offset, int numberRecords) {

        try {

            return null;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getDeviceNotificationFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public long getCountDeviceNotificationFiltered(String deviceId, String startCreationDate, String endCreationDate, String checked, String startCheckedDate, String endCheckedDate) {
        try {

            return 0;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getCountDeviceNotificationFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public UserNotification setUserNotification(UUID userId, String detail, UUID actionUserId) {
        try {

            return null;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setUserNotification.label, ex.getMessage()));
        }
    }

    @Override
    public UserNotification setUserNotificationChecked(UUID userNotificationId, UUID actionUserId) {
        try {

            return null;
        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.setUserNotificationChecked.label, ex.getMessage()));
        }
    }

    @Override
    public List<UserNotification> getUserNotificationFiltered(String userId, String startCreationDate, String endCreationDate, String checked, String startCheckedDate, String endCheckedDate, String field, String orderField, int offset, int numberRecords) {
        try {
            return null;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getUserNotificationFiltered.label, ex.getMessage()));
        }

    }

    @Override
    public long getCountUserNotificationFiltered(String userId, String startCreationDate, String endCreationDate, String checked, String startCheckedDate, String endCheckedDate) {
        try {
            return 0;

        } catch (
                ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label,
                    new ServiceFault(FaultMapping.NotificationsGeneralRepoFault.getCountUserNotificationFiltered.label, ex.getMessage()));
        }
    }
}

