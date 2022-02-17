package com.mob.serverapi.device.database;


import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deviceUserLog")
public class tDeviceUserLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID deviceLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_DEVICEUSERLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table deviceUser, column deviceUserId
    @JoinColumn(name = "alteredId", referencedColumnName = "deviceUserId",
            foreignKey = @ForeignKey(name="FK_DEVICEUSERLOG_DEVICEID"))
    private tDeviceUser deviceUser;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tDeviceUserLog() {
    }

    public tDeviceUserLog(UUID deviceLogId, String action, tUser user, tDeviceUser deviceUser,
                          LocalDateTime alterationDate, String alterationDetail) {
        this.deviceLogId = deviceLogId;
        this.action = action;
        this.user = user;
        this.deviceUser = deviceUser;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }

    /**
     * @return the deviceLogId.
     */
    public UUID getDeviceLogId() {
        return deviceLogId;
    }
    /**
     * @param deviceLogId to set to.
     */
    public void setDeviceLogId(UUID deviceLogId) {
        this.deviceLogId = deviceLogId;
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
     * @return the deviceUser.
     */
    public tDeviceUser getDeviceUser() {
        return deviceUser;
    }
    /**
     * @param deviceUser to set to.
     */
    public void setDeviceUser(tDeviceUser deviceUser) {
        this.deviceUser = deviceUser;
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
