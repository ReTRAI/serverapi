package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerAssociationLog;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class tResellerAssociationLogRepository {

    @Autowired
    ItResellerAssociationLogRepository repository;

    public tResellerAssociationLog saveResellerLog(tResellerAssociationLog resellerAssociationLog) {

        return repository.save(resellerAssociationLog);
    }

    public void insertResellerAssociationLog(tUser actionUser, tResellerAssociation alteredUser, String action, String detail) {

        tResellerAssociationLog log = new tResellerAssociationLog();
        log.setUser(actionUser);
        log.setResellerAssociation(alteredUser);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveResellerLog(log);
    }

    @Transactional
    public void deleteResellerAssociationLogByAssociationId(UUID resellerAssociationId){

        repository.deleteByResellerAssociation_ResellerAssociationId(resellerAssociationId);
    }
}
