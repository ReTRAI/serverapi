package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import org.springframework.data.jpa.repository.JpaRepository;

interface ItResellerRepository extends JpaRepository<tReseller,Integer> {

}
