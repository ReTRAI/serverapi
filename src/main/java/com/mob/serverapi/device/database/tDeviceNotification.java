package com.mob.serverapi.device.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deviceNotification")
public class tDeviceNotification implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceNotificationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceNotificationId;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "detail", nullable = false)
    @Lob
    private String detail;

    @Column(name = "checked", nullable = false)
    private boolean checked;

    @Column(name = "checkedDate")
    private LocalDateTime checkedDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to device, column deviceId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICENOTIFICATION_DEVICEID"))
    private tDevice device;

    protected tDeviceNotification() {}


    /**
     * @return the deviceNotificationId.
     */
    public UUID getDeviceNotificationId() {
        return deviceNotificationId;
    }

    /**
     * @param deviceNotificationId to set to.
     */
    public void setDeviceNotificationId(UUID deviceNotificationId) {
        this.deviceNotificationId = deviceNotificationId;
    }

    /**
     * @return the creationDate.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate to set to.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail to set to.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the checked.
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked to set to.
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the checkedDate.
     */
    public LocalDateTime getCheckedDate() {
        return checkedDate;
    }

    /**
     * @param checkedDate to set to.
     */
    public void setCheckedDate(LocalDateTime checkedDate) {
        this.checkedDate = checkedDate;
    }

    /**
     * @return the reseller.
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
}
