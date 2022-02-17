package com.mob.serverapi.apps.database;

import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appLog")
public class tAppLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "appLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID appLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_APPLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table App, column appId
    @JoinColumn(name = "appId", referencedColumnName = "appId",
            foreignKey = @ForeignKey(name="FK_APPLOG_DEVICEID"))
    private tApp app;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tAppLog() {
    }

    public tAppLog(UUID appLogId, String action, tUser user, tApp app,
                   LocalDateTime alterationDate, String alterationDetail) {
        this.appLogId = appLogId;
        this.action = action;
        this.user = user;
        this.app = app;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }

    /**
     * @return the appLogId.
     */
    public UUID getAppLogId() {
        return appLogId;
    }
    /**
     * @param appLogId to set to.
     */
    public void setAppLogId(UUID appLogId) {
        this.appLogId = appLogId;
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
     * @return the app.
     */
    public tApp getApp() {
        return app;
    }
    /**
     * @param app to set to.
     */
    public void setApp(tApp app) {
        this.app = app;
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
