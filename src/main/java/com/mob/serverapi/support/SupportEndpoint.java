package com.mob.serverapi.support;


import com.mob.serverapi.support.base.*;
import com.mob.serverapi.support.repositories.endpoints.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class SupportEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/support/base";

    private final SupportRepository supportRepository;

    @Autowired
    public SupportEndpoint(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupportByIdRequest")
    @ResponsePayload
    public GetSupportByIdResponse getSupportById(@RequestPayload GetSupportByIdRequest request) {

        GetSupportByIdResponse response = new GetSupportByIdResponse();
        response.setSupport(supportRepository.getSupportById(UUID.fromString(request.getSupportId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupportByUserIdRequest")
    @ResponsePayload
    public GetSupportByUserIdResponse getSupportByUserId(@RequestPayload GetSupportByUserIdRequest request) {

        GetSupportByUserIdResponse response = new GetSupportByUserIdResponse();
        response.setSupport(supportRepository.getSupportByUserId(UUID.fromString(request.getUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupportFilteredRequest")
    @ResponsePayload
    public GetSupportFilteredResponse getSupportFiltered(@RequestPayload GetSupportFilteredRequest request) {

        GetSupportFilteredResponse response = new GetSupportFilteredResponse();
        response.getSupport().addAll(supportRepository.getSupportFiltered(request.getSupportId(),
                request.getSupportName(), request.isOnlyChildren(), request.getField(), request.getOrderField(),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountSupportFilteredRequest")
    @ResponsePayload
    public GetCountSupportFilteredResponse getCountSupportFiltered(@RequestPayload GetCountSupportFilteredRequest request) {

        GetCountSupportFilteredResponse response = new GetCountSupportFilteredResponse();
        response.setResult(supportRepository.getCountSupportFiltered(request.getSupportId(),
                request.getSupportName(), request.isOnlyChildren()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketFilteredRequest")
    @ResponsePayload
    public GetTicketFilteredResponse getTicketFiltered(@RequestPayload GetTicketFilteredRequest request) {

        GetTicketFilteredResponse response = new GetTicketFilteredResponse();
        response.getTicket().addAll(supportRepository.getTicketFiltered(request.getTicketId(),
                request.getTicketStatus(), request.getStartCreationDate(), request.getEndCreationDate(),
                request.getField(), request.getOrderField(), request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountTicketFilteredRequest")
    @ResponsePayload
    public GetCountTicketFilteredResponse getCountTicketFiltered(@RequestPayload GetCountTicketFilteredRequest request) {

        GetCountTicketFilteredResponse response = new GetCountTicketFilteredResponse();
        response.setResult(supportRepository.getCountTicketFiltered(request.getTicketId(), request.getTicketStatus(),
                request.getStartCreationDate(), request.getEndCreationDate()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTicketDetailFilteredRequest")
    @ResponsePayload
    public GetTicketDetailFilteredResponse getTicketDetailFiltered(@RequestPayload GetTicketDetailFilteredRequest request) {

        GetTicketDetailFilteredResponse response = new GetTicketDetailFilteredResponse();
        response.getTicketDetail().addAll(supportRepository.getTicketDetailFiltered(request.getTicketId(),
                request.getStartDetailDate(), request.getEndDetailDate(),
                request.getField(), request.getOrderField(), request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountTicketDetailFilteredRequest")
    @ResponsePayload
    public GetCountTicketDetailFilteredResponse getCountTicketDetailFiltered(@RequestPayload GetCountTicketDetailFilteredRequest request) {

        GetCountTicketDetailFilteredResponse response = new GetCountTicketDetailFilteredResponse();
        response.setResult(supportRepository.getCountTicketDetailFiltered(request.getTicketId(),
                request.getStartDetailDate(), request.getEndDetailDate()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetSupportRequest")
    @ResponsePayload
    public SetSupportResponse setSupport(@RequestPayload SetSupportRequest request) {

        SetSupportResponse response = new SetSupportResponse();
        response.setSupport(supportRepository.setSupport(UUID.fromString(request.getUserId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemoveSupportRequest")
    @ResponsePayload
    public RemoveSupportResponse removeSupport(@RequestPayload RemoveSupportRequest request) {

        RemoveSupportResponse response = new RemoveSupportResponse();
        response.setResult(supportRepository.removeSupport(UUID.fromString(request.getSupportId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupportAssociationRequest")
    @ResponsePayload
    public GetSupportAssociationResponse getSupportAssociation(@RequestPayload GetSupportAssociationRequest request) {

        GetSupportAssociationResponse response = new GetSupportAssociationResponse();
        response.setSupportAssociation(supportRepository.getSupportAssociation(
                UUID.fromString(request.getParentSupportId()),
                UUID.fromString(request.getChildSupportId())));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAvailableSupportParentRequest")
    @ResponsePayload
    public GetAvailableSupportParentResponse getAvailableSupportParent(@RequestPayload GetAvailableSupportParentRequest request) {

        GetAvailableSupportParentResponse response = new GetAvailableSupportParentResponse();
        response.getSupport().addAll(supportRepository.getAvailableSupportParent(UUID.fromString(request.getSupportId()),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCountAvailableSupportParentRequest")
    @ResponsePayload
    public GetCountAvailableSupportParentResponse getCountAvailableSupportParent
            (@RequestPayload GetCountAvailableSupportParentRequest request) {

        GetCountAvailableSupportParentResponse response = new GetCountAvailableSupportParentResponse();
        response.setResult(supportRepository.getCountAvailableSupportParent(UUID.fromString(request.getSupportId())));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupportParentByChildIdRequest")
    @ResponsePayload
    public GetSupportParentByChildIdResponse getSupportParentByChildId(@RequestPayload GetSupportParentByChildIdRequest request) {

        GetSupportParentByChildIdResponse response = new GetSupportParentByChildIdResponse();
        response.setSupport(supportRepository.getSupportParentByChildId(
                UUID.fromString(request.getChildSupportId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetSupportAssociationRequest")
    @ResponsePayload
    public SetSupportAssociationResponse setSupportAssociation(@RequestPayload SetSupportAssociationRequest request) {

        SetSupportAssociationResponse response = new SetSupportAssociationResponse();
        response.setResult(supportRepository.setSupportAssociation(UUID.fromString(request.getParentSupportId()),
                UUID.fromString(request.getChildSupportId()), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetTicketRequest")
    @ResponsePayload
    public SetTicketResponse setTicket(@RequestPayload SetTicketRequest request) {

        SetTicketResponse response = new SetTicketResponse();
        response.setTicket(supportRepository.setTicket(request.getMessage(), UUID.fromString(request.getCreationUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SetTicketDetailRequest")
    @ResponsePayload
    public SetTicketDetailResponse setTicketDetail(@RequestPayload SetTicketDetailRequest request) {

        SetTicketDetailResponse response = new SetTicketDetailResponse();
        response.setResult(supportRepository.setTicketDetail(UUID.fromString(request.getTicketId()),
                request.getMessage(), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateTicketRequest")
    @ResponsePayload
    public UpdateTicketResponse updateTicket(@RequestPayload UpdateTicketRequest request) {

        UpdateTicketResponse response = new UpdateTicketResponse();
        response.setResult(supportRepository.updateTicket(UUID.fromString(request.getTicketId()),
                request.getStatus(), request.getAssignedUserId(), UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RemoveSupportAssociationRequest")
    @ResponsePayload
    public RemoveSupportAssociationResponse removeSupportAssociation(@RequestPayload RemoveSupportAssociationRequest request) {

        RemoveSupportAssociationResponse response = new RemoveSupportAssociationResponse();
        response.setResult(supportRepository.removeSupportAssociation(UUID.fromString(request.getParentSupportId()),
                UUID.fromString(request.getChildSupportId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "IsHierarchyValidRequest")
    @ResponsePayload
    public IsHierarchyValidResponse isHierarchyValid(@RequestPayload IsHierarchyValidRequest request) {

        IsHierarchyValidResponse response = new IsHierarchyValidResponse();
        response.setResult(supportRepository.isHierarchyValid(UUID.fromString(request.getSupportId()),
                UUID.fromString(request.getChildSupportId())));

        return response;
    }
}
