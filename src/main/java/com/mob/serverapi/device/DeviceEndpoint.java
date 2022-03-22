package com.mob.serverapi.device;


import com.mob.serverapi.device.base.*;
import com.mob.serverapi.device.repositories.endpoints.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class DeviceEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/device/base";

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceEndpoint(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDeviceByIdRequest")
    @ResponsePayload
    public GetDeviceByIdResponse getDeviceById(@RequestPayload GetDeviceByIdRequest request) {

        GetDeviceByIdResponse response = new GetDeviceByIdResponse();
        response.setDevice(deviceRepository.getDeviceById(UUID.fromString(request.getDeviceId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AssignDeviceRequest")
    @ResponsePayload
    public AssignDeviceResponse assignDevice(@RequestPayload AssignDeviceRequest request) {

        AssignDeviceResponse response = new AssignDeviceResponse();
        response.setDevice(deviceRepository.assignDevice(UUID.fromString(request.getDeviceId()),
                UUID.fromString(request.getResellerId()), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActivateDeviceRequest")
    @ResponsePayload
    public ActivateDeviceResponse activateDevice(@RequestPayload ActivateDeviceRequest request) {

        ActivateDeviceResponse response = new ActivateDeviceResponse();
        response.setDevice(deviceRepository.activateDevice(UUID.fromString(request.getDeviceId()),
                request.getOwnerNickname(), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BlockDeviceRequest")
    @ResponsePayload
    public BlockDeviceResponse blockDevice(@RequestPayload BlockDeviceRequest request) {

        BlockDeviceResponse response = new BlockDeviceResponse();
        response.setDevice(deviceRepository.blockDevice(UUID.fromString(request.getDeviceId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "WipeDeviceRequest")
    @ResponsePayload
    public WipeDeviceResponse wipeDevice(@RequestPayload WipeDeviceRequest request) {

        WipeDeviceResponse response = new WipeDeviceResponse();
        response.setDevice(deviceRepository.wipeDevice(UUID.fromString(request.getDeviceId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SuspendDeviceRequest")
    @ResponsePayload
    public SuspendDeviceResponse suspendDevice(@RequestPayload SuspendDeviceRequest request) {

        SuspendDeviceResponse response = new SuspendDeviceResponse();
        response.setDevice(deviceRepository.suspendDevice(UUID.fromString(request.getDeviceId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetDeviceRequest")
    @ResponsePayload
    public SetDeviceResponse setDevice(@RequestPayload SetDeviceRequest request) {

        SetDeviceResponse response = new SetDeviceResponse();
        response.setDevice(deviceRepository.setDevice(request.getBrand(), request.getModel(), request.getSerialNumber(),
                request.getImeiNumber(), request.getSimNumber(), request.getAndroidId(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetDeviceListRequest")
    @ResponsePayload
    public SetDeviceListResponse setDeviceList(@RequestPayload SetDeviceListRequest request) {

        SetDeviceListResponse response = new SetDeviceListResponse();
        response.getDevice().addAll(deviceRepository.setDeviceList(request.getDevice(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDevicesFilteredRequest")
    @ResponsePayload
    public GetDevicesFilteredResponse getDevicesFiltered(@RequestPayload GetDevicesFilteredRequest request) {

        GetDevicesFilteredResponse response = new GetDevicesFilteredResponse();
        response.getDevice().addAll(deviceRepository.getDevicesFiltered(request.getDeviceId(),
                request.getResellerId(), request.getStatus(), request.getStartCreationDate(),
                request.getEndCreationDate(), request.getStartActivationDate(),
                request.getEndActivationDate(), request.getStartExpirationDate(),
                request.getEndExpirationDate(), request.getField(), request.getOrderField(),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountDevicesFilteredRequest")
    @ResponsePayload
    public GetCountDevicesFilteredResponse getCountDevicesFiltered(@RequestPayload GetCountDevicesFilteredRequest request) {

        GetCountDevicesFilteredResponse response = new GetCountDevicesFilteredResponse();
        response.setResult(deviceRepository.getCountDevicesFiltered(request.getDeviceId(),
                request.getResellerId(), request.getStatus(), request.getStartCreationDate(),
                request.getEndCreationDate(), request.getStartActivationDate(),
                request.getEndActivationDate(), request.getStartExpirationDate(),
                request.getEndExpirationDate()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetDeviceNotesRequest")
    @ResponsePayload
    public SetDeviceNotesResponse setDeviceNotes(@RequestPayload SetDeviceNotesRequest request) {

        SetDeviceNotesResponse response = new SetDeviceNotesResponse();
        response.setDevice(deviceRepository.setDeviceNotes(UUID.fromString(request.getDeviceId()), request.getNotes(),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDeviceBalanceMovementsRequest")
    @ResponsePayload
    public GetDeviceBalanceMovementsResponse getDeviceBalanceMovements(@RequestPayload GetDeviceBalanceMovementsRequest request) {

        GetDeviceBalanceMovementsResponse response = new GetDeviceBalanceMovementsResponse();
        response.getDeviceBalance().addAll(deviceRepository.getDeviceBalanceMovements
                (UUID.fromString(request.getDeviceId()), request.getStartMovementDate(), request.getEndMovementDate(),
                        request.getMinValue(), request.getMaxValue(), request.getDebitCredit(), request.getField(), request.getOrderField(),
                        request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountDeviceBalanceMovementsRequest")
    @ResponsePayload
    public GetCountDeviceBalanceMovementsResponse getCountDeviceBalanceMovements
            (@RequestPayload GetCountDeviceBalanceMovementsRequest request) {

        GetCountDeviceBalanceMovementsResponse response = new GetCountDeviceBalanceMovementsResponse();
        response.setResult(deviceRepository.getCountDeviceBalanceMovements(UUID.fromString(request.getDeviceId()),
                request.getStartMovementDate(), request.getEndMovementDate(),
                request.getMinValue(), request.getMaxValue(), request.getDebitCredit()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetDeviceBalanceMovementRequest")
    @ResponsePayload
    public SetDeviceBalanceMovementResponse setDeviceBalanceMovement
            (@RequestPayload SetDeviceBalanceMovementRequest request) {

        SetDeviceBalanceMovementResponse response = new SetDeviceBalanceMovementResponse();
        response.setResult(deviceRepository.setDeviceBalanceMovement(UUID.fromString(request.getDeviceId()), request.getDebitCredit(),
                request.getMovementValue(), UUID.fromString(request.getActionUserId())));

        return response;
    }
}
