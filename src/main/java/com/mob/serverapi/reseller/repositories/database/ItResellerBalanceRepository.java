package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItResellerBalanceRepository extends JpaRepository<tResellerBalance, UUID>{

    List<tResellerBalance> findByReseller_ResellerIdOrderByMovementDateDesc(UUID resellerId);




}
