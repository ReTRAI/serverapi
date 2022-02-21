package com.mob.serverapi.device.database;


import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deviceStatusLog")
public class tDeviceStatusLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceStatusLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID deviceStatusLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_DEVICESTATUSLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table deviceStatus, column deviceStatusId
    @JoinColumn(name = "alteredId", referencedColumnName = "deviceStatusId",
            foreignKey = @ForeignKey(name="FK_DEVICESTATUSLOG_DEVICESTATUSID"))
    private tDeviceStatus deviceStatus;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tDeviceStatusLog() {
    }


    /**
     * @return the deviceStatusLogId.
     */
    public UUID getDeviceLogId() {
        return deviceStatusLogId;
    }

    /**
     * @param deviceStatusLogId to set to.
     */
    public void setDeviceLogId(UUID deviceStatusLogId) {
        this.deviceStatusLogId = deviceStatusLogId;
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
     * @return the actionUserId.
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
     * @return the deviceStatus.
     */
    public tDeviceStatus getAlteredId() {
        return deviceStatus;
    }

    /**
     * @param deviceStatus to set to.
     */
    public void setAlteredId(tDeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
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
