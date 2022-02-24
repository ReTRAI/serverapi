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
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
    public Device setDevice(String brand, String model, String serialNumber, String imeiNumber, String simNumber, String androidId,
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
                                device.setModel(model);
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

    @Override
    public List<Device> setDeviceList(List<Device> deviceList, UUID actionUserId) {

        List<Device> devicesInserted = new ArrayList<>();
        try {

            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {
                for (Device d : deviceList) {

                    Device deviceSaved = setDevice(d.getBrand(), d.getModel(), d.getSerialNumber(),
                            d.getImeiNumber(), d.getSimNumber(), d.getAndroidId(), actionUserId);

                    if (deviceSaved != null)
                        devicesInserted.add(deviceSaved);
                }

            } else {
                throw new ServiceFaultException("ERROR", new ServiceFault("USER_DONT_EXIST", ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("SET_DEVICE", ex.getMessage()));
        }

        return devicesInserted;
    }

    @Override
    public List<Device> getDevicesFiltered(@Nullable String deviceId, @Nullable String resellerId,
                                           @Nullable String status, @Nullable String startCreationDate,
                                           @Nullable String endCreationDate, @Nullable String startActivationDate,
                                           @Nullable String endActivationDate, @Nullable String startExpirationDate,
                                           @Nullable String endExpirationDate, @Nullable String field,
                                           @Nullable String orderField, int offset, int numberRecords) {
        List<Device> returnList = new ArrayList<>();

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


            UUID localDeviceId = deviceId.equals("") ? null : UUID.fromString(deviceId);
            UUID localResellerId = resellerId.equals("") ? null : UUID.fromString(resellerId);
            String localDeviceStatus = status.equals("") ? null : status;

            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter).plusDays(1);
            LocalDateTime localStartActivationDate = startActivationDate.equals("") ? null :
                    LocalDateTime.parse(startActivationDate, formatter);
            LocalDateTime localEndActivationDate = endActivationDate.equals("") ? null :
                    LocalDateTime.parse(endActivationDate, formatter).plusDays(1);
            LocalDateTime localStartExpirationDate = startExpirationDate.equals("") ? null :
                    LocalDateTime.parse(startExpirationDate, formatter);
            LocalDateTime localEndExpirationDate = endExpirationDate.equals("") ? null :
                    LocalDateTime.parse(endExpirationDate, formatter).plusDays(1);

            String localField = field.equals("") ? null : field;
            String localOrderField = orderField.equals("") ? null : orderField;

            List<tDevice> devices = deviceRepository.getDevicesFiltered(localDeviceId, localResellerId, localDeviceStatus, localStartCreationDate,
                    localEndCreationDate, localStartActivationDate, localEndActivationDate, localStartExpirationDate, localEndExpirationDate,
                    field, orderField, offset, numberRecords);

            if (devices != null) {
                returnList = DeviceUtils.transformDeviceList(devices);

            } else {
                throw new ServiceFaultException("WARNING", new ServiceFault("EMPTY_DEVICE_LIST", ""));
            }


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_DEVICES_FILTERED", ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountDevicesFiltered(@Nullable String deviceId, @Nullable String resellerId,
                                        @Nullable String status, @Nullable String startCreationDate,
                                        @Nullable String endCreationDate, @Nullable String startActivationDate,
                                        @Nullable String endActivationDate, @Nullable String startExpirationDate,
                                        @Nullable String endExpirationDate) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


            UUID localDeviceId = deviceId.equals("") ? null : UUID.fromString(deviceId);
            UUID localResellerId = resellerId.equals("") ? null : UUID.fromString(resellerId);
            String localDeviceStatus = status.equals("") ? null : status;

            LocalDateTime localStartCreationDate = startCreationDate.equals("") ? null :
                    LocalDateTime.parse(startCreationDate, formatter);
            LocalDateTime localEndCreationDate = endCreationDate.equals("") ? null :
                    LocalDateTime.parse(endCreationDate, formatter).plusDays(1);
            LocalDateTime localStartActivationDate = startActivationDate.equals("") ? null :
                    LocalDateTime.parse(startActivationDate, formatter);
            LocalDateTime localEndActivationDate = endActivationDate.equals("") ? null :
                    LocalDateTime.parse(endActivationDate, formatter).plusDays(1);
            LocalDateTime localStartExpirationDate = startExpirationDate.equals("") ? null :
                    LocalDateTime.parse(startExpirationDate, formatter);
            LocalDateTime localEndExpirationDate = endExpirationDate.equals("") ? null :
                    LocalDateTime.parse(endExpirationDate, formatter).plusDays(1);


            long countDevices = deviceRepository.getCountDevicesFiltered(localDeviceId, localResellerId, localDeviceStatus, localStartCreationDate,
                    localEndCreationDate, localStartActivationDate, localEndActivationDate, localStartExpirationDate, localEndExpirationDate);

            return countDevices;

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException("ERROR", new ServiceFault("GET_DEVICES_FILTERED", ex.getMessage()));
        }
    }
}

