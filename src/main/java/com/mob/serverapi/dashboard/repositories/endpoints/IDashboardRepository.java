package com.mob.serverapi.dashboard.repositories.endpoints;


import com.mob.serverapi.dashboard.base.Active;
import com.mob.serverapi.dashboard.base.Expiring;
import com.mob.serverapi.dashboard.base.Global;
import com.mob.serverapi.dashboard.base.Inactive;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface IDashboardRepository {


    Active getActiveDashboardByResellerId(UUID resellerId, boolean recursive);

    Inactive getInactiveDashboardByResellerId(UUID resellerId, boolean recursive);

    Global getGlobalDashboardByResellerId(UUID resellerId, boolean recursive);

    Expiring getExpiringDashboardByResellerId(UUID resellerId, boolean recursive);

}
