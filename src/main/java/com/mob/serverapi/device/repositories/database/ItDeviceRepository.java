package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItDeviceRepository extends JpaRepository<tDevice, UUID> {

    List<tDevice> findByReseller_ResellerId(UUID resellerId);

    boolean existsBySerialNumber(String serialNumber);

    boolean existsByImeiNumber(String imeiNumber);

    boolean existsBySimNumber(String simNumber);

    boolean existsByAndroidId(String androidId);

}
