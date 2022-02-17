package com.mob.serverapi.reseller.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resellerBalance")
public class tResellerBalance implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerBalanceId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerBalanceId;

    @Column(name = "debitCredit", nullable = false)
    private String debitCredit;

    @Column(name = "movementValue", nullable = false)
    private float movementValue;

    @Column(name = "movementDate", nullable = false)
    private LocalDateTime movementDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "resellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLERBALANCE_RESELLERID"))
    private tReseller reseller;

    protected tResellerBalance() {}

    public tResellerBalance(UUID resellerBalanceId, String DebitCredit, float movementValue, tReseller reseller) {
        this.resellerBalanceId = resellerBalanceId;
        this.debitCredit = DebitCredit;
        this.movementValue = movementValue;
        this.reseller = reseller;
    }

    /**
     * @return the ResellerBalanceId.
     */

    public UUID getResellerBalanceId() {
        return resellerBalanceId;
    }

    /**
     * @param resellerBalanceId the id to set to.
     */
    public void setResellerBalanceId(UUID resellerBalanceId) {
        this.resellerBalanceId = resellerBalanceId;
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
     * @return the Reseller.
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
}
