package com.mob.serverapi.notification;


import com.mob.serverapi.device.base.GetDevicesFilteredRequest;
import com.mob.serverapi.device.base.GetDevicesFilteredResponse;
import com.mob.serverapi.notification.base.*;
import com.mob.serverapi.notification.repositories.endpoints.NotificationRepository;
import com.mob.serverapi.users.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class NotificationEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/notification/base";

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationEndpoint(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetDeviceNotificationRequest")
    @ResponsePayload
    public SetDeviceNotificationResponse setDeviceNotification(@RequestPayload SetDeviceNotificationRequest request) {

        SetDeviceNotificationResponse response = new SetDeviceNotificationResponse();
        response.setDeviceNotification(notificationRepository.setDeviceNotification(UUID.fromString(request.getDeviceId()),
                request.getDetail(), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetDeviceNotificationCheckedRequest")
    @ResponsePayload
    public SetDeviceNotificationCheckedResponse setDeviceNotificationChecked(@RequestPayload SetDeviceNotificationCheckedRequest request) {

        SetDeviceNotificationCheckedResponse response = new SetDeviceNotificationCheckedResponse();
        response.setDeviceNotification(notificationRepository.setDeviceNotificationChecked(UUID.fromString(request.getDeviceNotificationId()),
                 UUID.fromString(request.getActionUserId())));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDeviceNotificationFilteredRequest")
    @ResponsePayload
    public GetDeviceNotificationFilteredResponse getDeviceNotificationFiltered(@RequestPayload GetDeviceNotificationFilteredRequest request) {

        GetDeviceNotificationFilteredResponse response = new GetDeviceNotificationFilteredResponse();
        response.getDeviceNotification().addAll(notificationRepository.getDeviceNotificationFiltered(
                request.getDeviceId(), request.getStartCreationDate(), request.getEndCreationDate(),
                request.getChecked(), request.getStartCheckedDate(),request.getEndCheckedDate(),
                request.getField(),request.getOrderField(),
                request.getOffset(),request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountDeviceNotificationFilteredRequest")
    @ResponsePayload
    public GetCountDeviceNotificationFilteredResponse getCountDeviceNotificationFiltered(@RequestPayload GetCountDeviceNotificationFilteredRequest request) {

        GetCountDeviceNotificationFilteredResponse response = new GetCountDeviceNotificationFilteredResponse();
        response.setResult(notificationRepository.getCountDeviceNotificationFiltered(
                request.getDeviceId(), request.getStartCreationDate(), request.getEndCreationDate(),
                request.getChecked(), request.getStartCheckedDate(),request.getEndCheckedDate()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetUserNotificationRequest")
    @ResponsePayload
    public SetUserNotificationResponse setUserNotification(@RequestPayload SetUserNotificationRequest request) {

        SetUserNotificationResponse response = new SetUserNotificationResponse();
        response.setUserNotification(notificationRepository.setUserNotification(UUID.fromString(request.getUserId()),
                request.getDetail(), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetUserNotificationCheckedRequest")
    @ResponsePayload
    public SetUserNotificationCheckedResponse setUserNotificationChecked(@RequestPayload SetUserNotificationCheckedRequest request) {

        SetUserNotificationCheckedResponse response = new SetUserNotificationCheckedResponse();
        response.setUserNotification(notificationRepository.setUserNotificationChecked(UUID.fromString(request.getUserNotificationId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserNotificationFilteredRequest")
    @ResponsePayload
    public GetUserNotificationFilteredResponse getUserNotificationFiltered(@RequestPayload GetUserNotificationFilteredRequest request) {

        GetUserNotificationFilteredResponse response = new GetUserNotificationFilteredResponse();
        response.getUserNotification().addAll(notificationRepository.getUserNotificationFiltered(
                request.getUserId(), request.getStartCreationDate(), request.getEndCreationDate(),
                request.getChecked(), request.getStartCheckedDate(),request.getEndCheckedDate(),
                request.getField(),request.getOrderField(),
                request.getOffset(),request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountUserNotificationFilteredRequest")
    @ResponsePayload
    public GetCountUserNotificationFilteredResponse GetCountUserNotificationFiltered(@RequestPayload GetCountUserNotificationFilteredRequest request) {

        GetCountUserNotificationFilteredResponse response = new GetCountUserNotificationFilteredResponse();
        response.setResult(notificationRepository.getCountUserNotificationFiltered(
                request.getUserId(), request.getStartCreationDate(), request.getEndCreationDate(),
                request.getChecked(), request.getStartCheckedDate(),request.getEndCheckedDate()));

        return response;
    }
}
