package com.mob.serverapi.notification.repositories.endpoints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class NotificationRepository implements INotificationRepository {

    @PersistenceContext
    protected EntityManager entityManager;

}

