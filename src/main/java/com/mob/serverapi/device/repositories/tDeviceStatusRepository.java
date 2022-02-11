package com.mob.serverapi.device.repositories;


import com.mob.serverapi.device.database.tDeviceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Component
public class tDeviceStatusRepository {

    @Autowired
    ItDeviceStatusRepository repository;

    public void savetDeviceStatus(tDeviceStatus deviceStatus){
        repository.save(deviceStatus);
    }

    public void createDefaultDeviceStatus(){


        List<tDeviceStatus.DeviceStatusEnum> descrs = new ArrayList<tDeviceStatus.DeviceStatusEnum>();
        descrs.add(tDeviceStatus.DeviceStatusEnum.ACTIVE);
        descrs.add(tDeviceStatus.DeviceStatusEnum.FREE);
        descrs.add(tDeviceStatus.DeviceStatusEnum.WIPED);
        descrs.add(tDeviceStatus.DeviceStatusEnum.BLOCKED);
        descrs.add(tDeviceStatus.DeviceStatusEnum.SUSPENDED);


        for (tDeviceStatus.DeviceStatusEnum d: descrs) {

            tDeviceStatus t = new tDeviceStatus();
            t.setDescription(d.toString());

            ExampleMatcher descriptionMatch = ExampleMatcher.matchingAny()
                    .withIgnorePaths("id")
                    .withMatcher("description", exact().ignoreCase());

            Example<tDeviceStatus> deviceStatus = Example.of(t, descriptionMatch);

            if(!repository.exists(deviceStatus)) {

                savetDeviceStatus(t);
            }

        }
    }
}
