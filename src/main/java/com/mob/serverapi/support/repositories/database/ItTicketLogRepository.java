package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tTicketLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

interface ItTicketLogRepository extends JpaRepository<tTicketLog, UUID> {

    @Transactional
    long deleteByTicket_TicketId(UUID ticketId);




}
