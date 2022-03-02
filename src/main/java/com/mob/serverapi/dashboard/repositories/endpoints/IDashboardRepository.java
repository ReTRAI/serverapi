package com.mob.serverapi.dashboard.repositories.endpoints;


import com.mob.serverapi.dashboard.base.Active;
import com.mob.serverapi.dashboard.base.Expiring;
import com.mob.serverapi.dashboard.base.Global;
import com.mob.serverapi.dashboard.base.Inactive;
import org.springframework.lang.Nullable;

import java.util.List;

interface IDashboardRepository {


    Active getActiveDashboardByResellerId(@Nullable String resellerIdId, @Nullable boolean recursive);

    Inactive getInactiveDashboardByResellerId(@Nullable String resellerIdId, @Nullable boolean recursive);

    Global getGlobalDashboardByResellerId(@Nullable String resellerIdId, @Nullable boolean recursive);

    Expiring getExpiringDashboardByResellerId(@Nullable String resellerIdId, @Nullable boolean recursive);

}
