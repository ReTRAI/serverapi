package com.mob.serverapi.dashboard.repositories.endpoints;

import com.mob.serverapi.dashboard.base.Active;
import com.mob.serverapi.dashboard.base.Expiring;
import com.mob.serverapi.dashboard.base.Global;
import com.mob.serverapi.dashboard.base.Inactive;
import com.mob.serverapi.device.database.tDeviceStatus;
import com.mob.serverapi.device.repositories.database.tDeviceRepository;
import com.mob.serverapi.device.repositories.database.tDeviceStatusRepository;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.repositories.database.tResellerRepository;
import com.mob.serverapi.reseller.repositories.endpoints.ResellerRepository;
import com.mob.serverapi.servicefault.FaultMapping;
import com.mob.serverapi.servicefault.ServiceFault;
import com.mob.serverapi.servicefault.ServiceFaultException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashboardRepository implements IDashboardRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    protected tDeviceRepository deviceRepository = new tDeviceRepository();
    @Autowired
    protected tDeviceStatusRepository deviceStatusRepository = new tDeviceStatusRepository();
    @Autowired
    protected tResellerRepository resellerRepository = new tResellerRepository();
    @Autowired
    protected ResellerRepository resellerMasterRepository = new ResellerRepository();

    @Override
    public Active getActiveDashboardByResellerId(UUID resellerId, boolean recursive) {
        Active active = new Active();

        try {

            tReseller reseller = resellerRepository.findById(resellerId);

            if (reseller != null) {

                List<UUID> resellerIds = new ArrayList<>();
                if(recursive)
                    resellerIds = resellerMasterRepository.getAllIdsInHierachy(resellerId);
                else
                    resellerIds.add(resellerId);


                active.setDay(getActivateInPeriod(resellerIds, 1));
                active.setWeek(getActivateInPeriod(resellerIds, 7));
                active.setMonth(getActivateInPeriod(resellerIds, 30));
                active.setQuarter(getActivateInPeriod(resellerIds, 90));

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DashboardGeneralRepoFault.getActiveDashboard.label, ex.getMessage()));
        }
        return active;
    }

    @Override
    public Inactive getInactiveDashboardByResellerId(UUID resellerId, boolean recursive) {
        Inactive inactive = new Inactive();

        try {

            tReseller reseller = resellerRepository.findById(resellerId);

            if (reseller != null) {

                List<UUID> resellerIds = new ArrayList<>();
                if(recursive)
                    resellerIds = resellerMasterRepository.getAllIdsInHierachy(resellerId);
                else
                    resellerIds.add(resellerId);

                inactive.setBlocked(getBlocked(resellerIds));
                inactive.setSuspended(getSuspended(resellerIds));
                inactive.setWiped(getWipped(resellerIds));
                inactive.setRemaining(getRemaining(resellerIds));

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DashboardGeneralRepoFault.getInactiveDashboard.label, ex.getMessage()));
        }
        return inactive;
    }

    @Override
    public Global getGlobalDashboardByResellerId(UUID resellerId, boolean recursive) {
        Global global = new Global();

        try {

            tReseller reseller = resellerRepository.findById(resellerId);

            if (reseller != null) {

                List<UUID> resellerIds = new ArrayList<>();
                if(recursive)
                    resellerIds = resellerMasterRepository.getAllIdsInHierachy(resellerId);
                else
                    resellerIds.add(resellerId);

                global.setActive(getActive(resellerIds));
                global.setFree(getFree(resellerIds));
                global.setNotActive(getNotActive(resellerIds));
                global.setPortalDevices(getAll(resellerIds));

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DashboardGeneralRepoFault.getGlobalDashboard.label, ex.getMessage()));
        }
        return global;
    }

    @Override
    public Expiring getExpiringDashboardByResellerId(UUID resellerId, boolean recursive) {
        Expiring expiring = new Expiring();

        try {

            tReseller reseller = resellerRepository.findById(resellerId);

            if (reseller != null) {

                List<UUID> resellerIds = new ArrayList<>();
                if(recursive)
                    resellerIds = resellerMasterRepository.getAllIdsInHierachy(resellerId);
                else
                    resellerIds.add(resellerId);


                expiring.setRenewed(getRenewedInPeriod(resellerIds,7));
                expiring.setIn7Days(getExpiringInPeriod(resellerIds, 7));
                expiring.setIn15Days(getExpiringInPeriod(resellerIds, 15));
                expiring.setIn30Days(getExpiringInPeriod(resellerIds, 30));

            } else {
                throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.RepoFault.resellerNotExist.label, ""));
            }
        } catch (ServiceFaultException se) {
            throw se;
        } catch (Exception ex) {
            throw new ServiceFaultException(FaultMapping.FaultType.error.label, new ServiceFault(FaultMapping.DashboardGeneralRepoFault.getExpiringDashboard.label, ex.getMessage()));
        }
        return expiring;
    }

    public long getActivateInPeriod(List<UUID> resellerIds, int days) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.ACTIVE.name());

        LocalDateTime beginDate = LocalDateTime.now().plusDays(days * -1);

        long count = deviceRepository.countByDeviceStatusAndActivationDateIsGreaterThan(resellerIds, deviceStatus, beginDate);

        return count;
    }

    public long getExpiringInPeriod(List<UUID> resellerIds, int days) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.SUSPENDED.name());

        LocalDateTime endDate = LocalDateTime.now().plusDays(days);

        long count = deviceRepository.countByDeviceStatusAndExpirationDateIsGreaterThan(resellerIds, deviceStatus, endDate);

        return count;
    }

    public long getRenewedInPeriod(List<UUID> resellerIds, int days) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.ACTIVE.name());

        LocalDateTime beginDate = LocalDateTime.now().plusDays(days*-1);

        long count = deviceRepository.countByDeviceStatusAndLastRenovationDateIsGreaterThan(resellerIds, deviceStatus, beginDate);

        return count;
    }

    public long getActive(List<UUID> resellerIds) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.ACTIVE.name());

        long count = deviceRepository.countByDeviceStatus(resellerIds, deviceStatus);

        return count;
    }

    public long getFree(List<UUID> resellerIds) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.FREE.name());

        long count = deviceRepository.countByDeviceStatus(resellerIds, deviceStatus);

        return count;
    }

    public long getBlocked(List<UUID> resellerIds) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.BLOCKED.name());

        long count = deviceRepository.countByDeviceStatus(resellerIds, deviceStatus);

        return count;
    }

    public long getWipped(List<UUID> resellerIds) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.WIPED.name());

        long count = deviceRepository.countByDeviceStatus(resellerIds, deviceStatus);

        return count;
    }

    public long getSuspended(List<UUID> resellerIds) {

        tDeviceStatus deviceStatus = deviceStatusRepository.findDeviceStatusByDescription(
                tDeviceStatus.DeviceStatusEnum.SUSPENDED.name());

        long count = deviceRepository.countByDeviceStatus(resellerIds, deviceStatus);

        return count;
    }

    public long getRemaining(List<UUID> resellerIds) {

        List<tDeviceStatus> statusToFilter = new ArrayList<>();
        statusToFilter.add(deviceStatusRepository.findDeviceStatusByDescription(tDeviceStatus.DeviceStatusEnum.SUSPENDED.name()));
        statusToFilter.add(deviceStatusRepository.findDeviceStatusByDescription(tDeviceStatus.DeviceStatusEnum.BLOCKED.name()));
        statusToFilter.add(deviceStatusRepository.findDeviceStatusByDescription(tDeviceStatus.DeviceStatusEnum.WIPED.name()));
        statusToFilter.add(deviceStatusRepository.findDeviceStatusByDescription(tDeviceStatus.DeviceStatusEnum.ACTIVE.name()));

        long count = deviceRepository.countNotInByDeviceStatus(resellerIds, statusToFilter);

        return count;
    }

    public long getNotActive(List<UUID> resellerIds) {

        List<tDeviceStatus> statusToFilter = new ArrayList<>();
        statusToFilter.add(deviceStatusRepository.findDeviceStatusByDescription(tDeviceStatus.DeviceStatusEnum.ACTIVE.name()));
        statusToFilter.add(deviceStatusRepository.findDeviceStatusByDescription(tDeviceStatus.DeviceStatusEnum.FREE.name()));

        long count = deviceRepository.countNotInByDeviceStatus(resellerIds, statusToFilter);

        return count;
    }

    public long getAll(List<UUID> resellerIds) {

        long count = deviceRepository.countAll(resellerIds);

        return count;
    }
}

