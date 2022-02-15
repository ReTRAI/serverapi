package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tResellerRepository {

    @Autowired
    ItResellerRepository repository;

    public tReseller saveReseller(tReseller reseller){

        return repository.save(reseller);
    }

    public void deleteResellerById(int resellerId){

        repository.deleteById(resellerId);
    }

    public tReseller findById(int resellerId){

        return repository.findById(resellerId).orElse(null);
    }

}
