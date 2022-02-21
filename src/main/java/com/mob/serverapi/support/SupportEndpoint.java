package com.mob.serverapi.support;


import com.mob.serverapi.support.base.GetSupportByIdRequest;
import com.mob.serverapi.support.base.GetSupportByIdResponse;
import com.mob.serverapi.support.base.GetSupportByUserIdRequest;
import com.mob.serverapi.support.base.GetSupportByUserIdResponse;
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

}
