package com.mob.serverapi.device.repositories.database;


import com.mob.serverapi.device.database.tDeviceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class tDeviceUserRepository {

    @Autowired
    ItDeviceUserRepository repository;

    public void saveDeviceUser(tDeviceUser device) {
        repository.save(device);
    }

    public tDeviceUser findByNickname(String nickname){

        return repository.findByNickname(nickname);
    }
}
