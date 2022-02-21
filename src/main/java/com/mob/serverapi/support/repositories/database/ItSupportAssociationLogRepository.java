package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupportAssociationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

interface ItSupportAssociationLogRepository extends JpaRepository<tSupportAssociationLog, UUID> {

    @Transactional
    long deleteBySupportAssociationLogId(UUID supportAssociationLogId);


}
