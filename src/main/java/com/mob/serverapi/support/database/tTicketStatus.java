package com.mob.serverapi.support.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ticketStatus")
public class tTicketStatus implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ticketStatusId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID ticketStatusId;

    @Column(name = "description", nullable = false)
    private String description;
    public enum TicketStatusEnum {
        OPEN,
        ONPROGRESS,
        PENDING,
        CANCELED,
        COMPLETED;
    }


    public tTicketStatus() {
    }

    /**
     * @return the ticketStatusId.
     */
    public UUID getTicketStatusId() {
        return ticketStatusId;
    }
    /**
     * @param ticketStatusId to set to.
     */
    public void setTicketStatusId(UUID ticketStatusId) {
        this.ticketStatusId = ticketStatusId;
    }
    /**
     * @return the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description to set to.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
