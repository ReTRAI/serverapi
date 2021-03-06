package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.base.ResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
public class tResellerAssociationRepository {

    @Autowired
    ItResellerAssociationRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tResellerAssociation saveResellerAssociation(tResellerAssociation reseller) {

        return repository.save(reseller);
    }

    public List<tResellerAssociation> getAssociationByParentResellerId (UUID resellerId){
        return repository.findDistinctByParentReseller_ResellerId(resellerId);
    }

    public tResellerAssociation getAssociationByChildResellerId (UUID resellerId){
        return repository.findByChildReseller_ResellerId(resellerId);
    }

    public long countAssociationByChildResellerId (UUID resellerId){
        return repository.countByChildReseller_ResellerId(resellerId);
    }

    public tResellerAssociation getAssociation (UUID parentResellerId, UUID childResellerId){
        return repository.findByParentReseller_ResellerIdAndChildReseller_ResellerId(parentResellerId, childResellerId);
    }

    public boolean existInResellerAssociation(UUID parentResellerId, UUID childResellerId){
        return repository.existsByParentReseller_ResellerIdOrChildReseller_ResellerId(parentResellerId,childResellerId);
    }

    @Transactional
    public void deleteAssociationById (UUID resellerAssociationId){
        repository.deleteById(resellerAssociationId);
    }


}
