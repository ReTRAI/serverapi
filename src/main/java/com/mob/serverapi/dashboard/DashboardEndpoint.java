package com.mob.serverapi.dashboard;


import com.mob.serverapi.dashboard.base.GetDashboardByResellerIdRequest;
import com.mob.serverapi.dashboard.base.GetDashboardByResellerIdResponse;
import com.mob.serverapi.dashboard.repositories.endpoints.DashboardRepository;
import com.mob.serverapi.device.base.*;
import com.mob.serverapi.device.repositories.endpoints.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class DashboardEndpoint {

    private static final String NAMESPACE_URI = "http://www.mob.com/serverapi/dashboard/base";

    private DashboardRepository dashboardRepository;

    @Autowired
    public DashboardEndpoint(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetDashboardByResellerIdRequest")
    @ResponsePayload
    public GetDashboardByResellerIdResponse getDashboardByResellerId(@RequestPayload GetDashboardByResellerIdRequest request) {

        GetDashboardByResellerIdResponse response = new GetDashboardByResellerIdResponse();
        response.setActive(dashboardRepository.getActiveDashboardByResellerId
                (UUID.fromString(request.getResellerId()), request.isRecursive()));
        response.setExpiring(dashboardRepository.getExpiringDashboardByResellerId
                (UUID.fromString(request.getResellerId()), request.isRecursive()));
        response.setInactive(dashboardRepository.getInactiveDashboardByResellerId
                (UUID.fromString(request.getResellerId()),request.isRecursive()));
        response.setGlobal(dashboardRepository.getGlobalDashboardByResellerId
                (UUID.fromString(request.getResellerId()),request.isRecursive()));

        return response;
    }


}
