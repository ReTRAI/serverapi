package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


interface ItUserRoleRepository extends JpaRepository<tUserRole, UUID> {

    tUserRole findByUser_UserIdAndUserType_UserTypeId(UUID userId, UUID userTypeId);

    long countByUser_UserIdAndUserType_UserTypeId(UUID userId, UUID userTypeId);

    List<tUserRole> findByUser_UserId(UUID userId);

}
