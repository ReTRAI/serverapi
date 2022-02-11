package com.mob.serverapi.device.database;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "deviceBalance")
public class tDeviceBalance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceBalanceId", unique = true, nullable = false)
    private int deviceBalanceId;

    @Column(name = "debitCredit", nullable = false)
    private String debitCredit;

    @Column(name = "movementValue", nullable = false)
    private float movementValue;

    @Column(name = "movementDate", nullable = false)
    private LocalDateTime movementDate;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICEBALANCE_DEVICEID"))
    private tDevice device;

    protected tDeviceBalance() {}

    public tDeviceBalance(int deviceBalanceId, String DebitCredit, float movementValue, tDevice device) {
        this.deviceBalanceId = deviceBalanceId;
        this.debitCredit = DebitCredit;
        this.movementValue = movementValue;
        this.device = device;
    }

    /**
     * @return the deviceBalanceId.
     */

    public int getDeviceBalanceId() {
        return deviceBalanceId;
    }

    /**
     * @param deviceBalanceId the id to set to.
     */
    public void setDeviceBalanceId(int deviceBalanceId) {
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