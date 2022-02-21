package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItTicketRepository extends JpaRepository<tTicket, UUID> {

    tTicket findByOpenUser_UserId(UUID userId);

    tTicket findByAssignedUser_UserId(UUID userId);


}
