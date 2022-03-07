package com.mob.serverapi.support.repositories.endpoints;


import com.mob.serverapi.support.base.Support;
import com.mob.serverapi.support.base.SupportAssociation;
import com.mob.serverapi.support.base.Ticket;
import com.mob.serverapi.support.base.TicketDetail;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

interface ISupportRepository {

    Support getSupportById(UUID supportId);

    Support getSupportByUserId(UUID userId);

    Support setSupport(UUID userId, UUID actionUserId);

    boolean removeSupport(UUID supportId, UUID actionUserId);


    List<Support> getSupportFiltered(@Nullable String supportId, @Nullable String supportName,
                                     boolean recursive, @Nullable String field, @Nullable String orderField,
                                     int offset, int numberRecords);

    long getCountSupportFiltered(@Nullable String supportId, @Nullable String supportName,
                                 boolean recursive);


    List<Ticket> getTicketFiltered(@Nullable String ticketId, @Nullable String status,
                                   @Nullable String startCreationDate, @Nullable String endCreationDate,
                                    @Nullable String field, @Nullable String orderField,
                                    int offset, int numberRecords);

    long getCountTicketFiltered(@Nullable String ticketId, @Nullable String status,
                                 @Nullable String startCreationDate, @Nullable String endCreationDate);

    List<TicketDetail> getTicketDetailFiltered(@Nullable String ticketId,
                                         @Nullable String startCreationDate, @Nullable String endCreationDate,
                                         @Nullable String field, @Nullable String orderField,
                                         int offset, int numberRecords);

    long getCountTicketDetailFiltered(@Nullable String ticketId,
                                @Nullable String startCreationDate, @Nullable String endCreationDate);

    boolean setSupportAssociation(UUID parentSupportId, UUID childSupportId, UUID actionUserId);

    boolean removeSupportAssociation(UUID parentSupportId, UUID childSupportId, UUID actionUserId);

    SupportAssociation getSupportAssociation(UUID parentSupportId, UUID childSupportId);

    Support getSupportParentByChildId(UUID childSupportId);

    Ticket setTicket(String message,UUID creationUserId);

    boolean updateTicket(UUID ticketId, @Nullable String status, @Nullable String assignedUserId, UUID actionUserId);

    boolean setTicketDetail(UUID ticketId,String message,UUID actionUserId);

    List<Support> getAvailableSupportParent(UUID supportId, int offset, int numberRecords);

    boolean isHierarchyValid (UUID supportId, UUID childSupportId);

    long getCountAvailableSupportParent(UUID supportId);
}
