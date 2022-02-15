package com.mob.serverapi.device.database;

import com.mob.serverapi.reseller.database.tReseller;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "device")
public class tDevice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceId", unique = true, nullable = false)
    private int deviceId;

    @Column(name = "brand", nullable = false)
    private String brand;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table reseller, column resellerId
    @JoinColumn(name = "resellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_DEVICE_RESELLERID"))
    private tReseller reseller;

    @Column(name = "serialNumber", nullable = false)
    private String serialNumber;

    @Column(name = "imeiNumber", nullable = false)
    private String imeiNumber;

    @Column(name = "simNumber", nullable = false)
    private String simNumber;

    @Column(name = "androidId", nullable = false)
    private String androidId;

    @Column(name = "firstSimNumber", nullable = false)
    private String firstSimNumber;

    @Column(name = "tunnelIP", nullable = false)
    private String tunnelIP;

    @Column(name = "creationDevice", nullable = false)
    private LocalDateTime creationDevice;

    @Column(name = "lastConnection", nullable = false)
    private LocalDateTime lastConnection;

    @Column(name = "lastBackup")
    private  LocalDateTime lastBackup;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table deviceStatus, column deviceStatusId
    @JoinColumn(name = "deviceStatusId", referencedColumnName = "deviceStatusId",
            foreignKey = @ForeignKey(name="FK_DEVICE_STATUSID"))
    private tDeviceStatus deviceStatus;

    @Column(name = "activationDate")
    private LocalDateTime activationDate;

    @Column(name = "expireDate")
    private LocalDateTime expireDate;

    @Column(name = "currentBalance")
    private float currentBalance;

    @Column(name = "currentMinutes")
    private Time currentMinutes;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "osVersion", length = 100, nullable = false)
    private String osVersion;

    /**
     * FK from DeviceLog to device
     */
    @OneToMany(targetEntity = tDeviceLog.class,mappedBy="device" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceLog> deviceLog;

    /**
     * FK from deviceNotification to device
     */
    @OneToMany(targetEntity = tDeviceNotification.class,mappedBy="device" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceNotification> deviceNotification;

    /**
     * FK from deviceBalance to device
     */
    @OneToMany(targetEntity = tDeviceBalance.class,mappedBy="device" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceBalance> deviceBalance;

    /**
     * FK from deviceUser to device
     */
    @OneToMany(targetEntity = tDeviceUser.class,mappedBy="device" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceUser> deviceUser;

    /**
     * FK from deviceApp to device
     */

    @OneToMany(targetEntity = tDeviceApp.class,mappedBy="device" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceApp> deviceApp;

    public tDevice() {
    }

    public tDevice(int deviceId, String brand, tReseller reseller, String serialNumber,
                   String imeiNumber, String simNumber, String androidId, String firstSimNumber,
                   String tunnelIP, LocalDateTime creationDevice, LocalDateTime lastConnection,
                   LocalDateTime lastBackup, tDeviceStatus deviceStatus, LocalDateTime activationDate,
                   LocalDateTime expireDate, float currentBalance, Time currentMinutes, String notes,
                   String osVersion) {
        this.deviceId = deviceId;
        this.brand = brand;
        this.reseller = reseller;
        this.serialNumber = serialNumber;
        this.imeiNumber = imeiNumber;
        this.simNumber = simNumber;
        this.androidId = androidId;
        this.firstSimNumber = firstSimNumber;
        this.tunnelIP = tunnelIP;
        this.creationDevice = creationDevice;
        this.lastConnection = lastConnection;
        this.lastBackup = lastBackup;
        this.deviceStatus = deviceStatus;
        this.activationDate = activationDate;
        this.expireDate = expireDate;
        this.currentBalance = currentBalance;
        this.currentMinutes = currentMinutes;
        this.notes = notes;
        this.osVersion = osVersion;
    }

    /**
     * @return the deviceId.
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId to set to.
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand to set to.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the reseller.
     */
    public tReseller getReseller() {
        return reseller;
    }

    /**
     * @param reseller to set to.
     */
    public void setReseller(tReseller reseller) {
        this.reseller = reseller;
    }

    /**
     * @return the serialNumber.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber to set to.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the imeiNumber.
     */
    public String getImeiNumber() {
        return imeiNumber;
    }

    /**
     * @param imeiNumber to set to.
     */
    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    /**
     * @return the simNumber.
     */
    public String getSimNumber() {
        return simNumber;
    }

    /**
     * @param simNumber to set to.
     */
    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    /**
     * @return the androidId.
     */
    public String getAndroidId() {
        return androidId;
    }

    /**
     * @param androidId to set to.
     */
    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    /**
     * @return the firstSimNumber.
     */
    public String getFirstSimNumber() {
        return firstSimNumber;
    }

    /**
     * @param firstSimNumber to set to.
     */
    public void setFirstSimNumber(String firstSimNumber) {
        this.firstSimNumber = firstSimNumber;
    }

    /**
     * @return the tunnelIP.
     */
    public String getTunnelIP() {
        return tunnelIP;
    }

    /**
     * @param tunnelIP to set to.
     */
    public void setTunnelIP(String tunnelIP) {
        this.tunnelIP = tunnelIP;
    }

    /**
     * @return the creationDevice.
     */
    public LocalDateTime getCreationDevice() {
        return creationDevice;
    }

    /**
     * @param creationDevice to set to.
     */
    public void setCreationDevice(LocalDateTime creationDevice) {
        this.creationDevice = creationDevice;
    }

    /**
     * @return the lastConnection.
     */
    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    /**
     * @param lastConnection to set to.
     */
    public void setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
    }

    /**
     * @return the lastBackup.
     */
    public LocalDateTime getLastBackup() {
        return lastBackup;
    }

    /**
     * @param lastBackup to set to.
     */
    public void setLastBackup(LocalDateTime lastBackup) {
        this.lastBackup = lastBackup;
    }

    /**
     * @return the deviceStatus.
     */
    public tDeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * @param deviceStatus to set to.
     */
    public void setDeviceStatus(tDeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    /**
     * @return the activationDate.
     */
    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    /**
     * @param activationDate to set to.
     */
    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    /**
     * @return the expireDate.
     */
    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    /**
     * @param expireDate to set to.
     */
    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * @return the currentBalance.
     */
    public float getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance to set to.
     */
    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @return the currentMinutes.
     */
    public Time getCurrentMinutes() {
        return currentMinutes;
    }

    /**
     * @param currentMinutes to set to.
     */
    public void setCurrentMinutes(Time currentMinutes) {
        this.currentMinutes = currentMinutes;
    }

    /**
     * @return the notes.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes to set to.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the osVersion.
     */
    public String getOsVersion() {
        return osVersion;
    }
    /**
     * @param osVersion to set to.
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

}
