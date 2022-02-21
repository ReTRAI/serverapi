package com.mob.serverapi.utils;


import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.base.ResellerAssociation;
import com.mob.serverapi.reseller.base.ResellerBalance;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerBalance;
import com.mob.serverapi.support.base.Support;
import com.mob.serverapi.support.database.tSupport;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public  class SupportUtils {

    public static Support transformSupport(tSupport support){

        Support r = new Support();
        r.setSupportId(support.getSupportId().toString());
        r.setUserId(support.getUser().getUserId().toString());
        r.setSupportName(support.getUser().getUserName());
        return r;
    }

    public static List<Support> transformSupportList(List<tSupport> support){

        List<Support> rs= new ArrayList<Support>();

        for (tSupport r: support) {
            Support newSupport = transformSupport(r);
            rs.add(newSupport);
        }

        return rs;
    }


}
