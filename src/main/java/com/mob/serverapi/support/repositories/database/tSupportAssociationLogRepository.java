package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupportAssociation;
import com.mob.serverapi.support.database.tSupportAssociationLog;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class tSupportAssociationLogRepository {

    @Autowired
    ItSupportAssociationLogRepository repository;

    public tSupportAssociationLog saveSupportLog(tSupportAssociationLog supportAssociationLog) {

        return repository.save(supportAssociationLog);
    }

    public void insertSupportAssociationLog(tUser actionUser, tSupportAssociation alteredUser, String action, String detail) {

        tSupportAssociationLog log = new tSupportAssociationLog();
        log.setUser(actionUser);
        log.setAlteredId(alteredUser);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveSupportLog(log);
    }

    @Transactional
    public void deleteSupportAssociationLogByResellerId(UUID supportAssociationId) {

        repository.deleteBySupportAssociationLogId(supportAssociationId);
    }
}
