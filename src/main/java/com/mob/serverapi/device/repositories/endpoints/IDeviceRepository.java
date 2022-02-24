package com.mob.serverapi.device.repositories.endpoints;


import com.mob.serverapi.device.base.Device;
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
}
