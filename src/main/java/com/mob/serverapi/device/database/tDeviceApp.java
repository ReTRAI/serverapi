package com.mob.serverapi.device.database;

import com.mob.serverapi.apps.database.tApp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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


}
