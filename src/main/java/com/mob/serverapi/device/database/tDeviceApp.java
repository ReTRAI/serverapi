package com.mob.serverapi.device.database;

import com.mob.serverapi.apps.database.tApp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "deviceApp")
public class tDeviceApp implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceAppId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceAppId;

    @Column(name = "currentVersion", length = 100, nullable = false)
    private String currentVersion;

    @Column(name = "installationDate", nullable = false)
    private LocalDateTime installationDate;

    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to device, column deviceId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPS_DEVICEID"))
    private tDevice device;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to App, column appId
    @JoinColumn(name = "appId", referencedColumnName = "appId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPS_APPID"))
    private tApp app;

    /**
     * FK from deviceAppLog to deviceapp
     */

    @OneToMany(targetEntity = tDeviceAppLog.class,mappedBy="deviceApp" , fetch = FetchType.LAZY)
    private Set<tDeviceAppLog> deviceAppLog;

    public tDeviceApp() {
    }


    /**
     * @return the deviceAppId.
     */
    public UUID getDeviceAppId() {
        return deviceAppId;
    }
    /**
     * @param deviceAppId to set to.
     */
    public void setDeviceAppId(UUID deviceAppId) {
        this.deviceAppId = deviceAppId;
    }
    /**
     * @return the currentVersion.
     */
    public String getCurrentVersion() {
        return currentVersion;
    }
    /**
     * @param currentVersion to set to.
     */
    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
    /**
     * @return the installationDate.
     */
    public LocalDateTime getInstallationDate() {
        return installationDate;
    }
    /**
     * @param installationDate to set to.
     */
    public void setInstallationDate(LocalDateTime installationDate) {
        this.installationDate = installationDate;
    }
    /**
     * @return the lastUpdateDate.
     */
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }
    /**
     * @param lastUpdateDate to set to.
     */
    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    /**
     * @return the active.
     */
    public boolean isActive() {
        return active;
    }
    /**
     * @param active to set to.
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * @return the device.
     */
    public tDevice getDevice() {
        return device;
    }
    /**
     * @param device to set to.
     */
    public void setDevice(tDevice device) {
        this.device = device;
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
}
