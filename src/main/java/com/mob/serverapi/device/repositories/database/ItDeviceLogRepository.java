package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

interface ItDeviceLogRepository extends JpaRepository<tDeviceLog, UUID> {

    @Transactional
    long deleteByDevice_DeviceId(UUID deviceId);


}
