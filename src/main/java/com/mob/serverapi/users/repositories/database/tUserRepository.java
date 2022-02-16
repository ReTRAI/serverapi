package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tUserRepository {

    @Autowired
    ItUserRepository repository;

    public tUser saveUser(tUser user){

        return repository.save(user);
    }

    public tUser findById(UUID userId){

        return repository.findById(userId).orElse(null);
    }

    public tUser findByEmail(String email){

        return repository.findByUserEmail(email);
    }

    public boolean userExistsByField(tUser user, String field){

        boolean exists = false;

        ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                    .withIgnorePaths("id")
                    .withMatcher(field, exact().ignoreCase());

        Example<tUser> userExample = Example.of(user, descriptionMatch);

        exists =repository.exists(userExample);

        return exists;
    }
}
