package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


interface ItUserTypeRepository extends JpaRepository<tUserType, UUID> {
    tUserType findByDescription(String description);

}
