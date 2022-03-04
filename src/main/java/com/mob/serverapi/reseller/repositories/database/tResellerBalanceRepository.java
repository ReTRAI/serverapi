package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerBalance;
import com.mob.serverapi.reseller.database.tResellerBalance_;
import com.mob.serverapi.reseller.database.tReseller_;
import com.mob.serverapi.users.database.tUser;
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
public class tResellerBalanceRepository {

    @Autowired
    ItResellerBalanceRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tResellerBalance saveResellerBalance(tResellerBalance resellerBalance) {

        return repository.save(resellerBalance);
    }

    public List<tResellerBalance> getAllBalanceMovements(UUID resellerId) {
        return repository.findByReseller_ResellerIdOrderByMovementDateDesc(resellerId);
    }

    public float getCurrentBalance(UUID resellerId) {

        float value = 0.0f;

        List<tResellerBalance> allMovements = repository.findByReseller_ResellerIdOrderByMovementDateDesc(resellerId);

        for (tResellerBalance rb : allMovements) {

            float movValue = rb.getDebitCredit().equals("D") ? rb.getMovementValue() * -1.0f : rb.getMovementValue();
            value += movValue;
        }

        return value;
    }

    public List<tResellerBalance> getResellerBalanceFiltered(UUID resellerId, @Nullable LocalDateTime startMovementDate,
                                                             @Nullable LocalDateTime endMovementDate, @Nullable String minValue,
                                                             @Nullable String maxValue, @Nullable String debitCredit,
                                                             @Nullable String field, @Nullable String orderField,
                                                             int offset, int numberRecords) {

        List<tResellerBalance> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tResellerBalance> query = cb.createQuery(tResellerBalance.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tResellerBalance> root = query.from(tResellerBalance.class);
        Join<tResellerBalance, tReseller> reseller = root.join(tResellerBalance_.RESELLER);

        query = getPredicates(cb, query, root, reseller, resellerId, startMovementDate, endMovementDate,
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
            if (field.equals("resellerId")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(reseller.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(reseller.get(field)));
            }
        }

        query.orderBy(orders);

        List<tResellerBalance> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tResellerBalance r : result) {
            finalList.add(r);
        }

        return finalList;
    }

    public long getCountResellerFiltered(UUID resellerId, @Nullable LocalDateTime startMovementDate,
                                         @Nullable LocalDateTime endMovementDate, @Nullable String minValue,
                                         @Nullable String maxValue, @Nullable String debitCredit) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tResellerBalance> query = cb.createQuery(tResellerBalance.class);
        Metamodel m = entityManager.getMetamodel();
        Root<tResellerBalance> root = query.from(tResellerBalance.class);
        Join<tResellerBalance, tReseller> reseller = root.join(tResellerBalance_.RESELLER);

        query = getPredicates(cb, query, root, reseller, resellerId, startMovementDate, endMovementDate,
                minValue, maxValue, debitCredit);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

    private CriteriaQuery<tResellerBalance> getPredicates(CriteriaBuilder cb, CriteriaQuery<tResellerBalance> query,
                                                          Root<tResellerBalance> root, Join<tResellerBalance, tReseller> reseller,
                                                          UUID resellerId, @Nullable LocalDateTime startMovementDate,
                                                          @Nullable LocalDateTime endMovementDate, @Nullable String minValue,
                                                          @Nullable String maxValue, @Nullable String debitCredit) {

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (resellerId != null)
            predicates.add(cb.equal(reseller.get("resellerId"), resellerId));

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
            predicates.add(cb.equal(cb.lower(root.get("debitCredit")), debitCredit));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;
    }
}
