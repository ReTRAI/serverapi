package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface ItUserRoleRepository extends JpaRepository<tUserRole,Integer> {

    tUserRole findByUser_UserIdAndUserType_UserTypeId(int userId, int userTypeId);

    long countByUser_UserIdAndUserType_UserTypeId(int userId, int userTypeId);

    List<tUserRole> findByUser_UserId(int userId);

}
