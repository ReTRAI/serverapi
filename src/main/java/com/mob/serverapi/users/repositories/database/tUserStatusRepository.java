package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tUserStatusRepository {

    @Autowired
    ItUserStatusRepository repository;

    public void savetUserStatus(tUserStatus userStatus){
        repository.save(userStatus);
    }

    public void createDefaultUserStatus(){


        List<tUserStatus.UserStatusEnum> descrs = new ArrayList<tUserStatus.UserStatusEnum>();
        descrs.add(tUserStatus.UserStatusEnum.ACTIVE);
        descrs.add(tUserStatus.UserStatusEnum.INACTIVE);
        descrs.add(tUserStatus.UserStatusEnum.BLOCKED);
        descrs.add(tUserStatus.UserStatusEnum.CHANGEPW);

        for (tUserStatus.UserStatusEnum d: descrs) {

            tUserStatus t = new tUserStatus();
            t.setDescription(d.toString());

            ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                    .withIgnorePaths("id")
                    .withMatcher("description", exact().ignoreCase());

            Example<tUserStatus> userStatus = Example.of(t, descriptionMatch);

            if(!repository.exists(userStatus)) {

                savetUserStatus(t);
            }

        }
    }
}
