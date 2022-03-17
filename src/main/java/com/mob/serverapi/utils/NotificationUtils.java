package com.mob.serverapi.utils;


import com.mob.serverapi.device.database.tDeviceNotification;
import com.mob.serverapi.notification.base.DeviceNotification;
import com.mob.serverapi.notification.base.UserNotification;
import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.base.ResellerAssociation;
import com.mob.serverapi.reseller.base.ResellerBalance;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerBalance;
import com.mob.serverapi.users.database.tUserNotification;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public  class NotificationUtils {



    public static DeviceNotification transformDeviceNotification(tDeviceNotification deviceNotification){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        DeviceNotification r = new DeviceNotification();
        r.setDeviceNotificationId(deviceNotification.getDeviceNotificationId().toString());
        r.setCreationDate(deviceNotification.getCreationDate().format(formatter));

        r.setDeviceId(deviceNotification.getDevice().getDeviceId().toString());
        r.setDetail(deviceNotification.getDetail());

        if(deviceNotification.getCheckedDate() != null)
            r.setCheckedDate(deviceNotification.getCheckedDate().format(formatter));

        r.setChecked(deviceNotification.isChecked());

        return r;
    }

    public static List<DeviceNotification> transformDeviceNotificationList(List<tDeviceNotification> deviceNotification){

        List<DeviceNotification> rs= new ArrayList<DeviceNotification>();

        for (tDeviceNotification r: deviceNotification) {
            DeviceNotification newDeviceNotification = transformDeviceNotification(r);
            rs.add(newDeviceNotification);
        }

        return rs;
    }

    public static UserNotification transformUserNotification(tUserNotification userNotification){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        UserNotification r = new UserNotification();
        r.setUserNotificationId(userNotification.getUserNotificationId().toString());
        r.setCreationDate(userNotification.getCreationDate().format(formatter));
        r.setChecked(userNotification.isChecked());
        r.setUserId(userNotification.getUser().getUserId().toString());
        r.setDetail(userNotification.getDetail());


        if(userNotification.getCheckedDate() != null)
            r.setCheckedDate(userNotification.getCheckedDate().format(formatter));

        return r;
    }

    public static List<UserNotification> transformUserNotificationList(List<tUserNotification> userNotification){

        List<UserNotification> rs= new ArrayList<UserNotification>();

        for (tUserNotification r: userNotification) {
            UserNotification newUserNotification = transformUserNotification(r);
            rs.add(newUserNotification);
        }

        return rs;
    }


}
