package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItUserLogRepository extends JpaRepository<tUserLog, UUID> {


}
