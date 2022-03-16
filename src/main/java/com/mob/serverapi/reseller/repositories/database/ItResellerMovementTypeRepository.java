package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerMovementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


interface ItResellerMovementTypeRepository extends JpaRepository<tResellerMovementType, UUID> {

    tResellerMovementType findByDescription(String description);

}
