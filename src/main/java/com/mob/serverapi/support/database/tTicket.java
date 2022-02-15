package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ticket")
public class tTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId", unique = true, nullable = false)
    private int ticketId;

    @Column(name = "openDate", nullable = false)
    private LocalDateTime openDate;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,optional = false)
    //FK to table user, column userId
    @JoinColumn(name = "openUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKET_OPENUSERID"))
    private tUser openUser;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table ticketStatus, column ticketStatusId
    @JoinColumn(name = "ticketStatusId", referencedColumnName = "ticketStatusId",
            foreignKey = @ForeignKey(name="FK_TICKET_TICKETSTATUSID"))
    private tTicketStatus ticketStatus;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, optional = true)
    //FK to table user, column userId
    @JoinColumn(name = "assignedUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKET_ASSIGNEDUSERID"))
    private tUser assignedUser;

    /**
     * FK from ticketDetail to ticket
     */
    @OneToMany(targetEntity = tTicketDetail.class,mappedBy="ticket" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicketDetail> ticketDetail;

    /**
     * FK from TicketLog to user
     */
    @OneToMany(targetEntity = tTicketLog.class,mappedBy="ticket" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicketLog> ticketLog;

    public tTicket() {
    }

    public tTicket(int ticketId, LocalDateTime openDate, tUser openUser,
                   tTicketStatus ticketStatus, tUser assignedUser) {
        this.ticketId = ticketId;
        this.openDate = openDate;
        this.openUser = openUser;
        this.ticketStatus = ticketStatus;
        this.assignedUser = assignedUser;
    }

    /**
     * @return the ticketId.
     */
    public int getTicketId() {
        return ticketId;
    }
    /**
     * @param ticketId  to set to.
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * @return the openDate.
     */
    public LocalDateTime getOpenDate() {
        return openDate;
    }
    /**
     * @param openDate  to set to.
     */
    public void setOpenDate(LocalDateTime openDate) {
        this.openDate = openDate;
    }
    /**
     * @return the openUser.
     */
    public tUser getOpenUser() {
        return openUser;
    }
    /**
     * @param openUser  to set to.
     */
    public void setOpenUser(tUser openUser) {
        this.openUser = openUser;
    }
    /**
     * @return the ticketStatus.
     */
    public tTicketStatus getTicketStatus() {
        return ticketStatus;
    }
    /**
     * @param ticketStatus  to set to.
     */
    public void setTicketStatus(tTicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
    /**
     * @return the assignedUser.
     */
    public tUser getAssignedUser() {
        return assignedUser;
    }
    /**
     * @param assignedUser  to set to.
     */
    public void setAssignedUser(tUser assignedUser) {
        this.assignedUser = assignedUser;
    }
}
