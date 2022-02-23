package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceLog;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class tDeviceLogRepository {

    @Autowired
    ItDeviceLogRepository repository;

    public tDeviceLog saveDeviceLog(tDeviceLog deviceLog) {
        return repository.save(deviceLog);
    }

    public void insertDeviceLog(tUser actionUser, tDevice altered, String action, String detail) {

        tDeviceLog log = new tDeviceLog();
        log.setUser(actionUser);
        log.setDevice(altered);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveDeviceLog(log);
    }

    @Transactional
    public void deleteDeviceLogByDeviceId(UUID deviceId) {

        repository.deleteByDevice_DeviceId(deviceId);
    }
}
