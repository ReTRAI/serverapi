package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.*;
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
public class tDeviceNotificationRepository {

    @Autowired
    ItDeviceNotificationRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tDeviceNotification saveDeviceNotification(tDeviceNotification deviceNotification) {

        return repository.save(deviceNotification);
    }

    public tDeviceNotification findById(UUID deviceNotificationId) {

        return repository.findById(deviceNotificationId).orElse(null);
    }


    public List<tDeviceNotification> getDeviceNotificationFiltered(@Nullable UUID deviceId, @Nullable LocalDateTime startCreationDate,
                                                                   @Nullable LocalDateTime endCreationDate, @Nullable String checked,
                                                                   @Nullable LocalDateTime startCheckedDate, @Nullable LocalDateTime endCheckedDate,
                                                                   @Nullable String field, @Nullable String orderField,
                                                                   int offset, int numberRecords) {

        List<tDeviceNotification> finalList = new ArrayList<>();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tDeviceNotification> query = cb.createQuery(tDeviceNotification.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tDeviceNotification> root = query.from(tDeviceNotification.class);
        Join<tDeviceNotification, tDevice> device = root.join(tDeviceNotification_.DEVICE);

        query = getPredicates(cb, query, root, device, deviceId, startCreationDate, endCreationDate, checked,
                startCheckedDate, endCheckedDate);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if (field.equals("checked") || field.equals("checkedDate") ||
                    field.equals("creationDate")) {
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

        List<tDeviceNotification> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tDeviceNotification r : result) {
            finalList.add(r);
        }

        return finalList;
    }

    public long getCountDeviceFiltered(@Nullable UUID deviceId, @Nullable LocalDateTime startCreationDate,
                                       @Nullable LocalDateTime endCreationDate, @Nullable String checked,
                                       @Nullable LocalDateTime startCheckedDate, @Nullable LocalDateTime endCheckedDate) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tDeviceNotification> query = cb.createQuery(tDeviceNotification.class);
        Metamodel m = entityManager.getMetamodel();
        Root<tDeviceNotification> root = query.from(tDeviceNotification.class);
        Join<tDeviceNotification, tDevice> device = root.join(tDeviceNotification_.DEVICE);

        query = getPredicates(cb, query, root, device, deviceId, startCreationDate, endCreationDate, checked,
                startCheckedDate, endCheckedDate);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

    private CriteriaQuery<tDeviceNotification> getPredicates(CriteriaBuilder cb, CriteriaQuery<tDeviceNotification> query,
                                                          Root<tDeviceNotification> root, Join<tDeviceNotification, tDevice> device,
                                                        @Nullable UUID deviceId, @Nullable LocalDateTime startCreationDate,
                                                        @Nullable LocalDateTime endCreationDate, @Nullable String checked,
                                                        @Nullable LocalDateTime startCheckedDate, @Nullable LocalDateTime endCheckedDate) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (deviceId != null)
            predicates.add(cb.equal(device.get("deviceId"), deviceId));

        if (startCreationDate != null && endCreationDate!= null)
            predicates.add(cb.between(root.get("creationDate"), startCreationDate, endCreationDate));
        if (startCreationDate != null && endCreationDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), startCreationDate));
        if (startCreationDate == null && endCreationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), endCreationDate));

        if (startCheckedDate != null && endCheckedDate!= null)
            predicates.add(cb.between(root.get("checkedDate"), startCheckedDate, endCheckedDate));
        if (startCheckedDate != null && endCheckedDate== null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("checkedDate"), startCheckedDate));
        if (startCheckedDate == null && endCheckedDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("checkedDate"), endCheckedDate));

        if(checked!= null)
        {
            boolean valChecked = !checked.equals("0");

            predicates.add(cb.equal(root.get("checked"), valChecked));
        }


        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;
    }
}
