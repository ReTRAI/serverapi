package com.mob.serverapi.reseller.repositories.database;

import com.mob.serverapi.reseller.database.tResellerMovementType;
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
public class tResellerMovementTypeRepository {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    ItResellerMovementTypeRepository repository;

    public void saveResellerMovementType(tResellerMovementType resellerMovementType) {
        repository.save(resellerMovementType);
    }

    public void createDefaultResellerMovementType() {


        List<tResellerMovementType.ResellerMovementTypeEnum> descrs = new ArrayList<tResellerMovementType.ResellerMovementTypeEnum>();
        descrs.add(tResellerMovementType.ResellerMovementTypeEnum.MANUAL_DEBIT);
        descrs.add(tResellerMovementType.ResellerMovementTypeEnum.MANUAL_CREDIT);
        descrs.add(tResellerMovementType.ResellerMovementTypeEnum.TRANSFER);
        descrs.add(tResellerMovementType.ResellerMovementTypeEnum.ACTIVATE_DEVICE);

        for (tResellerMovementType.ResellerMovementTypeEnum d : descrs) {

            tResellerMovementType t = new tResellerMovementType();
            t.setDescription(d.toString());

            ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                    .withIgnorePaths("id")
                    .withMatcher("description", exact().ignoreCase());

            Example<tResellerMovementType> userType = Example.of(t, descriptionMatch);

            if (!repository.exists(userType)) {

                saveResellerMovementType(t);
            }

        }
    }

    public tResellerMovementType findResellerMovementTypeByDescription(String description) {

        return repository.findByDescription(description);
    }

}
