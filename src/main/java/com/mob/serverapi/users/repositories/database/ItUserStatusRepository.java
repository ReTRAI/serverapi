package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserStatus;
import com.mob.serverapi.users.database.tUserType;
import org.springframework.data.jpa.repository.JpaRepository;

interface ItUserStatusRepository extends JpaRepository<tUserStatus,Integer> {

    tUserStatus findByDescription(String description);
}
