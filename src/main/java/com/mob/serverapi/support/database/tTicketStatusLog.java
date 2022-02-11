package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticketStatusLog")
public class tTicketStatusLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketStatusLogId", unique = true, nullable = false)
    public int ticketStatusLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @OneToOne(cascade = CascadeType.MERGE,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKETSTATUSLOG_USERID"))
    private tUser user;

    @OneToOne(cascade = CascadeType.MERGE,optional = false)
    //FK to table device, column deviceId
    @JoinColumn(name = "alteredId", referencedColumnName = "ticketStatusId",
            foreignKey = @ForeignKey(name="FK_TICKETSTATUSLOG_TICKETSTATUSID"))
    private tTicketStatus ticketStatus;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tTicketStatusLog() {
    }

    public tTicketStatusLog(int ticketStatusLogId, String action, tUser user, tTicketStatus ticketStatus,
                            LocalDateTime alterationDate, String alterationDetail) {
        this.ticketStatusLogId = ticketStatusLogId;
        this.action = action;
        this.user = user;
        this.ticketStatus = ticketStatus;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }
    /**
     * @return the deviceStatusId.
     */
    public int getTicketStatusLogId() {
        return ticketStatusLogId;
    }
    /**
     * @param ticketStatusLogId to set to.
     */
    public void setTicketStatusLogId(int ticketStatusLogId) {
        this.ticketStatusLogId = ticketStatusLogId;
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
     * @return the ticketStatus.
     */
    public tTicketStatus getDeviceStatus() {
        return ticketStatus;
    }
    /**
     * @param ticketStatus to set to.
     */
    public void setDeviceStatus(tTicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
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
