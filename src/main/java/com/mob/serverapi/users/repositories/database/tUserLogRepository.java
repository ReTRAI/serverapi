package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tUserLogRepository {

    @Autowired
    ItUserLogRepository repository;

    public tUserLog saveUserLog(tUserLog userLog){

        return repository.save(userLog);
    }


    public void insertUserLog(tUser actionUser, tUser alteredUser, String action, String detail){

        tUserLog log = new tUserLog();
        log.setActionUser(actionUser);
        log.setAlteredUser(alteredUser);
        log.setAction(action);
        log.setAlterationDate(LocalDateTime.now());
        log.setAlterationDetail(detail);

        saveUserLog(log);
    }


}
