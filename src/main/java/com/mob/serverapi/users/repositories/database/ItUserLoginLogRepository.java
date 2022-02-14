package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

interface ItUserLoginLogRepository extends JpaRepository<tUserLoginLog,Integer> {

    tUserLoginLog findFirstByUser_UserIdAndValidAuthenticationOrderByLoginDateAsc(int userId, boolean validAuthentication);

    long countByUser_UserIdAndValidAuthenticationAndLoginDateGreaterThan(int userId, boolean validAuthentication, LocalDateTime loginDate);


}
