package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDevice;
import com.mob.serverapi.device.database.tDeviceBalance;
import com.mob.serverapi.device.database.tDeviceBalance_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class tDeviceBalanceRepository {

    @Autowired
    ItDeviceBalanceRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tDeviceBalance saveDeviceBalance(tDeviceBalance deviceBalance) {

        return repository.save(deviceBalance);
    }

    public List<tDeviceBalance> getAllBalanceMovements(UUID deviceId) {
        return repository.findByDevice_DeviceIdOrderByMovementDateDesc(deviceId);
    }

    public float getCurrentBalance(UUID deviceId) {

        float value = 0.0f;

        List<tDeviceBalance> allMovements = repository.findByDevice_DeviceIdOrderByMovementDateDesc(deviceId);

        for (tDeviceBalance rb : allMovements) {

            float movValue = rb.getDebitCredit().equals("D") ? rb.getMovementValue() * -1.0f : rb.getMovementValue();
            value += movValue;
        }

        return value;
    }

    public List<tDeviceBalance> getDeviceBalanceFiltered(UUID deviceId, @Nullable LocalDateTime startMovementDate,
                                                             @Nullable LocalDateTime endMovementDate, @Nullable String minValue,
                                                             @Nullable String maxValue, @Nullable String debitCredit,
                                                             @Nullable String field, @Nullable String orderField,
                                                             int offset, int numberRecords) {

        List<tDeviceBalance> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tDeviceBalance> query = cb.createQuery(tDeviceBalance.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tDeviceBalance> root = query.from(tDeviceBalance.class);
        Join<tDeviceBalance, tDevice> device = root.join(tDeviceBalance_.DEVICE);

        query = getPredicates(cb, query, root, device, deviceId, startMovementDate, endMovementDate,
                minValue, maxValue, debitCredit);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if (field.equals("debitCredit") || field.equals("movementValue") ||
                    field.equals("movementDate")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if (field.equals("deviceId")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(device.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(device.get(field)));
            }
        }

        query.orderBy(orders);

        List<tDeviceBalance> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tDeviceBalance r : result) {
            finalList.add(r);
        }

        return finalList;
    }

    public long getCountDeviceFiltered(UUID resellerId, @Nullable LocalDateTime startMovementDate,
                                         @Nullable LocalDateTime endMovementDate, @Nullable String minValue,
                                         @Nullable String maxValue, @Nullable String debitCredit) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tDeviceBalance> query = cb.createQuery(tDeviceBalance.class);
        Metamodel m = entityManager.getMetamodel();
        Root<tDeviceBalance> root = query.from(tDeviceBalance.class);
        Join<tDeviceBalance, tDevice> device = root.join(tDeviceBalance_.DEVICE);

        query = getPredicates(cb, query, root, device, resellerId, startMovementDate, endMovementDate,
                minValue, maxValue, debitCredit);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

    private CriteriaQuery<tDeviceBalance> getPredicates(CriteriaBuilder cb, CriteriaQuery<tDeviceBalance> query,
                                                          Root<tDeviceBalance> root, Join<tDeviceBalance, tDevice> device,
                                                          UUID deviceId, @Nullable LocalDateTime startMovementDate,
                                                          @Nullable LocalDateTime endMovementDate, @Nullable String minValue,
                                                          @Nullable String maxValue, @Nullable String debitCredit) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (deviceId != null)
            predicates.add(cb.equal(device.get("deviceId"), deviceId));

        if (startMovementDate != null && endMovementDate!= null)
            predicates.add(cb.between(root.get("movementDate"), startMovementDate, endMovementDate));
        if (startMovementDate != null && endMovementDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("movementDate"), startMovementDate));
        if (startMovementDate == null && endMovementDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("movementDate"), endMovementDate));

        if (minValue != null && maxValue!= null)
            predicates.add(cb.between(root.get("movementValue"), Float.parseFloat(minValue), Float.parseFloat(maxValue)));
        if (minValue != null && maxValue== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("movementValue"), Float.parseFloat(minValue)));
        if (minValue == null && maxValue != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("movementValue"), Float.parseFloat(maxValue)));

        if (debitCredit != null)
            predicates.add(cb.equal(cb.lower(root.get("debitCredit")), debitCredit.toLowerCase()));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;
    }
}
