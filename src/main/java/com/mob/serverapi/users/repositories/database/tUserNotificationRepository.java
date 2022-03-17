package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserNotification;
import com.mob.serverapi.users.database.tUserNotification_;
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
public class tUserNotificationRepository {

    @Autowired
    ItUserNotificationRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tUserNotification saveUserNotification(tUserNotification userNotification) {

        return repository.save(userNotification);
    }

    public tUserNotification findById(UUID userNotificationId) {

        return repository.findById(userNotificationId).orElse(null);
    }

    public List<tUserNotification> getUserNotificationFiltered(@Nullable UUID userId, @Nullable LocalDateTime startCreationDate,
                                                               @Nullable LocalDateTime endCreationDate, @Nullable String checked,
                                                               @Nullable LocalDateTime startCheckedDate, @Nullable LocalDateTime endCheckedDate,
                                                               @Nullable String field, @Nullable String orderField,
                                                               int offset, int numberRecords) {

        List<tUserNotification> finalList = new ArrayList<>();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tUserNotification> query = cb.createQuery(tUserNotification.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tUserNotification> root = query.from(tUserNotification.class);
        Join<tUserNotification, tUser> user = root.join(tUserNotification_.USER);

        query = getPredicates(cb, query, root, user, userId, startCreationDate, endCreationDate, checked,
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
            if (field.equals("userId")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(user.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(user.get(field)));
            }
        }

        query.orderBy(orders);

        List<tUserNotification> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tUserNotification r : result) {
            finalList.add(r);
        }

        return finalList;
    }

    public long getCountUserFiltered(@Nullable UUID userId, @Nullable LocalDateTime startCreationDate,
                                     @Nullable LocalDateTime endCreationDate, @Nullable String checked,
                                     @Nullable LocalDateTime startCheckedDate, @Nullable LocalDateTime endCheckedDate) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tUserNotification> query = cb.createQuery(tUserNotification.class);
        Metamodel m = entityManager.getMetamodel();
        Root<tUserNotification> root = query.from(tUserNotification.class);
        Join<tUserNotification, tUser> user = root.join(tUserNotification_.USER);

        query = getPredicates(cb, query, root, user, userId, startCreationDate, endCreationDate, checked,
                startCheckedDate, endCheckedDate);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

    private CriteriaQuery<tUserNotification> getPredicates(CriteriaBuilder cb, CriteriaQuery<tUserNotification> query,
                                                           Root<tUserNotification> root, Join<tUserNotification, tUser> user,
                                                           @Nullable UUID userId, @Nullable LocalDateTime startCreationDate,
                                                           @Nullable LocalDateTime endCreationDate, @Nullable String checked,
                                                           @Nullable LocalDateTime startCheckedDate, @Nullable LocalDateTime endCheckedDate) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (userId != null)
            predicates.add(cb.equal(user.get("userId"), userId));

        if (startCreationDate != null && endCreationDate != null)
            predicates.add(cb.between(root.get("creationDate"), startCreationDate, endCreationDate));
        if (startCreationDate != null && endCreationDate == null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"), startCreationDate));
        if (startCreationDate == null && endCreationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"), endCreationDate));

        if (startCheckedDate != null && endCheckedDate != null)
            predicates.add(cb.between(root.get("checkedDate"), startCheckedDate, endCheckedDate));
        if (startCheckedDate != null && endCheckedDate == null)
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
