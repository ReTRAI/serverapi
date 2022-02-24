package com.mob.serverapi.utils;


import com.mob.serverapi.device.base.Device;
import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.base.ResellerAssociation;
import com.mob.serverapi.reseller.base.ResellerBalance;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerBalance;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public  class DeviceUtils {

    public static Device transformDevice(tDevice device){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Device r = new Device();
        r.setDeviceId(device.getDeviceId().toString());

        if(device.getReseller()!= null)
            r.setResellerId(device.getReseller().getResellerId().toString());

        r.setDeviceStatus(device.getDeviceStatus().getDescription());
        r.setBrand(device.getBrand());
        r.setSerialNumber(device.getSerialNumber());
        r.setImeiNumber(device.getImeiNumber());
        r.setSimNumber(device.getSimNumber());
        r.setAndroidId(device.getAndroidId());
        r.setFirstSimNumber(device.getFirstSimNumber());
        r.setCreationDate(device.getCreationDate().format(formatter));

        if(device.getLastConnection()!= null)
            r.setLastConnection(device.getLastConnection().format(formatter));
        if(device.getLastBackup()!= null)
            r.setLastBackup(device.getLastBackup().format(formatter));
        if(device.getActivationDate()!= null)
            r.setActivationDate(device.getActivationDate().format(formatter));
        if(device.getExpirationDate()!= null)
            r.setExpireDate(device.getExpirationDate().format(formatter));
        r.setCurrentBalance(device.getCurrentBalance());
        r.setCurrentMinutes(device.getCurrentMinutes());
        r.setNotes(device.getNotes());
        if(device.getOsVersion()!= null)
            r.setOsVersion(device.getOsVersion());

        return r;
    }

    public static List<Device> transformDeviceList(List<tDevice> device){

        List<Device> rs= new ArrayList<Device>();

        for (tDevice r: device) {
            Device newDevice = transformDevice(r);
            rs.add(newDevice);
        }

        return rs;
    }

}
