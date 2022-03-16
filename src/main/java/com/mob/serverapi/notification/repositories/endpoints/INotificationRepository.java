package com.mob.serverapi.notification.repositories.endpoints;


import com.mob.serverapi.notification.base.DeviceNotification;
import com.mob.serverapi.notification.base.UserNotification;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface INotificationRepository {


    DeviceNotification setDeviceNotification(UUID deviceId, String detail, UUID actionUserId);

    DeviceNotification setDeviceNotificationChecked(UUID deviceNotificationId, UUID actionUserId);

    List<DeviceNotification> getDeviceNotificationFiltered(@Nullable String deviceId, @Nullable String startCreationDate,
                                                            @Nullable String endCreationDate, @Nullable String checked,
                                                            @Nullable String startCheckedDate, @Nullable String endCheckedDate,
                                                            @Nullable String field, @Nullable String orderField,
                                                            int offset, int numberRecords);

    long getCountDeviceNotificationFiltered(@Nullable String deviceId, @Nullable String startCreationDate,
                                                            @Nullable String endCreationDate, @Nullable String checked,
                                                            @Nullable String startCheckedDate, @Nullable String endCheckedDate);

    UserNotification setUserNotification(UUID userId, String detail, UUID actionUserId);

    UserNotification setUserNotificationChecked(UUID userNotificationId, UUID actionUserId);

    List<UserNotification> getUserNotificationFiltered(@Nullable String userId, @Nullable String startCreationDate,
                                                       @Nullable String endCreationDate, @Nullable String checked,
                                                       @Nullable String startCheckedDate, @Nullable String endCheckedDate,
                                                       @Nullable String field, @Nullable String orderField,
                                                       int offset, int numberRecords);

    long getCountUserNotificationFiltered(@Nullable String userId, @Nullable String startCreationDate,
                                            @Nullable String endCreationDate, @Nullable String checked,
                                            @Nullable String startCheckedDate, @Nullable String endCheckedDate);
}
