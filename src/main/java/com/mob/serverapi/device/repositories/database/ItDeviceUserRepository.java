package com.mob.serverapi.device.repositories.database;

import com.mob.serverapi.device.database.tDeviceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItDeviceUserRepository extends JpaRepository<tDeviceUser, UUID> {

    tDeviceUser findByNickname(String nickname);


}
