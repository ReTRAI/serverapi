package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class tResellerAssociationRepository {

    @Autowired
    ItResellerAssociationRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tResellerAssociation saveResellerAssociation(tResellerAssociation reseller) {

        return repository.save(reseller);
    }

    public List<tResellerAssociation> getAssociationByParentResellerId (int resellerId){
        return repository.findDistinctByParentReseller_ResellerId(resellerId);
    }

}
