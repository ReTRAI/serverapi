package com.mob.serverapi.support.repositories.database;


import com.mob.serverapi.support.database.tTicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tTicketStatusRepository {

    @Autowired
    ItTicketStatusRepository repository;

    public void saveTicketStatus(tTicketStatus ticketStatus){
        repository.save(ticketStatus);
    }

    public tTicketStatus findTicketStatusByDescription(String description){
        return repository.findByDescription(description);
    }
    public void createDefaultTicketStatus(){


        List<tTicketStatus.TicketStatusEnum> descrs = new ArrayList<tTicketStatus.TicketStatusEnum>();
        descrs.add(tTicketStatus.TicketStatusEnum.OPEN);
        descrs.add(tTicketStatus.TicketStatusEnum.ONPROGRESS);
        descrs.add(tTicketStatus.TicketStatusEnum.PENDING);
        descrs.add(tTicketStatus.TicketStatusEnum.CANCELED);
        descrs.add(tTicketStatus.TicketStatusEnum.COMPLETED);


        for (tTicketStatus.TicketStatusEnum d: descrs) {

            tTicketStatus t = new tTicketStatus();
            t.setDescription(d.toString());

            ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                    .withIgnorePaths("id")
                    .withMatcher("description", exact().ignoreCase());

            Example<tTicketStatus> ticketStatus = Example.of(t, descriptionMatch);

            if(!repository.exists(ticketStatus)) {

                saveTicketStatus(t);
            }

        }
    }
}
