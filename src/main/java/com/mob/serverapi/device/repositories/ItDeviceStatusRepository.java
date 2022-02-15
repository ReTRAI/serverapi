package com.mob.serverapi.device.repositories;

import com.mob.serverapi.device.database.tDeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

interface ItDeviceStatusRepository extends JpaRepository<tDeviceStatus,Integer> {
}
