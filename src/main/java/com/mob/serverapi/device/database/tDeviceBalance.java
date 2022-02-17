package com.mob.serverapi.device.database;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deviceBalance")
public class tDeviceBalance implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceBalanceId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceBalanceId;

    @Column(name = "debitCredit", nullable = false)
    private String debitCredit;

    @Column(name = "movementValue", nullable = false)
    private float movementValue;

    @Column(name = "movementDate", nullable = false)
    private LocalDateTime movementDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to device, column deviceId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICEBALANCE_DEVICEID"))
    private tDevice device;

    protected tDeviceBalance() {}

    public tDeviceBalance(UUID deviceBalanceId, String DebitCredit, float movementValue, tDevice device) {
        this.deviceBalanceId = deviceBalanceId;
        this.debitCredit = DebitCredit;
        this.movementValue = movementValue;
        this.device = device;
    }

    /**
     * @return the deviceBalanceId.
     */

    public UUID getDeviceBalanceId() {
        return deviceBalanceId;
    }

    /**
     * @param deviceBalanceId the id to set to.
     */
    public void setDeviceBalanceId(UUID deviceBalanceId) {
        this.deviceBalanceId = deviceBalanceId;
    }

    /**
     * @return the DebitCredit.
     */

    public String getDebitCredit() {
        return debitCredit;
    }

    /**
     * @param debitCredit  to set to.
     */
    public void setDebitCredit(String debitCredit) {
        debitCredit = debitCredit;
    }

    /**
     * @return the movementValue.
     */

    public float getMovementValue() {
        return movementValue;
    }

    /**
     * @param movementValue to set to.
     */
    public void setMovementValue(float movementValue) {
        this.movementValue = movementValue;
    }

    /**
     * @return the movementDate.
     */

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    /**
     * @param movementDate to set to.
     */
    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
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
}