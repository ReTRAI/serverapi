package com.mob.serverapi.support.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ticketStatus")
public class tTicketStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketStatusId", unique = true, nullable = false)
    private int ticketStatusId;

    @Column(name = "description", nullable = false)
    private String description;
    private enum TicketStatusEnum {
        OPEN,
        ONPROGRESS,
        PENDING,
        CANCELED,
        COMPLETED;
    }
    /**
     * FK from ticketStatusLog to ticketStatus
     */
    @OneToOne(mappedBy = "ticketStatus")
    private tTicketStatusLog ticketStatusLog;

    public tTicketStatus() {
    }

    public tTicketStatus(int ticketStatusId, String description) {
        this.ticketStatusId = ticketStatusId;
        this.description = description;
    }
    /**
     * @return the ticketStatusId.
     */
    public int getTicketStatusId() {
        return ticketStatusId;
    }
    /**
     * @param ticketStatusId to set to.
     */
    public void setTicketStatusId(int ticketStatusId) {
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
