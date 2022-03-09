package com.mob.serverapi.notification;


import com.mob.serverapi.notification.repositories.endpoints.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class NotificationEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/notification/base";

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationEndpoint(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

}
