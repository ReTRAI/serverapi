package com.mob.serverapi.reseller.repositories.endpoints;

import com.mob.serverapi.reseller.base.Reseller;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;


public class ResellerRepository implements IResellerRepository {

    @PersistenceContext
    protected EntityManager entityManager;


    @Override
    public Reseller getResellerById(int resellerId) {
        return null;
    }
}

