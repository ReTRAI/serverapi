package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUser;
import com.mob.serverapi.users.database.tUserType;
import org.springframework.data.jpa.repository.JpaRepository;

interface ItUserRepository extends JpaRepository<tUser,Integer> {


}
