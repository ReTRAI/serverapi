package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserStatus;
import com.mob.serverapi.users.database.tUserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ItUserRepository extends JpaRepository<tUser, UUID> {

    tUser findByUserEmail(String userEmail);

    tUser findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByUserEmail(String userEmail);



}
