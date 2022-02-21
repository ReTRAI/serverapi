package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Component
public class tSupportRepository {

    @Autowired
    ItSupportRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

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


}
