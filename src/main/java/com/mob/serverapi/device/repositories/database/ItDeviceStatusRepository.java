package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItDeviceStatusRepository extends JpaRepository<tDeviceStatus, UUID> {

    tDeviceStatus findByDescription(String description);

}
