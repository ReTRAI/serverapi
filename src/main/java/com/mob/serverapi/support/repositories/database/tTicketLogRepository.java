package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupport;
import com.mob.serverapi.support.database.tSupportLog;
import com.mob.serverapi.support.database.tTicket;
import com.mob.serverapi.support.database.tTicketLog;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class tTicketLogRepository {

    @Autowired
    ItTicketLogRepository repository;

    public tTicketLog saveTicketLog(tTicketLog ticketLog) {

        return repository.save(ticketLog);
    }

    public void insertTicketLog(tUser actionUser, tTicket altered, String action, String detail) {

        tTicketLog log = new tTicketLog();
        log.setUser(actionUser);
        log.setTicket(altered);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveTicketLog(log);
    }

    @Transactional
    public void deleteByTicketId(UUID ticketId) {

        repository.deleteByTicket_TicketId(ticketId);
    }
}
