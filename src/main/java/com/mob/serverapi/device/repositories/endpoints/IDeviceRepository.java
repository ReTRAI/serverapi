package com.mob.serverapi.device.repositories.endpoints;


import com.mob.serverapi.device.base.Device;
import com.mob.serverapi.device.base.DeviceBalance;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface IDeviceRepository {

    Device getDeviceById(UUID deviceId);

    Device setDevice(String brand, String model, String serialNumber, String imeiNumber, String simNumber,
                     String androidId, UUID actionUserId);

    List<Device> setDeviceList(List<Device> deviceList, UUID actionUserId);


    List<Device> getDevicesFiltered(@Nullable String deviceId, @Nullable String resellerId,
                                    @Nullable String status, @Nullable String startCreationDate,
                                    @Nullable String endCreationDate, @Nullable String startActivationDate,
                                    @Nullable String endActivationDate, @Nullable String startExpirationDate,
                                    @Nullable String endExpirationDate, @Nullable String field,
                                    @Nullable String orderField, int offset, int numberRecords);

    long getCountDevicesFiltered(@Nullable String deviceId, @Nullable String resellerId,
                                 @Nullable String status, @Nullable String startCreationDate,
                                 @Nullable String endCreationDate, @Nullable String startActivationDate,
                                 @Nullable String endActivationDate, @Nullable String startExpirationDate,
                                 @Nullable String endExpirationDate);

    Device assignDevice(UUID deviceId, UUID resellerId, UUID actionUserId);

    Device activateDevice(UUID deviceId, String ownerNickname, UUID actionUserId);

    Device blockDevice(UUID deviceId, UUID actionUserId);

    Device wipeDevice(UUID deviceId, UUID actionUserId);

    Device suspendDevice(UUID deviceId, UUID actionUserId);

    Device setDeviceNotes(UUID deviceId, String notes, UUID actionUserId);

    boolean setDeviceBalanceMovement(UUID deviceId, String debitCredit, float movementValue, UUID actionUserId);

    List<DeviceBalance> getDeviceBalanceMovements(UUID deviceId, @Nullable String startMovementDate,
                                                  @Nullable String endMovementDate, @Nullable String minValue,
                                                  @Nullable String maxValue, @Nullable String debitCredit,
                                                  @Nullable String field, @Nullable String orderField,
                                                  int offset, int numberRecords);

    long getCountDeviceBalanceMovements(UUID deviceId, @Nullable String startMovementDate,
                                   @Nullable String endMovementDate, @Nullable String minValue,
                                   @Nullable String maxValue, @Nullable String debitCredit);
}
