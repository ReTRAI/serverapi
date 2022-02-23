package com.mob.serverapi.device.repositories.endpoints;

import com.mob.serverapi.device.base.Device;
import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceStatus;
import com.mob.serverapi.device.repositories.database.tDeviceLogRepository;
import com.mob.serverapi.device.repositories.database.tDeviceRepository;
import com.mob.serverapi.device.repositories.database.tDeviceStatusRepository;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.utils.DeviceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.UUID;


public class DeviceRepository implements IDeviceRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tDeviceRepository deviceRepository = new tDeviceRepository();
    @Autowired
    protected tDeviceLogRepository deviceLogRepository = new tDeviceLogRepository();
    @Autowired
    protected tDeviceStatusRepository deviceStatusRepository = new tDeviceStatusRepository();
    @Autowired
    protected tUserRepository userRepository = new tUserRepository();

    @Override
    public Device getDeviceById(UUID deviceId) {
        Device deviceToReturn = new Device();

        try {
            tDevice u = deviceRepository.findById(deviceId);

            if (u != null) {
                deviceToReturn = DeviceUtils.transformDevice(u);
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("DEVICE_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_DEVICE_BY_ID", ex.getMessage()));
        }

        return deviceToReturn;
    }

    @Override
    public Device setDevice(String brand, String serialNumber, String imeiNumber, String simNumber, String androidId,
                            UUID actionUserId) {
        Device deviceToReturn = new Device();

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                if (!deviceRepository.existSerialNumber(serialNumber)) {
                    if (!deviceRepository.existImeiNumber(imeiNumber)) {
                        if (!deviceRepository.existSimNumber(simNumber)) {
                            if (!deviceRepository.existAndroidId(androidId)) {

                                tDeviceStatus deviceStatusVal = deviceStatusRepository.findDeviceStatusByDescription(
                                        tDeviceStatus.DeviceStatusEnum.UNASSIGNED.name());

                                tDevice device = new tDevice();
                                device.setDeviceStatus(deviceStatusVal);
                                device.setBrand(brand);
                                device.setSerialNumber(serialNumber);
                                device.setImeiNumber(imeiNumber);
                                device.setSimNumber(simNumber);
                                device.setAndroidId(androidId);
                                device.setFirstSimNumber(simNumber);
                                device.setCreationDate(LocalDateTime.now());

                                tDevice saved = deviceRepository.saveDevice(device);

                                if (saved != null) {

                                    deviceToReturn = DeviceUtils.transformDevice(saved);

                                    deviceLogRepository.insertDeviceLog(actionUser, saved, "DEVICE CREATED", "DEVICE ID: "
                                            + saved.getDeviceId());

                                } else {
                                    throw new ServiceFaultException("ERROR", new ServiceFault("CANT_SAVE_DEVICE", ""));
                                }
                            } else {
                                throw new ServiceFaultException("ERROR", new ServiceFault("ANDROIDID_EXISTS", ""));
                            }
                        } else {
                            throw new ServiceFaultException("ERROR", new ServiceFault("SIM_NUMBER_EXISTS", ""));
                        }
                    } else {
                        throw new ServiceFaultException("ERROR", new ServiceFault("IMEI_EXISTS", ""));
                    }
                } else {
                    throw new ServiceFaultException("ERROR", new ServiceFault("SERIAL_NUMBER_EXISTS", ""));
                }
            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_DEVICE", ex.getMessage()));
        }

        return deviceToReturn;
    }
}

