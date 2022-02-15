package com.mob.serverapi.utils;


import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.database.tReseller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public  class ResellerUtils {

    public static Reseller transformReseller(tReseller reseller){

        Reseller r = new Reseller();
        r.setResellerId(reseller.getResellerId());
        r.setUserId(reseller.getUserId().getUserId());
        r.setResellerName(reseller.getUserId().getUserName());
        r.setCurrentBalance(BigDecimal.valueOf(reseller.getCurrentBalance()));
        r.setTotalDevices(getResellerTotalDevices(reseller.getResellerId()));
        r.setActiveDevices(getResellerActiveDevices(reseller.getResellerId()));
        r.setInactiveDevices(getResellerInactiveDevices(reseller.getResellerId()));
        r.setFreeDevices(getResellerFreeDevices(reseller.getResellerId()));
        return r;
    }

    public static List<Reseller> transformResellerList(List<tReseller> reseller){

        List<Reseller> rs= new ArrayList<Reseller>();

        for (tReseller r: reseller) {
            Reseller newReseller = transformReseller(r);
            rs.add(newReseller);
        }

        return rs;
    }

    public static int getResellerTotalDevices(int resellerId){
        return 0;
    }

    public static int getResellerActiveDevices(int resellerId){
        return 0;
    }

    public static int getResellerInactiveDevices(int resellerId){
        return 0;
    }
    public static int getResellerFreeDevices(int resellerId){
        return 0;
    }
}
