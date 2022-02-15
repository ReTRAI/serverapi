package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class tUserLoginLogRepository {

    @Autowired
    ItUserLoginLogRepository repository;

    public tUserLoginLog saveUserLoginLog(tUserLoginLog userLoginLog){

        return repository.save(userLoginLog);
    }

    public tUserLoginLog getLastValid(int userId, boolean validAuthentication){

        return repository.findFirstByUser_UserIdAndValidAuthenticationOrderByLoginDateAsc(userId, validAuthentication);
    }

    public long countFailedLogins(int userId, boolean validAuthentication, LocalDateTime loginDate){

        return repository.countByUser_UserIdAndValidAuthenticationAndLoginDateGreaterThan(userId, validAuthentication,loginDate);
    }

    public void insertUserLoginLog(tUser user, boolean valid, String detail){

        tUserLoginLog log = new tUserLoginLog();
        log.setUser(user);
        log.setValidAuthentication(valid);
        log.setAuthenticationDetail(detail);
        log.setLoginDate(LocalDateTime.now());

        saveUserLoginLog(log);
    }
}
