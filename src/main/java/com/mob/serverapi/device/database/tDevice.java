package com.mob.serverapi.device.database;

import com.mob.serverapi.reseller.database.tReseller;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "device")
public class tDevice implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceId;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
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

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "lastConnection")
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

    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

    @Column(name = "lastRenovationDate")
    private LocalDateTime lastRenovationDate;

    @Column(name = "currentBalance")
    private float currentBalance;

    @Column(name = "currentMinutes")
    private float currentMinutes;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "osVersion", length = 100)
    private String osVersion;

    /**
     * FK from DeviceLog to device
     */
    @OneToMany(targetEntity = tDeviceLog.class,mappedBy="device" , fetch = FetchType.LAZY)
    private Set<tDeviceLog> deviceLog;

    /**
     * FK from deviceNotification to device
     */
    @OneToMany(targetEntity = tDeviceNotification.class,mappedBy="device" , fetch = FetchType.LAZY)
    private Set<tDeviceNotification> deviceNotification;

    /**
     * FK from deviceBalance to device
     */
    @OneToMany(targetEntity = tDeviceBalance.class,mappedBy="device" , fetch = FetchType.LAZY)
    private Set<tDeviceBalance> deviceBalance;

    /**
     * FK from deviceUser to device
     */
    @OneToMany(targetEntity = tDeviceUser.class,mappedBy="device" , fetch = FetchType.LAZY)
    private Set<tDeviceUser> deviceUser;

    /**
     * FK from deviceApp to device
     */

    @OneToMany(targetEntity = tDeviceApp.class,mappedBy="device" , fetch = FetchType.LAZY)
    private Set<tDeviceApp> deviceApp;

    public tDevice() {
    }



}
