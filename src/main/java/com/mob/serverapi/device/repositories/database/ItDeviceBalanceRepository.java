package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDeviceBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItDeviceBalanceRepository extends JpaRepository<tDeviceBalance, UUID> {

    List<tDeviceBalance> findByDevice_DeviceIdOrderByMovementDateDesc(UUID deviceId);


}
