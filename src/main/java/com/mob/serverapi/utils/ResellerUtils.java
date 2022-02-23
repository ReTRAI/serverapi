package com.mob.serverapi.utils;


import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.mob.serverapi.reseller.base.*;
import com.mob.serverapi.reseller.database.tResellerBalance;

public  class ResellerUtils {

    public static Reseller transformReseller(tReseller reseller){

        Reseller r = new Reseller();
        r.setResellerId(reseller.getResellerId().toString());
        r.setUserId(reseller.getUser().getUserId().toString());
        r.setResellerName(reseller.getUser().getUserName());
        r.setCurrentBalance(reseller.getCurrentBalance());
        r.setTotalDevices(reseller.getTotalDevices());
        r.setActiveDevices(reseller.getActiveDevices());
        r.setInactiveDevices(reseller.getInactiveDevices());
        r.setFreeDevices(reseller.getFreeDevices());
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

    public static List<ResellerAssociation> transformResellerAssociationList(List<tResellerAssociation> resellerAssociations){

        List<ResellerAssociation> rs= new ArrayList<ResellerAssociation>();

        for (tResellerAssociation r: resellerAssociations) {
            ResellerAssociation newResellerAssoc = transformResellerAssociation(r);
            rs.add(newResellerAssoc);
        }

        return rs;
    }

    public static ResellerBalance transformResellerBalance(tResellerBalance resellerBalance){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ResellerBalance r = new ResellerBalance();
        r.setResellerBalanceId(resellerBalance.getResellerBalanceId().toString());
        r.setDebitCredit(resellerBalance.getDebitCredit());
        r.setMovementDate(resellerBalance.getMovementDate().format(formatter));
        r.setMovementValue(resellerBalance.getMovementValue());
        return r;
    }

    public static List<ResellerBalance> transformResellerBalanceList(List<tResellerBalance> resellerBalances){

        List<ResellerBalance> rs= new ArrayList<ResellerBalance>();

        for (tResellerBalance r: resellerBalances) {
            ResellerBalance newResellerBalance = transformResellerBalance(r);
            rs.add(newResellerBalance);
        }

        return rs;
    }


}
