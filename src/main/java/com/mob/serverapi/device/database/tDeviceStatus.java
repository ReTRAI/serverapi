package com.mob.serverapi.device.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

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
    @OneToMany(targetEntity = tDeviceStatusLog.class,mappedBy="deviceStatus" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceStatusLog> deviceStatusLog;

    public tDeviceStatus() {
    }


    /**
     * @return the deviceStatusId.
     */
    public UUID getDeviceStatusId() {
        return deviceStatusId;
    }

    /**
     * @param deviceStatusId to set to.
     */
    public void setDeviceStatusId(UUID deviceStatusId) {
        this.deviceStatusId = deviceStatusId;
    }

    /**
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description to set to.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
