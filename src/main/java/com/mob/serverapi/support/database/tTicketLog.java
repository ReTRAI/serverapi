package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticketLog")
public class tTicketLog {

    @Id
    @GeneratedValue
    @Column(name = "ticketLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID ticketLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKETLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table ticket, column ticketId
    @JoinColumn(name = "alteredId", referencedColumnName = "ticketId",
            foreignKey = @ForeignKey(name="FK_TICKETLOG_TICKETID"))
    private tTicket ticket;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tTicketLog() {
    }

    /**
     * @return the ticketLogId.
     */
    public UUID getTicketLogId() {
        return ticketLogId;
    }
    /**
     * @param ticketLogId to set to.
     */
    public void setTicketLogId(UUID ticketLogId) {
        this.ticketLogId = ticketLogId;
    }
    /**
     * @return the action.
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action to set to.
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * @return the user.
     */
    public tUser getUser() {
        return user;
    }
    /**
     * @param user to set to.
     */
    public void setUser(tUser user) {
        this.user = user;
    }
    /**
     * @return the ticket.
     */
    public tTicket getTicket() {
        return ticket;
    }
    /**
     * @param ticket to set to.
     */
    public void setTicket(tTicket ticket) {
        this.ticket = ticket;
    }
    /**
     * @return the alterationDate.
     */
    public LocalDateTime getAlterationDate() {
        return alterationDate;
    }
    /**
     * @param alterationDate to set to.
     */
    public void setAlterationDate(LocalDateTime alterationDate) {
        this.alterationDate = alterationDate;
    }
    /**
     * @return the alterationDetail.
     */
    public String getAlterationDetail() {
        return alterationDetail;
    }
    /**
     * @param alterationDetail to set to.
     */
    public void setAlterationDetail(String alterationDetail) {
        this.alterationDetail = alterationDetail;
    }
}
