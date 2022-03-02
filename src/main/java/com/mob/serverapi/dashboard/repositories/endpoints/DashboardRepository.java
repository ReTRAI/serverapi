package com.mob.serverapi.dashboard.repositories.endpoints;

import com.mob.serverapi.dashboard.base.Active;
import com.mob.serverapi.dashboard.base.Expiring;
import com.mob.serverapi.dashboard.base.Global;
import com.mob.serverapi.dashboard.base.Inactive;

import java.util.List;


public class DashboardRepository implements IDashboardRepository {


    @Override
    public Active getActiveDashboardByResellerId(String resellerIdId, boolean recursive) {
        return null;
    }

    @Override
    public Inactive getInactiveDashboardByResellerId(String resellerIdId, boolean recursive) {
        return null;
    }

    @Override
    public Global getGlobalDashboardByResellerId(String resellerIdId, boolean recursive) {
        return null;
    }

    @Override
    public Expiring getExpiringDashboardByResellerId(String resellerIdId, boolean recursive) {
        return null;
    }
}

