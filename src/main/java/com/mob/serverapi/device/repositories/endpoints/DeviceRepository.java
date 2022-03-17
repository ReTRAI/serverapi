package com.mob.serverapi.device.repositories.endpoints;

import com.mob.serverapi.device.base.Device;
import com.mob.serverapi.device.base.DeviceBalance;
import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceBalance;
import com.mob.serverapi.device.database.tDeviceStatus;
import com.mob.serverapi.device.database.tDeviceUser;
import com.mob.serverapi.device.repositories.database.*;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.repositories.database.tResellerRepository;
import com.mob.serverapi.servicefault.FaultMapping;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.utils.DeviceUtils;
import com.mob.serverapi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    @Autowired
    protected tResellerRepository resellerRepository = new tResellerRepository();
    @Autowired
    protected tDeviceUserRepository deviceUserRepository = new tDeviceUserRepository();
    @Autowired
    protected tDeviceUserLogRepository deviceUserLogRepository = new tDeviceUserLogRepository();
    @Autowired
    protected tDeviceBalanceRepository deviceBalanceRepository = new tDeviceBalanceRepository();

    @Override
    public Device getDeviceById(UUID deviceId) {
        Device deviceToReturn = new Device();

        try {
            tDevice u = deviceRepository.findById(deviceId);

            if (u != null) {
                deviceToReturn = DeviceUtils.transformDevice(u);
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.getDeviceById.label, ex.getMessage()));
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

                                }
                            } else {
                                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.androidIdExist.label, ""));
                            }
                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.simNumberExist.label, ""));
                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.imeiExist.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.serialNumberExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.setDevice.label, ex.getMessage()));
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
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.setDeviceList.label, ex.getMessage()));
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

            if (devices != null)
                returnList = DeviceUtils.transformDeviceList(devices);


        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.getDeviceFiltered.label, ex.getMessage()));
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
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.getCountDeviceFiltered.label, ex.getMessage()));
        }
    }

    @Override
    public Device assignDevice(UUID deviceId, UUID resellerId, UUID actionUserId) {

        Device device = new Device();

        try {

            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                tDevice deviceVal = deviceRepository.findById((deviceId));
                if (deviceVal != null) {

                    tDeviceStatus deviceStatusVal = deviceStatusRepository.findDeviceStatusByDescription(
                            deviceVal.getDeviceStatus().getDescription());

                    if (deviceStatusVal.getDescription().equals(tDeviceStatus.DeviceStatusEnum.UNASSIGNED.name())) {

                        tReseller resellerVal = resellerRepository.findById(resellerId);
                        if (resellerVal != null) {

                            tDeviceStatus deviceStatusFree = deviceStatusRepository.findDeviceStatusByDescription(
                                    tDeviceStatus.DeviceStatusEnum.FREE.name());

                            deviceVal.setReseller(resellerVal);
                            deviceVal.setDeviceStatus(deviceStatusFree);

                            tDevice saved = deviceRepository.saveDevice(deviceVal);

                            if (saved != null) {
                                device = DeviceUtils.transformDevice(saved);
                                deviceLogRepository.insertDeviceLog(actionUser, saved, "DEVICE ASSIGNED TO RESELLER", "DEVICE ID: "
                                        + saved.getDeviceId() + " RESELLER ID: " + resellerVal.getResellerId());

                                resellerVal.setTotalDevices(resellerVal.getTotalDevices() + 1);
                                resellerRepository.saveReseller(resellerVal);

                            }

                        } else {
                            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.inconsistentDeviceStatus.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.assignDevice.label, ex.getMessage()));
        }
        return device;
    }

    @Override
    public Device activateDevice(UUID deviceId, String ownerNickname, UUID actionUserId) {
        Device device = new Device();

        try {

            tUser actionUser = userRepository.findById(actionUserId);
            if (actionUser != null) {

                tDevice deviceVal = deviceRepository.findById((deviceId));
                if (deviceVal != null) {

                    tDeviceStatus deviceStatusVal = deviceStatusRepository.findDeviceStatusByDescription(
                            deviceVal.getDeviceStatus().getDescription());

                    if (deviceStatusVal.getDescription().equals(tDeviceStatus.DeviceStatusEnum.FREE.name())) {


                        tDeviceStatus deviceStatusActive = deviceStatusRepository.findDeviceStatusByDescription(
                                tDeviceStatus.DeviceStatusEnum.ACTIVE.name());

                        deviceVal.setDeviceStatus(deviceStatusActive);
                        deviceVal.setActivationDate(LocalDateTime.now());
                        deviceVal.setExpirationDate(LocalDateTime.now().plusDays(180));

                        tDeviceUser deviceUser = new tDeviceUser();
                        deviceUser.setDevice(deviceVal);
                        deviceUser.setNickname(ownerNickname);
                        deviceUser.setCreationDate(LocalDateTime.now());

                        String password = UserUtils.generateRandomAlphanumericString();
                        byte[] salt = UserUtils.createPasswordSalt();
                        byte[] passwdHash = UserUtils.hashPassword(salt, password);

                        deviceUser.setUserActivationPasswordSalt(salt);
                        deviceUser.setUserActivationPasswordHash(passwdHash);

                        tDeviceUser deviceUserSaved = deviceUserRepository.saveDeviceUser(deviceUser);

                        if (deviceUserSaved != null) {
                            tDevice saved = deviceRepository.saveDevice(deviceVal);

                            if (saved != null) {

                                device = DeviceUtils.transformDevice(saved);
                                deviceLogRepository.insertDeviceLog(actionUser, saved, "ACTIVATE DEVICE", "DEVICE ID: "
                                        + saved.getDeviceId());
                                deviceUserLogRepository.insertDeviceUserLog(actionUser, deviceUserSaved, "CREATE DEVICE USER ",
                                        "DEVICE ID: " + saved.getDeviceId());

                            }
                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.inconsistentDeviceStatus.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.activateDevice.label, ex.getMessage()));
        }
        return device;
    }

    @Override
    public Device blockDevice(UUID deviceId, UUID actionUserId) {
        Device device = new Device();

        try {

            tUser actionUser = userRepository.findById(actionUserId);
            if (actionUser != null) {

                tDevice deviceVal = deviceRepository.findById((deviceId));
                if (deviceVal != null) {

                    tDeviceStatus deviceStatusVal = deviceStatusRepository.findDeviceStatusByDescription(
                            deviceVal.getDeviceStatus().getDescription());

                    if (deviceStatusVal.getDescription().equals(tDeviceStatus.DeviceStatusEnum.ACTIVE.name())) {

                        tDeviceStatus deviceStatusFree = deviceStatusRepository.findDeviceStatusByDescription(
                                tDeviceStatus.DeviceStatusEnum.BLOCKED.name());

                        deviceVal.setDeviceStatus(deviceStatusFree);
                        tDevice saved = deviceRepository.saveDevice(deviceVal);

                        if (saved != null) {
                            device = DeviceUtils.transformDevice(saved);
                            deviceLogRepository.insertDeviceLog(actionUser, saved, "DEVICE BLOCKED", "DEVICE ID: "
                                    + saved.getDeviceId());

                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.inconsistentDeviceStatus.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.blockDevice.label, ex.getMessage()));
        }
        return device;
    }

    @Override
    public Device wipeDevice(UUID deviceId, UUID actionUserId) {
        Device device = new Device();

        try {

            tUser actionUser = userRepository.findById(actionUserId);
            if (actionUser != null) {

                tDevice deviceVal = deviceRepository.findById((deviceId));
                if (deviceVal != null) {

                    tDeviceStatus deviceStatusVal = deviceStatusRepository.findDeviceStatusByDescription(
                            deviceVal.getDeviceStatus().getDescription());

                    if (deviceStatusVal.getDescription().equals(tDeviceStatus.DeviceStatusEnum.ACTIVE.name())) {

                        tDeviceStatus deviceStatusFree = deviceStatusRepository.findDeviceStatusByDescription(
                                tDeviceStatus.DeviceStatusEnum.WIPED.name());

                        deviceVal.setDeviceStatus(deviceStatusFree);
                        tDevice saved = deviceRepository.saveDevice(deviceVal);

                        if (saved != null) {
                            device = DeviceUtils.transformDevice(saved);
                            deviceLogRepository.insertDeviceLog(actionUser, saved, "DEVICE BLOCKED", "DEVICE ID: "
                                    + saved.getDeviceId());

                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.inconsistentDeviceStatus.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.wipeDevice.label, ex.getMessage()));
        }
        return device;
    }

    @Override
    public Device suspendDevice(UUID deviceId, UUID actionUserId) {
        Device device = new Device();

        try {

            tUser actionUser = userRepository.findById(actionUserId);
            if (actionUser != null) {

                tDevice deviceVal = deviceRepository.findById((deviceId));
                if (deviceVal != null) {

                    tDeviceStatus deviceStatusVal = deviceStatusRepository.findDeviceStatusByDescription(
                            deviceVal.getDeviceStatus().getDescription());

                    if (deviceStatusVal.getDescription().equals(tDeviceStatus.DeviceStatusEnum.ACTIVE.name())) {

                        tDeviceStatus deviceStatusFree = deviceStatusRepository.findDeviceStatusByDescription(
                                tDeviceStatus.DeviceStatusEnum.SUSPENDED.name());

                        deviceVal.setDeviceStatus(deviceStatusFree);
                        tDevice saved = deviceRepository.saveDevice(deviceVal);

                        if (saved != null) {
                            device = DeviceUtils.transformDevice(saved);
                            deviceLogRepository.insertDeviceLog(actionUser, saved, "DEVICE BLOCKED", "DEVICE ID: "
                                    + saved.getDeviceId());

                        }
                    } else {
                        throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.inconsistentDeviceStatus.label, ""));
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.suspendDevice.label, ex.getMessage()));
        }
        return device;
    }

    @Override
    public Device setDeviceNotes(UUID deviceId, String notes, UUID actionUserId) {
        Device device = new Device();

        try {

            tUser actionUser = userRepository.findById(actionUserId);
            if (actionUser != null) {

                tDevice deviceVal = deviceRepository.findById((deviceId));
                if (deviceVal != null) {

                    deviceVal.setNotes(notes.trim());
                    tDevice saved = deviceRepository.saveDevice(deviceVal);

                    if (saved != null) {
                        device = DeviceUtils.transformDevice(saved);
                        deviceLogRepository.insertDeviceLog(actionUser, saved, "CHANGE DEVICE NOTES", "DEVICE ID: "
                                + saved.getDeviceId());
                    }
                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.setDeviceNotes.label, ex.getMessage()));
        }
        return device;
    }

    @Override
    public boolean setDeviceBalanceMovement(UUID deviceId, String debitCredit, float movementValue, UUID actionUserId) {

        boolean val = false;

        try {
            tUser actionUser = userRepository.findById(actionUserId);

            if (actionUser != null) {

                LocalDateTime localMovementDate = LocalDateTime.now();

                tDevice u = deviceRepository.findById(deviceId);

                if (u != null) {

                    tDeviceBalance dBalance = new tDeviceBalance();
                    dBalance.setDevice(u);
                    dBalance.setMovementDate(localMovementDate);
                    dBalance.setDebitCredit(debitCredit.toUpperCase());
                    dBalance.setMovementValue(movementValue);

                    deviceBalanceRepository.saveDeviceBalance(dBalance);

                    u.setCurrentBalance(deviceBalanceRepository.getCurrentBalance(deviceId));
                    deviceRepository.saveDevice(u);

                    deviceLogRepository.insertDeviceLog(actionUser, u, "ADD_DEVICE_BALANCE_MOVEMENT", "");

                    val = true;

                } else {
                    throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
                }
            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.userNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.setDeviceBalanceMovement.label, ex.getMessage()));
        }

        return val;

    }

    @Override
    public List<DeviceBalance> getDeviceBalanceMovements(UUID deviceId, @Nullable String startMovementDate,
                                                         @Nullable String endMovementDate, @Nullable String minValue,
                                                         @Nullable String maxValue, @Nullable String debitCredit,
                                                         @Nullable String field, @Nullable String orderField,
                                                         int offset, int numberRecords) {

        List<DeviceBalance> returnList = new ArrayList<>();

        try {
            tDevice u = deviceRepository.findById(deviceId);

            if (u != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                String localMinValue = minValue.equals("") ? null : minValue;
                String localMaxValue = maxValue.equals("") ? null : maxValue;
                String localDebitCredit = debitCredit.equals("") ? null : debitCredit;
                LocalDateTime localStartMovementDate = startMovementDate.equals("") ? null :
                        LocalDateTime.parse(startMovementDate, formatter);
                LocalDateTime localEndMovementDate = endMovementDate.equals("") ? null :
                        LocalDateTime.parse(endMovementDate, formatter).plusDays(1);

                String localField = field.equals("") ? null : field;
                String localOrderField = orderField.equals("") ? null : orderField;

                List<tDeviceBalance> listBalance = deviceBalanceRepository.getDeviceBalanceFiltered(deviceId,
                        localStartMovementDate, localEndMovementDate, localMinValue, localMaxValue, localDebitCredit, localField,
                        localOrderField, offset, numberRecords);

                if (listBalance != null)
                    returnList = DeviceUtils.transformDeviceBalanceList(listBalance);


            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.getDeviceBalanceMovements.label, ex.getMessage()));
        }
        return returnList;
    }

    @Override
    public long getCountDeviceBalanceMovements(UUID deviceId, @Nullable String startMovementDate,
                                               @Nullable String endMovementDate, @Nullable String minValue,
                                               @Nullable String maxValue, @Nullable String debitCredit) {


        try {
            tDevice u = deviceRepository.findById(deviceId);

            if (u != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                String localMinValue = minValue.equals("") ? null : minValue;
                String localMaxValue = maxValue.equals("") ? null : maxValue;
                String localDebitCredit = debitCredit.equals("") ? null : debitCredit;
                LocalDateTime localStartMovementDate = startMovementDate.equals("") ? null :
                        LocalDateTime.parse(startMovementDate, formatter);
                LocalDateTime localEndMovementDate = endMovementDate.equals("") ? null :
                        LocalDateTime.parse(endMovementDate, formatter).plusDays(1);


                long nBalance = deviceBalanceRepository.getCountDeviceFiltered(deviceId,
                        localStartMovementDate, localEndMovementDate, localMinValue, localMaxValue, localDebitCredit);

                return nBalance;

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.deviceNotExist.label, ""));
            }

        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DeviceGeneralRepoFault.getCountDeviceBalanceMovements.label, ex.getMessage()));
        }
    }
}

