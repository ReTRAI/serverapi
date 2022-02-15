package com.mob.serverapi.device.repositories.database;


import com.mob.serverapi.device.database.tDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class tDeviceRepository {

    @Autowired
    ItDeviceRepository repository;

    public void saveDevice(tDevice device) {
        repository.save(device);
    }

    public List<tDevice> getAllDevicesByResellerId(int resellerId){

        return repository.findByReseller_ResellerId(resellerId);
    }
}
