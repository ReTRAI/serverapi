package com.mob.serverapi.reseller;


import com.mob.serverapi.reseller.base.*;
import com.mob.serverapi.reseller.repositories.endpoints.ResellerRepository;
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
    public GetResellerByIdResponse getResellerById(@RequestPayload GetResellerByIdRequest request) {

        GetResellerByIdResponse response = new GetResellerByIdResponse();
        response.setReseller(resellerRepository.getResellerById(request.getResellerId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getResellerFilteredRequest")
    @ResponsePayload
    public GetResellerFilteredResponse getResellerFiltered(@RequestPayload GetResellerFilteredRequest request) {

        GetResellerFilteredResponse response = new GetResellerFilteredResponse();
        response.getReseller().addAll(resellerRepository.getResellerFiltered(request.getResellerId(),
                request.getResellerName(),request.isOnlyChildren(),request.getField(), request.getOrderField(),
                request.getOffset(), request.getNumberRecords()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "setResellerRequest")
    @ResponsePayload
    public SetResellerResponse setReseller (@RequestPayload SetResellerRequest request) {

        SetResellerResponse response = new SetResellerResponse();
        response.setReseller(resellerRepository.setReseller(request.getUserId(), request.getActionUserId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeResellerRequest")
    @ResponsePayload
    public RemoveResellerResponse removeReseller (@RequestPayload RemoveResellerRequest request) {

        RemoveResellerResponse response = new RemoveResellerResponse();
        response.setResult(resellerRepository.removeReseller(request.getResellerId(),request.getActionUserId()));

        return response;
    }
}
