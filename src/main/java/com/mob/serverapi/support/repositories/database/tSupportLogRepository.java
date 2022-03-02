package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupport;
import com.mob.serverapi.support.database.tSupportLog;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class tSupportLogRepository {

    @Autowired
    ItSupportLogRepository repository;

    public tSupportLog saveResellerLog(tSupportLog supportLog) {

        return repository.save(supportLog);
    }

    public void insertSupportLog(tUser actionUser, tSupport altered, String action, String detail) {

        tSupportLog log = new tSupportLog();
        log.setUser(actionUser);
        log.setSupport(altered);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveResellerLog(log);
    }

    @Transactional
    public void deleteSupportLogBySupportId(UUID supportId) {

        repository.deleteBySupport_SupportId(supportId);
    }

}
