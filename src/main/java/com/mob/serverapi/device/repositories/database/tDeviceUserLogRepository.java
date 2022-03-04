package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceLog;
import com.mob.serverapi.device.database.tDeviceUser;
import com.mob.serverapi.device.database.tDeviceUserLog;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class tDeviceUserLogRepository {

    @Autowired
    ItDeviceUserLogRepository repository;

    public tDeviceUserLog saveDeviceUserLog(tDeviceUserLog deviceUserLog) {
        return repository.save(deviceUserLog);
    }

    public void insertDeviceUserLog(tUser actionUser, tDeviceUser altered, String action, String detail) {

        tDeviceUserLog log = new tDeviceUserLog();
        log.setUser(actionUser);
        log.setDeviceUser(altered);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveDeviceUserLog(log);
    }

}
