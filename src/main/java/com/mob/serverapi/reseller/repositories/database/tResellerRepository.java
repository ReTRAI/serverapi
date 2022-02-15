package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tResellerRepository {

    @Autowired
    ItResellerRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tReseller saveReseller(tReseller reseller){

        return repository.save(reseller);
    }

    public void deleteResellerById(int resellerId){

        repository.deleteById(resellerId);
    }

    public tReseller findById(int resellerId){

        return repository.findById(resellerId).orElse(null);
    }

    public List<tReseller> getResellerFiltered(@Nullable Integer resellerId, @Nullable String resellerName, @Nullable String field,
                                               @Nullable String orderField, int offset, int numberRecords){

        List<tReseller> finalList = new ArrayList<>();

        Pageable pages = PageRequest.of(offset, numberRecords);

        if(orderField!= null) {
            if (orderField.equals("ASC"))
                pages = PageRequest.of(offset, numberRecords, Sort.by(field).ascending());
            if (orderField.equals("DESC"))
                pages = PageRequest.of(offset, numberRecords, Sort.by(field).descending());

        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tReseller> query = cb.createQuery(tReseller.class);
        Root<tReseller> root = query.from(tReseller.class);
        Join<tUser, tReseller> user = root.join("userId");


        List<Predicate> predicates = new ArrayList<Predicate>();
        if (resellerId != null)
            predicates.add(cb.equal(root.get("resellerId"), resellerId));
        if (resellerName != null)
            predicates.add(cb.like(user.<String>get("userName"), "%"+resellerName+"%"));
//        if (CollectionUtils.isNotEmpty(status))
//            predicates.add(notification.get("notificationAckStatus").get("ackStatusCode").in(status));
//        if (CollectionUtils.isNotEmpty(channels))
//            predicates.add(notification.get("notificationRequestChannel").get("notificationChannel").get("channelCode").in(channels));


        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);


//        List<Order> orders = new ArrayList<Order>(2);
//        if (field != null)
//            if(orderField.equals("ASC"))
//                orders.add(cb.asc(notification.get(field)));
//        if(orderField.equals("DESC"))
//            orders.add(cb.desc(notification.get(field)));

//        query.orderBy(orders);
        TypedQuery<tReseller> finalQuery = entityManager.createQuery(query);
        Page<tReseller> result = new PageImpl<tReseller>(finalQuery.getResultList(), pages, numberRecords);


        for (tReseller r: result
             ) {
            finalList.add(r);
        }

        return finalList;
    }
}
