package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;

interface ItResellerRepository extends JpaRepository<tReseller,Integer> {

    List<tReseller> findDistinctByResellerIdAndUser_UserName(@Nullable int resellerId, @Nullable String userName,
                                                             Pageable pageable);




}
