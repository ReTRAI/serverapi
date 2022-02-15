package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.users.database.tUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<tReseller> getResellerFiltered(@Nullable Integer resellerId, @Nullable String resellerName, @Nullable String field,
                                               @Nullable String orderField, int offset, int numberRecords){

        Pageable pages = PageRequest.of(offset, numberRecords);

        if(orderField.equals("ASC"))
            pages = PageRequest.of(offset, numberRecords, Sort.by(field).ascending());
        if(orderField.equals("DESC"))
            pages = PageRequest.of(offset, numberRecords, Sort.by(field).descending());

        return repository.findDistinctByResellerIdAndUser_UserName(resellerId,resellerName, pages);
    }
}
