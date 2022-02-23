package com.mob.serverapi.device.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "deviceStatus")
public class tDeviceStatus implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceStatusId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceStatusId;

    @Column(name = "description", nullable = false)
    private String description;
    public enum DeviceStatusEnum {
        ACTIVE,
        FREE,
        WIPED,
        BLOCKED,
        SUSPENDED;
    }

    /**
     * FK from device to deviceStatus
     */

    @OneToMany(targetEntity = tDevice.class,mappedBy="deviceStatus" , fetch = FetchType.LAZY)
    private Set<tDevice> device;

    /**
     * FK from DeviceStatusLog to deviceStatus
     */
    @OneToMany(targetEntity = tDeviceStatusLog.class,mappedBy="deviceStatus" , fetch = FetchType.LAZY)
    private Set<tDeviceStatusLog> deviceStatusLog;

    public tDeviceStatus() {
    }
    
}
