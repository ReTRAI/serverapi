package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tTicketDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItTicketDetailRepository extends JpaRepository<tTicketDetail, UUID> {


}
