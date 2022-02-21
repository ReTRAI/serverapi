package com.mob.serverapi.support;


import com.mob.serverapi.support.base.*;
import com.mob.serverapi.support.repositories.endpoints.SupportRepository;
import com.mob.serverapi.users.base.GetCountUserFilteredRequest;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSupportByIdRequest")
    @ResponsePayload
    public GetSupportByIdResponse getSupportById(@RequestPayload GetSupportByIdRequest request) {

        GetSupportByIdResponse response = new GetSupportByIdResponse();
        response.setSupport(supportRepository.getSupportById(UUID.fromString(request.getSupportId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSupportByUserIdRequest")
    @ResponsePayload
    public GetSupportByUserIdResponse getSupportByUserId(@RequestPayload GetSupportByUserIdRequest request) {

        GetSupportByUserIdResponse response = new GetSupportByUserIdResponse();
        response.setSupport(supportRepository.getSupportByUserId(UUID.fromString(request.getUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSupportFilteredRequest")
    @ResponsePayload
    public GetSupportFilteredResponse getSupportFiltered(@RequestPayload GetSupportFilteredRequest request) {

        GetSupportFilteredResponse response = new GetSupportFilteredResponse();
        response.getSupport().addAll(supportRepository.getSupportFiltered(request.getSupportId(),
                request.getSupportName(), request.isOnlyChildren(), request.getField(), request.getOrderField(),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountSupportFilteredRequest")
    @ResponsePayload
    public GetCountSupportFilteredResponse getCountSupportFiltered(@RequestPayload GetCountSupportFilteredRequest request) {

        GetCountSupportFilteredResponse response = new GetCountSupportFilteredResponse();
        response.setResult(supportRepository.getCountSupportFiltered(request.getSupportId(),
                request.getSupportName(), request.isOnlyChildren()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTicketFilteredRequest")
    @ResponsePayload
    public GetTicketFilteredResponse getTicketFiltered(@RequestPayload GetTicketFilteredRequest request) {

        GetTicketFilteredResponse response = new GetTicketFilteredResponse();
        response.getTicket().addAll(supportRepository.getTicketFiltered(request.getTicketId(),
                request.getTicketStatus(),request.getStartCreationDate(),request.getEndCreationDate(),
                request.getField(),request.getOrderField(),request.getOffset(),request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountTicketFilteredRequest")
    @ResponsePayload
    public GetCountTicketFilteredResponse getCountTicketFiltered(@RequestPayload GetCountTicketFilteredRequest request) {

        GetCountTicketFilteredResponse response = new GetCountTicketFilteredResponse();
        response.setResult(supportRepository.getCountTicketFiltered(request.getTicketId(),request.getTicketStatus(),
                request.getStartCreationDate(), request.getEndCreationDate()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setSupportRequest")
    @ResponsePayload
    public SetSupportResponse setSupport(@RequestPayload SetSupportRequest request) {

        SetSupportResponse response = new SetSupportResponse();
        response.setSupport(supportRepository.setSupport(UUID.fromString(request.getUserId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeSupportRequest")
    @ResponsePayload
    public RemoveSupportResponse removeSupport(@RequestPayload RemoveSupportRequest request) {

        RemoveSupportResponse response = new RemoveSupportResponse();
        response.setResult(supportRepository.removeSupport(UUID.fromString(request.getSupportId()),
                UUID.fromString(request.getActionUserId())));

        return response;
    }

}
