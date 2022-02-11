package com.mob.serverapi.device.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "deviceAppLog")
public class tDeviceAppLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceAppLogId", unique = true, nullable = false)
    public int deviceAppLogId;

    @Column(name = "action")
    public String action;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPLOG_USERID"))
    private tUser user;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    //FK to table device, column deviceId
    @JoinColumn(name = "deviceAppId", referencedColumnName = "deviceAppId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPLOG_DEVICEAPPID"))
    private tDeviceApp deviceApp;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tDeviceAppLog() {
    }

    public tDeviceAppLog(int deviceAppLogId, String action, tUser user, tDeviceApp deviceApp,
                         LocalDateTime alterationDate, String alterationDetail) {
        this.deviceAppLogId = deviceAppLogId;
        this.action = action;
        this.user = user;
        this.deviceApp = deviceApp;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }
    /**
     * @return the deviceAppLogId.
     */
    public int getDeviceAppLogId() {
        return deviceAppLogId;
    }
    /**
     * @param deviceAppLogId to set to.
     */
    public void setDeviceAppLogId(int deviceAppLogId) {
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
