package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDeviceUserLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItDeviceUserLogRepository extends JpaRepository<tDeviceUserLog, UUID> {

}
