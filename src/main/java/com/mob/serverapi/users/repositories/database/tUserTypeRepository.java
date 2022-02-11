package com.mob.serverapi.users.repositories.database;//package com.mobile.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserType;
import com.mob.serverapi.users.repositories.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Component
public class tUserTypeRepository {

    @Autowired
    ItUserTypeRepository repository;

    public void savetUserType(tUserType userType){
        repository.save(userType);
    }

    public void createDefaultUserType(){


        List<tUserType.UserTypeEnum> descrs = new ArrayList<tUserType.UserTypeEnum>();
		descrs.add(tUserType.UserTypeEnum.ADMIN);
		descrs.add(tUserType.UserTypeEnum.RESELLER);
		descrs.add(tUserType.UserTypeEnum.SUPPORT);

        for (tUserType.UserTypeEnum d: descrs) {

            tUserType t = new tUserType();
            t.setDescription(d);

//            ExampleMatcher modelMatcher = ExampleMatcher.matching()
//                    .withIgnorePaths("id")
//                    .withMatcher("model", ignoreCase());
//
//            Example<tUserType> userType = Example.of(t, modelMatcher);
//
//            if(!repository.exists(userType)) {
//
//                savetUserType(t);
//            }

        }
    }
}
