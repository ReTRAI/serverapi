package com.mob.serverapi.device.repositories.database;


import com.mob.serverapi.device.database.tDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class tDeviceRepository {

    @Autowired
    ItDeviceRepository repository;

    public tDevice saveDevice(tDevice device) {
       return repository.save(device);
    }

    public List<tDevice> getAllDevicesByResellerId(UUID resellerId){

        return repository.findByReseller_ResellerId(resellerId);
    }

    public tDevice findById (UUID deviceId){

        return repository.findById(deviceId).orElse(null);
    }

    public boolean existSerialNumber(String serialNumber)
    {
        return repository.existsBySerialNumber(serialNumber);
    }

    public boolean existImeiNumber(String imeiNumber)
    {
        return repository.existsByImeiNumber(imeiNumber);
    }
    public boolean existSimNumber(String simNumber)
    {
        return repository.existsBySimNumber(simNumber);
    }
    public boolean existAndroidId(String androidId)
    {
        return repository.existsByAndroidId(androidId);
    }
}
