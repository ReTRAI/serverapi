package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerLog;
import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class tResellerLogRepository {

    @Autowired
    ItResellerLogRepository repository;

    public tResellerLog saveResellerLog(tResellerLog resellerLog) {

        return repository.save(resellerLog);
    }

    public void insertResellerLog(tUser actionUser, tReseller alteredUser, String action, String detail) {

        tResellerLog log = new tResellerLog();
        log.setUser(actionUser);
        log.setAlteredId(alteredUser);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveResellerLog(log);
    }
}
