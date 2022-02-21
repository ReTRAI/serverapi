package com.mob.serverapi.support.repositories.database;


import com.mob.serverapi.support.database.tSupport;
import com.mob.serverapi.support.database.tSupportAssociation;
import com.mob.serverapi.support.database.tSupport_;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class tSupportRepository {

    @Autowired
    ItSupportRepository repository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    protected tSupportAssociationRepository supportAssociationRepository = new tSupportAssociationRepository();

    public tSupport saveSupport(tSupport support) {

        return repository.save(support);
    }

    public void deleteSupportById(UUID supportId) {

        repository.deleteById(supportId);
    }

    public tSupport findById(UUID supportId) {

        return repository.findById(supportId).orElse(null);
    }

    public tSupport findByUserId(UUID userId) {

        return repository.findByUser_UserId(userId);
    }

    public List<tSupport> getSupportFiltered(@Nullable UUID supportId, @Nullable String supportName,
                                               boolean onlyChildren, @Nullable String field,
                                               @Nullable String orderField, int offset, int numberRecords){

        List<tSupport> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tSupport> query = cb.createQuery(tSupport.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tSupport> root = query.from(tSupport.class);
        Join<tSupport, tUser> user = root.join(tSupport_.USER);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (supportId != null && !onlyChildren)
            predicates.add(cb.equal(root.get("supportId"), supportId));

        if (supportId != null && onlyChildren){

            List<UUID> childrenIds = new ArrayList<>();
            List<tSupportAssociation> associationsLevel1 = supportAssociationRepository.getAssociationByParentSupportId(supportId);
            for (tSupportAssociation ra: associationsLevel1) { childrenIds.add(ra.getChildSupport().getSupportId()); }

            List<Integer> children = new ArrayList<>();
            predicates.add(root.get("supportId").in(childrenIds));
        }
        if (supportName != null)
            predicates.add(cb.like(user.<String>get("userName"), "%"+supportName+"%"));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if(field.equals("supportId"))
            {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if(field.equals("userName")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(user.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(user.get(field)));
            }
        }

        query.orderBy(orders);

        List<tSupport> result = entityManager.createQuery(query)
                .setMaxResults(numberRecords)
                .setFirstResult(offset)
                .getResultList();

        for (tSupport r: result) { finalList.add(r); }

        return finalList;
    }

    public long getCountSupportFiltered(@Nullable UUID supportId, @Nullable String supportName,
                                         boolean onlyChildren){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tSupport> query = cb.createQuery(tSupport.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tSupport> root = query.from(tSupport.class);
        Join<tSupport, tUser> user = root.join(tSupport_.USER);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (supportId != null && !onlyChildren)
            predicates.add(cb.equal(root.get("supportId"), supportId));

        if (supportId != null && onlyChildren){

            List<UUID> childrenIds = new ArrayList<>();
            List<tSupportAssociation> associationsLevel1 = supportAssociationRepository.getAssociationByParentSupportId(supportId);
            for (tSupportAssociation ra: associationsLevel1) { childrenIds.add(ra.getChildSupport().getSupportId()); }

            List<Integer> children = new ArrayList<>();
            predicates.add(root.get("supportId").in(childrenIds));
        }
        if (supportName != null)
            predicates.add(cb.like(user.<String>get("userName"), "%"+supportName+"%"));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        long result = entityManager.createQuery(query)
                .getResultList()
                .size();

        return result;
    }

}
