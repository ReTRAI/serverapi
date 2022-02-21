package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

interface ItSupportLogRepository extends JpaRepository<tSupportLog, UUID> {

    @Transactional
    long deleteBySupport_SupportId(UUID supportId);


}
