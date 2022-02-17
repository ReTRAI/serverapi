package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

interface ItResellerLogRepository extends JpaRepository<tResellerLog, UUID> {

    @Transactional
    long deleteByReseller_ResellerId(UUID resellerId);

}
