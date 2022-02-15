package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ItDeviceRepository extends JpaRepository<tDevice, Integer> {

    List<tDevice> findByReseller_ResellerId(int resellerId);

}
