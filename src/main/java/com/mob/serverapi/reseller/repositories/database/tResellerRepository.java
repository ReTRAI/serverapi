package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tReseller_;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUser_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tResellerRepository {

    @Autowired
    ItResellerRepository repository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    protected tResellerAssociationRepository resellerAssociationRepository = new tResellerAssociationRepository();

    public tReseller saveReseller(tReseller reseller){

        return repository.save(reseller);
    }

    public void deleteResellerById(UUID resellerId){

        repository.deleteById(resellerId);
    }

    public tReseller findById(UUID resellerId){

        return repository.findById(resellerId).orElse(null);
    }

    public List<tReseller> getResellerFiltered(@Nullable UUID resellerId, @Nullable String resellerName,
                                               boolean onlyChildren, @Nullable String field,
                                               @Nullable String orderField, int offset, int numberRecords){

        List<tReseller> finalList = new ArrayList<>();


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<tReseller> query = cb.createQuery(tReseller.class);

        Metamodel m = entityManager.getMetamodel();
        Root<tReseller> root = query.from(tReseller.class);
        Join<tReseller, tUser> user = root.join(tReseller_.USER);

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (resellerId != null && !onlyChildren)
            predicates.add(cb.equal(root.get("resellerId"), resellerId));

        if (resellerId != null && onlyChildren){

            List<UUID> childrenIds = new ArrayList<>();
            List<tResellerAssociation> associationsLevel1 = resellerAssociationRepository.getAssociationByParentResellerId(resellerId);
            for (tResellerAssociation ra: associationsLevel1) { childrenIds.add(ra.getChildReseller().getResellerId()); }

            List<Integer> children = new ArrayList<>();
            predicates.add(root.get("resellerId").in(childrenIds));
        }
        if (resellerName != null)
            predicates.add(cb.like(user.<String>get("userName"), "%"+resellerName+"%"));

        Predicate[] predArray = new Predicate[predicates.size()];
        predicates.toArray(predArray);
        query.where(predArray);

        List<Order> orders = new ArrayList<Order>(2);
        if (field != null) {
            if(field.equals("resellerId") || field.equals("currentBalance") ||
                    field.equals("totalDevices") || field.equals("activeDevices") ||
                    field.equals("inactiveDevices")  || field.equals("freeDevices"))
            {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.get(field)));
            }
            if(field.equals("userName")) {
                if (orderField.toUpperCase(Locale.ROOT).equals("ASC"))
                    orders.add(cb.asc(root.join(tReseller_.USER).get(field)));
                if (orderField.toUpperCase(Locale.ROOT).equals("DESC"))
                    orders.add(cb.desc(root.join(tReseller_.USER).get(field)));
            }
        }

        query.orderBy(orders);

        List<tReseller> result = entityManager.createQuery(query)
                                .setMaxResults(numberRecords)
                                .setFirstResult(offset)
                                .getResultList();

        for (tReseller r: result) { finalList.add(r); }

        return finalList;
    }
}
