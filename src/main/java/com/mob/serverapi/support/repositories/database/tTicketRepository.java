package com.mob.serverapi.support.repositories.database;


import com.mob.serverapi.support.database.tTicket;
import com.mob.serverapi.support.database.tTicketStatus;
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
public class tTicketRepository {

    @Autowired
    ItTicketRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tTicket saveTicket(tTicket ticket) {

        return repository.save(ticket);
    }

    public void deleteTicketById(UUID ticketId) {

        repository.deleteById(ticketId);
    }

    public tTicket findById(UUID ticketId) {

        return repository.findById(ticketId).orElse(null);
    }

    public tTicket findByOpenUserId(UUID userId) {

        return repository.findByOpenUser_UserId(userId);
    }

    public tTicket findByAssignedUserId(UUID userId) {

        return repository.findByAssignedUser_UserId(userId);
    }

    public List<tTicket> getTicketFiltered(@Nullable UUID ticketId, @Nullable String status,
                                           @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate,
                                           @Nullable UUID openUserId, @Nullable UUID assignedUserId,
                                           @Nullable String field, @Nullable String orderField,
                                           int offset, int numberRecords) {

        List<tTicket> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tTicket> query = cb.createQuery(tTicket.class);
        Root<tTicket> root = query.from(tTicket.class);
        Join<tTicket, tTicketStatus> statusJoin = root.join(tTicket_.TICKET_STATUS);

        Join<tTicket, tUser> openUser = root.join(tTicket_.OPEN_USER);
        Join<tTicket, tUser> assignUser = root.join(tTicket_.ASSIGNED_USER, JoinType.LEFT);

        query = getPredicates(cb,query,root,statusJoin, openUser, assignUser, ticketId,status,startCreationDate,
                endCreationDate,openUserId,assignedUserId);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if (field.equals("ticketId") || field.equals("openDate")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if (field.equals("status")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(statusJoin.get("description")));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(statusJoin.get("description")));
            }
        }

        query.orderBy(orders);

        List<tTicket> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tTicket r : result) {
            finalList.add(r);
        }

        return finalList;
    }

    public long getCountTicketFiltered(@Nullable UUID ticketId, @Nullable String status,
                                       @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate,
                                       @Nullable UUID openUserId, @Nullable UUID assignedUserId) {

        List<tTicket> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tTicket> query = cb.createQuery(tTicket.class);

        Root<tTicket> root = query.from(tTicket.class);
        Join<tTicket, tTicketStatus> statusJoin = root.join(tTicket_.TICKET_STATUS);

        Join<tTicket, tUser> openUser = root.join(tTicket_.OPEN_USER);
        Join<tTicket, tUser> assignUser = root.join(tTicket_.ASSIGNED_USER, JoinType.LEFT);


        query = getPredicates(cb,query,root,statusJoin,openUser,assignUser,
                ticketId,status,startCreationDate,endCreationDate,openUserId,assignedUserId);


        long result = entityManager.createQuery(query)
                .getResultList()
                .size();


        return result;
    }

    private CriteriaQuery<tTicket> getPredicates(CriteriaBuilder cb, CriteriaQuery<tTicket> query, Root<tTicket> root,
                                                 Join<tTicket, tTicketStatus> statusJoin,Join<tTicket, tUser> openUser,
                                                 Join<tTicket, tUser> assignUser,
                                                 @Nullable UUID ticketId, @Nullable String status,
                                                 @Nullable LocalDateTime startCreationDate, @Nullable LocalDateTime endCreationDate,
                                                 @Nullable UUID openUserId, @Nullable UUID assignedUserId){

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (ticketId != null)
            predicates.add(cb.equal(root.get("ticketId"), ticketId));
        if (status != null)
            predicates.add(cb.like(cb.lower(statusJoin.get("description")), "%" + status.toLowerCase() + "%"));

        if (startCreationDate != null && endCreationDate != null)
            predicates.add(cb.between(root.get("openDate"), startCreationDate, endCreationDate));
        if (startCreationDate != null && endCreationDate == null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("openDate"), startCreationDate));
        if (startCreationDate == null && endCreationDate != null)
            predicates.add(cb.lessThanOrEqualTo(root.get("openDate"), endCreationDate));

        if (openUserId != null)
            predicates.add(cb.equal(openUser.get("userId"), openUserId));
        if (assignedUserId != null)
            predicates.add(cb.equal(assignUser.get("userId"), assignedUserId));


        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        return query;
    }

}
