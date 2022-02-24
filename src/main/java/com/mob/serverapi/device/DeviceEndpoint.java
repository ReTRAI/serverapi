package com.mob.serverapi.device;


import com.mob.serverapi.device.base.*;
import com.mob.serverapi.device.repositories.endpoints.DeviceRepository;
import com.mob.serverapi.reseller.base.*;
import com.mob.serverapi.reseller.repositories.endpoints.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class DeviceEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/device/base";

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceEndpoint(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDeviceByIdRequest")
    @ResponsePayload
    public GetDeviceByIdResponse getDeviceById(@RequestPayload GetDeviceByIdRequest request) {

        GetDeviceByIdResponse response = new GetDeviceByIdResponse();
        response.setDevice(deviceRepository.getDeviceById(UUID.fromString(request.getDeviceId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setDeviceRequest")
    @ResponsePayload
    public SetDeviceResponse setDevice(@RequestPayload SetDeviceRequest request) {

        SetDeviceResponse response = new SetDeviceResponse();
        response.setDevice(deviceRepository.setDevice(request.getBrand(), request.getModel(), request.getSerialNumber(),
                request.getImeiNumber(),request.getSimNumber(),request.getAndroidId(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setDeviceListRequest")
    @ResponsePayload
    public SetDeviceListResponse setDeviceList(@RequestPayload SetDeviceListRequest request) {

        SetDeviceListResponse response = new SetDeviceListResponse();
        response.getDevice().addAll(deviceRepository.setDeviceList(request.getDevice(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDevicesFilteredRequest")
    @ResponsePayload
    public GetDevicesFilteredResponse getDevicesFiltered(@RequestPayload GetDevicesFilteredRequest request) {

        GetDevicesFilteredResponse response = new GetDevicesFilteredResponse();
        response.getDevice().addAll(deviceRepository.getDevicesFiltered(request.getDeviceId(),
                request.getResellerId(),request.getStatus(),request.getStartCreationDate(),
                request.getEndCreationDate(),request.getStartActivationDate(),
                request.getEndActivationDate(),request.getStartExpirationDate(),
                request.getEndExpirationDate(),request.getField(),request.getOrderField(),
                request.getOffset(),request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountDevicesFilteredRequest")
    @ResponsePayload
    public GetCountDevicesFilteredResponse getCountDevicesFiltered(@RequestPayload GetCountDevicesFilteredRequest request) {

        GetCountDevicesFilteredResponse response = new GetCountDevicesFilteredResponse();
        response.setResult(deviceRepository.getCountDevicesFiltered(request.getDeviceId(),
                request.getResellerId(),request.getStatus(),request.getStartCreationDate(),
                request.getEndCreationDate(),request.getStartActivationDate(),
                request.getEndActivationDate(),request.getStartExpirationDate(),
                request.getEndExpirationDate()));

        return response;
    }
}
