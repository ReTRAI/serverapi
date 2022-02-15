package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserType;
import org.springframework.data.jpa.repository.JpaRepository;


interface ItUserTypeRepository extends JpaRepository<tUserType,Integer> {

    tUserType findByDescription(String description);
}
