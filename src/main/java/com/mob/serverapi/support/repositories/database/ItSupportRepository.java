package com.mob.serverapi.support.repositories.database;

import com.mob.serverapi.support.database.tSupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

interface ItSupportRepository extends JpaRepository<tSupport, UUID> {

    tSupport findByUser_UserId(UUID userId);

    List<tSupport> findBySupportIdNotIn(Collection<UUID> supportIds);



}
