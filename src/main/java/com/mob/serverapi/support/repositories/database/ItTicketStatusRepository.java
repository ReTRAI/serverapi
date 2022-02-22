package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tTicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItTicketStatusRepository extends JpaRepository<tTicketStatus, UUID> {

    tTicketStatus findByDescription(String description);

}
