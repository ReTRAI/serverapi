package com.mob.serverapi.reseller;


import com.mob.serverapi.reseller.base.GetResellerByIdResponse;
import com.mob.serverapi.reseller.base.GetResellerByRequest;
import com.mob.serverapi.reseller.repositories.endpoints.ResellerRepository;
import com.mob.serverapi.users.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ResellerEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/reseller/base";

    private ResellerRepository resellerRepository;

    @Autowired
    public ResellerEndpoint(ResellerRepository resellerRepository) {
        this.resellerRepository = resellerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getResellerByIdRequest")
    @ResponsePayload
    public GetResellerByIdResponse getResellerByIdRequest(@RequestPayload GetResellerByRequest request) {

        GetResellerByIdResponse response = new GetResellerByIdResponse();
        response.setReseller(resellerRepository.getResellerById(request.getResellerId()));

        return response;
    }

}
