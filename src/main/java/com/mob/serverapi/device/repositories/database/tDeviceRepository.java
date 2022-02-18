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

    public void saveDevice(tDevice device) {
        repository.save(device);
    }

    public List<tDevice> getAllDevicesByResellerId(UUID resellerId){

        return repository.findByReseller_ResellerId(resellerId);
    }

    public tDevice findById (UUID deviceId){

        return repository.findById(deviceId).orElse(null);
    }
}
