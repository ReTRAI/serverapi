package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDeviceNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItDeviceNotificationRepository extends JpaRepository<tDeviceNotification, UUID> {

    List<tDeviceNotification> findByDevice_DeviceIdOrderByCreationDateDesc(UUID deviceId);

}
