package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

interface ItUserLoginLogRepository extends JpaRepository<tUserLoginLog,Integer> {

    tUserLoginLog findFirstByUser_UserIdAndValidAuthenticationOrderByLoginDateDesc(UUID userId, boolean validAuthentication);

    long countByUser_UserIdAndValidAuthenticationAndLoginDateGreaterThan(UUID userId, boolean validAuthentication, LocalDateTime loginDate);


}
