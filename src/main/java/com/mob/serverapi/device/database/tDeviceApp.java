package com.mob.serverapi.device.database;

import com.mob.serverapi.apps.database.tApp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "deviceApp")
public class tDeviceApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceAppId", unique = true, nullable = false)
    private int deviceAppId;

    @Column(name = "currentVersion", length = 100, nullable = false)
    private String currentVersion;

    @Column(name = "installationDate", nullable = false)
    private LocalDateTime installationDate;

    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPS_DEVICEID"))
    private tDevice device;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "appId", referencedColumnName = "appId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPS_APPID"))
    private tApp app;

    /**
     * FK from deviceAppLog to deviceapp
     */
    @OneToOne(mappedBy = "deviceApp")
    private tDeviceAppLog deviceAppLog;

    public tDeviceApp() {
    }

    public tDeviceApp(int deviceAppId, String currentVersion, LocalDateTime installationDate,
                      LocalDateTime lastUpdateDate, boolean active, tDevice device, tApp app) {
        this.deviceAppId = deviceAppId;
        this.currentVersion = currentVersion;
        this.installationDate = installationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.active = active;
        this.device = device;
        this.app = app;
    }

    /**
     * @return the deviceAppId.
     */
    public int getDeviceAppId() {
        return deviceAppId;
    }
    /**
     * @param deviceAppId to set to.
     */
    public void setDeviceAppId(int deviceAppId) {
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
