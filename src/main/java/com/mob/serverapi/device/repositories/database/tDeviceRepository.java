package com.mob.serverapi.device.repositories.database;


import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceStatus;
import com.mob.serverapi.device.database.tDevice_;
import com.mob.serverapi.reseller.database.tReseller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class tDeviceRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    ItDeviceRepository repository;
    @Autowired
    tDeviceStatusRepository deviceStatusRepository;

    public tDevice saveDevice(tDevice device) {
       return repository.save(device);
    }

    public List<tDevice> getAllDevicesByResellerId(UUID resellerId){

        return repository.findByReseller_ResellerId(resellerId);
    }

    public tDevice findById (UUID deviceId){

        return repository.findById(deviceId).orElse(null);
    }

    public boolean existSerialNumber(String serialNumber)
    {
        return repository.existsBySerialNumber(serialNumber);
    }

    public boolean existImeiNumber(String imeiNumber)
    {
        return repository.existsByImeiNumber(imeiNumber);
    }

    public boolean existSimNumber(String simNumber)
    {
        return repository.existsBySimNumber(simNumber);
    }

    public boolean existAndroidId(String androidId)
    {
        return repository.existsByAndroidId(androidId);
    }

    public long countByDeviceStatusAndActivationDateIsGreaterThan
            (Collection<UUID> resellerIds, tDeviceStatus deviceStatus, LocalDateTime activationDate){

        return repository.countByReseller_ResellerIdIsInAndDeviceStatusAndActivationDateIsGreaterThan
                (resellerIds,deviceStatus,activationDate);
    }

    public long countByDeviceStatusAndExpirationDateIsGreaterThan
            (Collection<UUID> resellerIds, tDeviceStatus deviceStatus, LocalDateTime expirationDate){

        return repository.countByReseller_ResellerIdIsInAndDeviceStatusAndExpirationDateIsLessThan
                (resellerIds,deviceStatus,expirationDate);
    }


    public long countByDeviceStatusAndLastRenovationDateIsGreaterThan
            (Collection<UUID> resellerIds, tDeviceStatus deviceStatus, LocalDateTime lastActivationDate){

        return repository.countByReseller_ResellerIdIsInAndDeviceStatusAndLastRenovationDateIsGreaterThan
                (resellerIds,deviceStatus,lastActivationDate);
    }


    public long countByDeviceStatus (Collection<UUID> resellerIds, tDeviceStatus deviceStatus){

        return repository.countByReseller_ResellerIdIsInAndDeviceStatus(resellerIds,deviceStatus);
    }

    public long countNotInByDeviceStatus (Collection<UUID> resellerIds, List<tDeviceStatus> deviceStatus){

        return repository.countByReseller_ResellerIdIsInAndDeviceStatusIsNotIn(resellerIds,deviceStatus);
    }

    public long countAll (Collection<UUID> resellerIds){

        return repository.countByReseller_ResellerIdIsIn(resellerIds);
    }

    public List<tDevice> getDevicesFiltered(@Nullable UUID deviceId, @Nullable UUID resellerId,
                                         @Nullable String deviceStatus, @Nullable LocalDateTime startCreationDate,
                                         @Nullable LocalDateTime endCreationDate, @Nullable LocalDateTime startActivationDate,
                                         @Nullable LocalDateTime endActivationDate, @Nullable LocalDateTime startExpirationDate,
                                         @Nullable LocalDateTime endExpirationDate, @Nullable String field,
                                         @Nullable String orderField, int offset, int numberRecords){

        List<tDevice> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tDevice> query = cb.createQuery(tDevice.class);
        Root<tDevice> root = query.from(tDevice.class);
        Join<tDevice, tDeviceStatus> status = root.join(tDevice_.DEVICE_STATUS);
        Join<tDevice, tReseller> reseller = root.join(tDevice_.RESELLER, JoinType.LEFT);

        query = getPredicates(cb,query,root,status,reseller,deviceId,resellerId,deviceStatus,startCreationDate,endCreationDate,
                startActivationDate,endActivationDate,startExpirationDate,endExpirationDate);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if(field.equals("deviceId") ||  field.equals("creationDate")
                    ||  field.equals("activationDate")||  field.equals("expirationDate"))
            {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if(field.equals("deviceStatus")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(status.get("description")));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(status.get("description")));
            }
            if(field.equals("resellerId")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(reseller.get("resellerId")));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(reseller.get("resellerId")));
            }
        }

        query.orderBy(orders);

        List<tDevice> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tDevice r: result) { finalList.add(r); }

        return finalList;
    }

    public long getCountDevicesFiltered(@Nullable UUID deviceId, @Nullable UUID resellerId,
                                     @Nullable String deviceStatus, @Nullable LocalDateTime startCreationDate,
                                     @Nullable LocalDateTime endCreationDate, @Nullable LocalDateTime startActivationDate,
                                     @Nullable LocalDateTime endActivationDate, @Nullable LocalDateTime startExpirationDate,
                                     @Nullable LocalDateTime endExpirationDate){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tDevice> query = cb.createQuery(tDevice.class);
        Root<tDevice> root = query.from(tDevice.class);
        Join<tDevice, tDeviceStatus> status = root.join(tDevice_.DEVICE_STATUS);
        Join<tDevice, tReseller> reseller = root.join(tDevice_.RESELLER, JoinType.LEFT);

        query = getPredicates(cb,query,root,status,reseller,deviceId,resellerId,deviceStatus,startCreationDate,endCreationDate,
                startActivationDate,endActivationDate,startExpirationDate,endExpirationDate);


        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

    private CriteriaQuery<tDevice> getPredicates(CriteriaBuilder cb, CriteriaQuery<tDevice> query, Root<tDevice> root,
                                                 Join<tDevice, tDeviceStatus> status, Join<tDevice, tReseller> reseller,
                                                 @Nullable UUID deviceId,
                                                 @Nullable UUID resellerId,
                                                 @Nullable String deviceStatus,
                                                 @Nullable LocalDateTime startCreationDate,
                                                 @Nullable LocalDateTime endCreationDate,
                                                 @Nullable LocalDateTime startActivationDate,
                                                 @Nullable LocalDateTime endActivationDate,
                                                 @Nullable LocalDateTime startExpirationDate,
                                                 @Nullable LocalDateTime endExpirationDate ){

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (deviceId != null)
            predicates.add(cb.equal(root.get("deviceId"), deviceId));
        if (deviceStatus != null)
            predicates.add(cb.equal(cb.lower(status.<String>get("description")), deviceStatus.toLowerCase()));
        if (resellerId != null)
            predicates.add(cb.equal(reseller.get("resellerId"), resellerId));

        if (startCreationDate != null && endCreationDate!= null)
            predicates.add(cb.between(root.get("creationDate"), startCreationDate, endCreationDate));
        if (startCreationDate != null && endCreationDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), startCreationDate));
        if (startCreationDate == null && endCreationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), endCreationDate));

        if (startActivationDate != null && endActivationDate!= null)
            predicates.add(cb.between(root.get("activationDate"), startActivationDate, endActivationDate));
        if (startActivationDate != null && endActivationDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("activationDate"), startActivationDate));
        if (startActivationDate == null && endActivationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("activationDate"), endActivationDate));

        if (startExpirationDate != null && endExpirationDate!= null)
            predicates.add(cb.between(root.get("expirationDate"), startExpirationDate, endExpirationDate));
        if (startExpirationDate != null && endExpirationDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("expirationDate"), startExpirationDate));
        if (startExpirationDate == null && endExpirationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("expirationDate"), endExpirationDate));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;

    }


}
