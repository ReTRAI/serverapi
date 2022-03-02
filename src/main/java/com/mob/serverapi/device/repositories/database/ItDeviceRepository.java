package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

interface ItDeviceRepository extends JpaRepository<tDevice, UUID> {

    List<tDevice> findByReseller_ResellerId(UUID resellerId);

    boolean existsBySerialNumber(String serialNumber);

    boolean existsByImeiNumber(String imeiNumber);

    boolean existsBySimNumber(String simNumber);

    boolean existsByAndroidId(String androidId);

    long countByReseller_ResellerIdIsInAndDeviceStatusAndActivationDateIsGreaterThan
            (Collection<UUID> resellerIds, tDeviceStatus deviceStatus, LocalDateTime activationDate);

    long countByReseller_ResellerIdIsInAndDeviceStatusAndExpirationDateIsLessThan
            (Collection<UUID> resellerIds, tDeviceStatus deviceStatus, LocalDateTime expirationDate);

    long countByReseller_ResellerIdIsInAndDeviceStatusAndLastRenovationDateIsGreaterThan
            (Collection<UUID> resellerIds, tDeviceStatus deviceStatus, LocalDateTime lastRenovationDate);

    long countByReseller_ResellerIdIsInAndDeviceStatus(Collection<UUID> resellerIds, tDeviceStatus deviceStatus);

    long countByReseller_ResellerIdIsInAndDeviceStatusIsNotIn
            (Collection<UUID> resellerIds, Collection<tDeviceStatus> deviceStatuses);

    long countByReseller_ResellerIdIsIn(Collection<UUID> resellerIds);





}
