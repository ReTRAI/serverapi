package com.mob.serverapi.users.repositories.database;

import com.mob.serverapi.device.database.tDeviceNotification;
import com.mob.serverapi.users.database.tUserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

interface ItUserNotificationRepository extends JpaRepository<tUserNotification, UUID> {

    List<tUserNotification> findByUser_UserIdOrderByCreationDateDesc(UUID userId);


}
