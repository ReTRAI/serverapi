package com.mob.serverapi.device.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "deviceStatus")
public class tDeviceStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceStatusId", unique = true, nullable = false)
    private int deviceStatusId;

    @Column(name = "description", nullable = false)
    private DeviceStatusEnum description;
    private enum DeviceStatusEnum {
        ACTIVE,
        FREE,
        WIPED,
        BLOCKED,
        SUSPENDED;
    }

    /**
     * FK from device to deviceStatus
     */
    @OneToOne(mappedBy = "deviceStatus")
    private tDevice device;

    /**
     * FK from DeviceStatusLog to deviceStatus
     */
    @OneToOne(mappedBy = "deviceStatus")
    private tDeviceStatusLog deviceStatusLog;

    public tDeviceStatus() {
    }

    public tDeviceStatus(int deviceStatusId, DeviceStatusEnum description) {
        this.deviceStatusId = deviceStatusId;
        this.description = description;
    }

    /**
     * @return the deviceStatusId.
     */
    public int getDeviceStatusId() {
        return deviceStatusId;
    }

    /**
     * @param deviceStatusId to set to.
     */
    public void setDeviceStatusId(int deviceStatusId) {
        this.deviceStatusId = deviceStatusId;
    }

    /**
     * @return the description.
     */
    public DeviceStatusEnum getDescription() {
        return description;
    }

    /**
     * @param description to set to.
     */
    public void setDescription(DeviceStatusEnum description) {
        this.description = description;
    }
}
