package com.mob.serverapi.utils;


import com.mob.serverapi.reseller.base.Reseller;
import com.mob.serverapi.reseller.base.ResellerAssociation;
import com.mob.serverapi.reseller.base.ResellerBalance;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerBalance;
import com.mob.serverapi.support.base.Support;
import com.mob.serverapi.support.base.Ticket;
import com.mob.serverapi.support.database.tSupport;
import com.mob.serverapi.support.database.tTicket;

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

    public static Ticket transformTicket(tTicket ticket){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Ticket r = new Ticket();
        r.setTicketId(ticket.getTicketId().toString());
        r.setCreationDate(ticket.getOpenDate().format(formatter));
        r.setCreationUserId(ticket.getOpenUser().getUserId().toString());

        if(ticket.getAssignedUser() != null)
            r.setAssignedUserId(ticket.getAssignedUser().getUserId().toString());

        return r;
    }

    public static List<Ticket> transformTicketList(List<tTicket> ticket){

        List<Ticket> rs= new ArrayList<Ticket>();

        for (tTicket r: ticket) {
            Ticket newTicket = transformTicket(r);
            rs.add(newTicket);
        }

        return rs;
    }
}
