package com.mob.serverapi.device.repositories.endpoints;


import com.mob.serverapi.device.base.Device;

import java.util.List;
import java.util.UUID;

interface IDeviceRepository {

    Device getDeviceById(UUID deviceId);

    Device setDevice(String brand, String model, String serialNumber, String imeiNumber,String simNumber,
                     String androidId, UUID actionUserId);

    List<Device> setDeviceList(List<Device> deviceList, UUID actionUserId);
}
