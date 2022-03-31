package com.mob.serverapi.support.repositories.database;


import com.mob.serverapi.support.database.*;
import com.mob.serverapi.support.database.tTicketDetail_;
import com.mob.serverapi.support.database.tTicket_;
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
public class tTicketDetailRepository {

    @Autowired
    ItTicketDetailRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public void saveTicketDetail(tTicketDetail ticketDetail) {
        repository.save(ticketDetail);
    }

    public List<tTicketDetail> getTicketDetailFiltered(@Nullable UUID ticketId,
                                           @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate,
                                           @Nullable UUID responseUserId,
                                           @Nullable String field, @Nullable String orderField,
                                           int offset, int numberRecords) {

        List<tTicketDetail> finalList = new ArrayList<>();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tTicketDetail> query = cb.createQuery(tTicketDetail.class);
        Root<tTicketDetail> root = query.from(tTicketDetail.class);
        Join<tTicketDetail, tTicket> ticketJoin = root.join(tTicketDetail_.TICKET);
        Join<tTicketDetail, tUser> responseUser = root.join(tTicketDetail_.USER);

        query = getPredicates(cb,query,root,ticketJoin,responseUser,ticketId,startCreationDate,endCreationDate,responseUserId);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if (field.equals("detailDate")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if (field.equals("ticketId") ) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(ticketJoin.get("ticketId")));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(ticketJoin.get("ticketId")));
            }
        }

        query.orderBy(orders);

        List<tTicketDetail> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tTicketDetail r : result) {
            finalList.add(r);
        }

        return finalList;
    }

    public long getCountTicketDetailFiltered(@Nullable UUID ticketId,
                                       @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate,
                                             @Nullable UUID responseUserId) {

        List<tTicketDetail> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tTicketDetail> query = cb.createQuery(tTicketDetail.class);
        Root<tTicketDetail> root = query.from(tTicketDetail.class);
        Join<tTicketDetail, tTicket> ticketJoin = root.join(tTicketDetail_.TICKET);
        Join<tTicketDetail, tUser> responseUser = root.join(tTicketDetail_.USER);

        query = getPredicates(cb,query,root,ticketJoin,responseUser,ticketId,startCreationDate,endCreationDate,responseUserId);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();


        return result;
    }

    private CriteriaQuery<tTicketDetail> getPredicates(CriteriaBuilder cb , CriteriaQuery<tTicketDetail> query, Root<tTicketDetail> root,
                                                       Join<tTicketDetail, tTicket> ticketJoin,
                                                       Join<tTicketDetail, tUser> responseUser,
                                                       @Nullable UUID ticketId,
                                                       @Nullable LocalDateTime startCreationDate,
                                                       @Nullable LocalDateTime endCreationDate,
                                                       @Nullable UUID responseUserId){

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (ticketId != null)
            predicates.add(cb.equal(ticketJoin.get("ticketId"), ticketId));

        if (startCreationDate != null && endCreationDate != null)
            predicates.add(cb.between(root.get("detailDate"), startCreationDate, endCreationDate));
        if (startCreationDate != null && endCreationDate == null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("detailDate"), startCreationDate));
        if (startCreationDate == null && endCreationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("detailDate"), endCreationDate));

        if (responseUserId != null)
            predicates.add(cb.equal(responseUser.get("userId"), responseUserId));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;
    }

}
