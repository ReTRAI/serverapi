package com.mob.serverapi.utils;


import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.mob.serverapi.reseller.base.*;

public  class ResellerUtils {

    public static Reseller transformReseller(tReseller reseller){

        Reseller r = new Reseller();
        r.setResellerId(reseller.getResellerId().toString());
        r.setUserId(reseller.getUserId().getUserId().toString());
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

    public static ResellerAssociation transformResellerAssociation(tResellerAssociation resellerAssociation){

        ResellerAssociation r = new ResellerAssociation();
        r.setResellerAssociationId(resellerAssociation.getResellerAssociationId().toString());
        r.setParentResellerId(resellerAssociation.getParentReseller().getResellerId().toString());
        r.setChildResellerId(resellerAssociation.getChildReseller().getResellerId().toString());
        return r;
    }

    public static List<ResellerAssociation> transformResellerResellerAssociationList(List<tResellerAssociation> resellerAssociations){

        List<ResellerAssociation> rs= new ArrayList<ResellerAssociation>();

        for (tResellerAssociation r: resellerAssociations) {
            ResellerAssociation newResellerAssoc = transformResellerAssociation(r);
            rs.add(newResellerAssoc);
        }

        return rs;
    }


    public static int getResellerTotalDevices(UUID resellerId){
        return 0;
    }

    public static int getResellerActiveDevices(UUID resellerId){
        return 0;
    }

    public static int getResellerInactiveDevices(UUID resellerId){
        return 0;
    }

    public static int getResellerFreeDevices(UUID resellerId){
        return 0;
    }
}
