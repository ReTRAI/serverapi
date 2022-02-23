package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerAssociationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

interface ItResellerAssociationLogRepository extends JpaRepository<tResellerAssociationLog, UUID> {


    @Transactional
    long deleteByResellerAssociation_ResellerAssociationId(UUID resellerAssociationId);


}
