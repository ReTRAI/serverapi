package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

interface ItResellerLogRepository extends JpaRepository<tResellerLog,Integer> {

    @Transactional
    long deleteByReseller_ResellerId(int resellerId);

}
