package com.mob.serverapi.reseller;


import com.mob.serverapi.reseller.base.*;
import com.mob.serverapi.reseller.repositories.endpoints.ResellerRepository;
import com.mob.serverapi.support.base.GetSupportParentByChildIdRequest;
import com.mob.serverapi.support.base.GetSupportParentByChildIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class ResellerEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/reseller/base";

    private ResellerRepository resellerRepository;

    @Autowired
    public ResellerEndpoint(ResellerRepository resellerRepository) {
        this.resellerRepository = resellerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerByIdRequest")
    @ResponsePayload
    public GetResellerByIdResponse getResellerById(@RequestPayload GetResellerByIdRequest request) {

        GetResellerByIdResponse response = new GetResellerByIdResponse();
        response.setReseller(resellerRepository.getResellerById(UUID.fromString(request.getResellerId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerByUserIdRequest")
    @ResponsePayload
    public GetResellerByUserIdResponse getResellerByUserId(@RequestPayload GetResellerByUserIdRequest request) {

        GetResellerByUserIdResponse response = new GetResellerByUserIdResponse();
        response.setReseller(resellerRepository.getResellerByUserId(UUID.fromString(request.getUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerByUserDeviceNameRequest")
    @ResponsePayload
    public GetResellerByUserDeviceNameResponse getResellerByUserDeviceName (@RequestPayload GetResellerByUserDeviceNameRequest request) {

        GetResellerByUserDeviceNameResponse response = new GetResellerByUserDeviceNameResponse();
        response.setReseller(resellerRepository.getResellerByUserDeviceName(request.getUserDeviceName()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerFilteredRequest")
    @ResponsePayload
    public GetResellerFilteredResponse getResellerFiltered(@RequestPayload GetResellerFilteredRequest request) {

        GetResellerFilteredResponse response = new GetResellerFilteredResponse();
        response.getReseller().addAll(resellerRepository.getResellerFiltered(request.getResellerId(),
                request.getResellerName(),request.isOnlyChildren(),request.getField(), request.getOrderField(),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAvailableResellerParentRequest")
    @ResponsePayload
    public GetAvailableResellerParentResponse getAvailableResellerParent (@RequestPayload GetAvailableResellerParentRequest request) {

        GetAvailableResellerParentResponse response = new GetAvailableResellerParentResponse();
        response.getReseller().addAll(resellerRepository.getAvailableResellerParent(UUID.fromString(request.getResellerId()),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountAvailableResellerParentRequest")
    @ResponsePayload
    public GetCountAvailableResellerParentResponse getCountAvailableResellerParent(@RequestPayload GetCountAvailableResellerParentRequest request) {

        GetCountAvailableResellerParentResponse response = new GetCountAvailableResellerParentResponse();
        response.setResult(resellerRepository.getCountAvailableResellerParent(UUID.fromString(request.getResellerId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerBalanceMovementsRequest")
    @ResponsePayload
    public GetResellerBalanceMovementsResponse getResellerBalanceMovements(@RequestPayload GetResellerBalanceMovementsRequest request) {

        GetResellerBalanceMovementsResponse response = new GetResellerBalanceMovementsResponse();
        response.getResellerBalance().addAll(resellerRepository.getResellerBalanceMovements
                (UUID.fromString(request.getResellerId()), request.getStartMovementDate(),request.getEndMovementDate(),
                        request.getMinValue(),request.getMaxValue(), request.getDebitCredit(),request.getMovementType(),
                        request.getField(),request.getOrderField(), request.getOffset(),request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountResellerBalanceMovementsRequest")
    @ResponsePayload
    public GetCountResellerBalanceMovementsResponse getCountResellerBalanceMovements
            (@RequestPayload GetCountResellerBalanceMovementsRequest request) {

        GetCountResellerBalanceMovementsResponse response = new GetCountResellerBalanceMovementsResponse();
        response.setResult(resellerRepository.getCountResellerBalanceMovements (UUID.fromString(request.getResellerId()),
                        request.getStartMovementDate(),request.getEndMovementDate(),
                        request.getMinValue(),request.getMaxValue(), request.getDebitCredit(),request.getMovementType()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountResellerFilteredRequest")
    @ResponsePayload
    public GetCountResellerFilteredResponse getCountResellerFiltered(@RequestPayload GetCountResellerFilteredRequest request) {

        GetCountResellerFilteredResponse response = new GetCountResellerFilteredResponse();
        response.setResult(resellerRepository.getCountResellerFiltered(request.getResellerId(),
                request.getResellerName(),request.isOnlyChildren()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetResellerRequest")
    @ResponsePayload
    public SetResellerResponse setReseller (@RequestPayload SetResellerRequest request) {

        SetResellerResponse response = new SetResellerResponse();
        response.setReseller(resellerRepository.setReseller(UUID.fromString(request.getUserId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetResellerBalanceMovementRequest")
    @ResponsePayload
    public SetResellerBalanceMovementResponse setResellerBalanceMovement (@RequestPayload SetResellerBalanceMovementRequest request) {

        SetResellerBalanceMovementResponse response = new SetResellerBalanceMovementResponse();
        response.setResult(resellerRepository.setResellerBalanceMovement(UUID.fromString(request.getResellerId()),
                request.getDebitCredit(),request.getMovementValue(),request.getMovementType(),
                request.getMovementDetail(), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerAssociationRequest")
    @ResponsePayload
    public GetResellerAssociationResponse getResellerAssociation(@RequestPayload GetResellerAssociationRequest request) {

        GetResellerAssociationResponse response = new GetResellerAssociationResponse();
        response.setResellerAssociation(resellerRepository.getResellerAssociation(UUID.fromString(request.getParentResellerId()),
                UUID.fromString(request.getChildResellerId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetResellerParentByChildIdRequest")
    @ResponsePayload
    public GetResellerParentByChildIdResponse getResellerParentByChildId(@RequestPayload GetResellerParentByChildIdRequest request) {

        GetResellerParentByChildIdResponse response = new GetResellerParentByChildIdResponse();
        response.setReseller(resellerRepository.getResellerParentByChildId(
                UUID.fromString(request.getChildResellerId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetResellerAssociationRequest")
    @ResponsePayload
    public SetResellerAssociationResponse setResellerAssociation (@RequestPayload SetResellerAssociationRequest request) {

        SetResellerAssociationResponse response = new SetResellerAssociationResponse();
        response.setResult(resellerRepository.setResellerAssociation(UUID.fromString(request.getParentResellerId()),
                UUID.fromString(request.getChildResellerId()), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemoveResellerRequest")
    @ResponsePayload
    public RemoveResellerResponse removeReseller (@RequestPayload RemoveResellerRequest request) {

        RemoveResellerResponse response = new RemoveResellerResponse();
        response.setResult(resellerRepository.removeReseller(UUID.fromString(request.getResellerId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemoveResellerAssociationRequest")
    @ResponsePayload
    public RemoveResellerAssociationResponse removeResellerAssociation (@RequestPayload RemoveResellerAssociationRequest request) {

        RemoveResellerAssociationResponse response = new RemoveResellerAssociationResponse();
        response.setResult(resellerRepository.removeResellerAssociation(UUID.fromString(request.getParentResellerId()),
                UUID.fromString(request.getChildResellerId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "IsHierarchyValidRequest")
    @ResponsePayload
    public IsHierarchyValidResponse isHierarchyValid (@RequestPayload IsHierarchyValidRequest request) {

        IsHierarchyValidResponse response = new IsHierarchyValidResponse();
        response.setResult(resellerRepository.isHierarchyValid(UUID.fromString(request.getResellerId()),
                UUID.fromString(request.getChildResellerId())));

        return response;
    }
}
