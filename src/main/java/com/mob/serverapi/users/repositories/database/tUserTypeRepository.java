package com.mob.serverapi.users.repositories.database;//package com.mobile.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tUserTypeRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    ItUserTypeRepository repository;

    public void saveUserType(tUserType userType){
        repository.save(userType);
    }

    public void createDefaultUserType(){


        List<tUserType.UserTypeEnum> descrs = new ArrayList<tUserType.UserTypeEnum>();
		descrs.add(tUserType.UserTypeEnum.ADMIN);
		descrs.add(tUserType.UserTypeEnum.RESELLER);
		descrs.add(tUserType.UserTypeEnum.SUPPORT);

        for (tUserType.UserTypeEnum d: descrs) {

            tUserType t = new tUserType();
            t.setDescription(d.toString());

            ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                    .withIgnorePaths("id")
                    .withMatcher("description", exact().ignoreCase());

            Example<tUserType> userType = Example.of(t, descriptionMatch);

            if(!repository.exists(userType)) {

                saveUserType(t);
            }

        }
    }

    public tUserType findUserTypeByDescription(String description){

        return repository.findByDescription(description);
    }

}
