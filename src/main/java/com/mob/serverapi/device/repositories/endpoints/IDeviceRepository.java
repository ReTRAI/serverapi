package com.mob.serverapi.device.repositories.endpoints;


import com.mob.serverapi.device.base.Device;
import java.util.UUID;

interface IDeviceRepository {

    Device getDeviceById(UUID deviceId);

    Device setDevice(String brand, String serialNumber, String imeiNumber,String simNumber,
                     String androidId, UUID actionUserId);
}
