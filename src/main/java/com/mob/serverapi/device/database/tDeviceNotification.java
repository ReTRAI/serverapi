package com.mob.serverapi.device.database;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "deviceNotification")
public class tDeviceNotification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceNotificationId", unique = true, nullable = false)
    private int deviceNotificationId;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "detail", nullable = false)
    @Lob
    private String detail;

    @Column(name = "checked", nullable = false)
    private boolean checked;

    @Column(name = "checkedDate")
    private LocalDateTime checkedDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICENOTIFICATION_DEVICEID"))
    private tDevice device;

    protected tDeviceNotification() {}

    public tDeviceNotification(int deviceNotificationId, LocalDateTime creationDate,
                                 String detail,boolean checked,
                                 LocalDateTime checkedDate, tDevice device) {
        this.deviceNotificationId = deviceNotificationId;
        this.creationDate = creationDate;
        this.detail = detail;
        this.checked = checked;
        this.checkedDate = checkedDate;
        this.device = device;
    }

    /**
     * @return the deviceNotificationId.
     */
    public int getDeviceNotificationId() {
        return deviceNotificationId;
    }

    /**
     * @param deviceNotificationId to set to.
     */
    public void setDeviceNotificationId(int deviceNotificationId) {
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
