package com.mob.serverapi.device.database;

import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deviceAppLog")
public class tDeviceAppLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceAppLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID deviceAppLogId;

    @Column(name = "action")
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table deviceApp, column deviceAppId
    @JoinColumn(name = "deviceAppId", referencedColumnName = "deviceAppId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPLOG_DEVICEAPPID"))
    private tDeviceApp deviceApp;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tDeviceAppLog() {
    }

    /**
     * @return the deviceAppLogId.
     */
    public UUID getDeviceAppLogId() {
        return deviceAppLogId;
    }
    /**
     * @param deviceAppLogId to set to.
     */
    public void setDeviceAppLogId(UUID deviceAppLogId) {
        this.deviceAppLogId = deviceAppLogId;
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
     * @return the deviceApp.
     */
    public tDeviceApp getApp() {
        return deviceApp;
    }
    /**
     * @param deviceApp to set to.
     */
    public void setApp(tDeviceApp deviceApp) {
        this.deviceApp = deviceApp;
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
