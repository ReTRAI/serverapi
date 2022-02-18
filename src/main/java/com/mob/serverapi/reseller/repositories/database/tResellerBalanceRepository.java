package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerBalance;
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
public class tResellerBalanceRepository {

    @Autowired
    ItResellerBalanceRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public tResellerBalance saveResellerBalance(tResellerBalance resellerBalance){

        return repository.save(resellerBalance);
    }

    public List<tResellerBalance> getAllBalanceMovements(UUID resellerId){
        return repository.findByReseller_ResellerIdOrderByMovementDateDesc(resellerId);
    }

    public float getCurrentBalance(UUID resellerId){

        float value = 0.0f;

        List<tResellerBalance> allMovements = repository.findByReseller_ResellerIdOrderByMovementDateDesc(resellerId);

        for (tResellerBalance rb: allMovements) {

            float movValue = rb.getDebitCredit().equals("D") ? rb.getMovementValue() * -1.0f : rb.getMovementValue();
            value += movValue;
        }

        return value;
    }
}
