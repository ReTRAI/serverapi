package com.mob.serverapi.support.repositories;

import com.mob.serverapi.support.database.tTicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

interface ItTicketStatusRepository extends JpaRepository<tTicketStatus,Integer> {
}
