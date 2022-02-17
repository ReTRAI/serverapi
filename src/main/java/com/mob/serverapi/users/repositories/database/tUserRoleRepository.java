package com.mob.serverapi.users.repositories.database;//package com.mobile.serverapi.users.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.users.database.tUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tUserRoleRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    ItUserRoleRepository repository;

    public void saveUserRole(tUserRole userRole){
        repository.save(userRole);
    }

    public void deleteUserRoleById(UUID userRoleId){
        repository.deleteById(userRoleId);
    }

    public tUserRole findByUserIdAndUserTypeId(UUID userId, UUID userTypeId){
        return repository.findByUser_UserIdAndUserType_UserTypeId(userId,userTypeId);
    }

    public long countByUserIdAndUserTypeId(UUID userId, UUID userTypeId){
        return repository.countByUser_UserIdAndUserType_UserTypeId(userId,userTypeId);
    }

    public List<tUserRole> findAllRolesByUserId(UUID userId){
        return repository.findByUser_UserId(userId);
    }


}
