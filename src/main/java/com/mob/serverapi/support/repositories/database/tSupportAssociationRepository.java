package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupportAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
public class tSupportAssociationRepository {

    @Autowired
    ItSupportAssociationRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tSupportAssociation saveSupportAssociation(tSupportAssociation supportAssociation) {

        return repository.save(supportAssociation);
    }

    public List<tSupportAssociation> getAssociationByParentSupportId(UUID supportId) {
        return repository.findDistinctByParentSupport_SupportId(supportId);
    }

    public long countAssociationByChildSupportId(UUID supportId) {
        return repository.countByChildSupport_SupportId(supportId);
    }

    public tSupportAssociation getAssociation(UUID parentSupportId, UUID childSupportId) {
        return repository.findByParentSupport_SupportIdAndChildSupport_SupportId(parentSupportId, childSupportId);
    }

    @Transactional
    public void deleteById(UUID supportAssociationId) {
        repository.deleteById(supportAssociationId);
    }
}
