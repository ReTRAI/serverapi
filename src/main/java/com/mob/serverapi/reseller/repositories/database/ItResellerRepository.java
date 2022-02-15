package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

interface ItResellerRepository extends JpaRepository<tReseller,Integer>, JpaSpecificationExecutor<tReseller> {


}
