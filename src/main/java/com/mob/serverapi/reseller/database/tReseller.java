package com.mob.serverapi.reseller.database;

import com.mob.serverapi.device.database.*;
import com.mob.serverapi.users.database.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "reseller")
public class tReseller implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerId;

    @OneToOne(cascade = CascadeType.MERGE,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_RESELLER_USERID"))
    private tUser user;

    @Column(name = "currentBalance", nullable = false)
    private float currentBalance;

    @Column(name = "totalDevices", nullable = false)
    private float totalDevices;

    @Column(name = "activeDevices", nullable = false)
    private float activeDevices;

    @Column(name = "inactiveDevices", nullable = false)
    private float inactiveDevices;

    @Column(name = "freeDevices", nullable = false)
    private float freeDevices;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    /**
     * FK from ResellerAssociation to Reseller
     */
    @OneToMany(targetEntity = tResellerAssociation.class,mappedBy="parentReseller" , fetch = FetchType.LAZY)
    private Set<tResellerAssociation> resellerAssociationParent;

    @OneToMany(targetEntity = tResellerAssociation.class,mappedBy="childReseller" , fetch = FetchType.LAZY)
    private Set<tResellerAssociation> resellerAssociationChild;

    /**
     * FK from resellerBalance to Reseller
     */
    @OneToMany(targetEntity = tResellerBalance.class,mappedBy="reseller" , fetch = FetchType.LAZY)
    private Set<tResellerBalance> resellerBalance;

    /**
     * FK from resellerNotification to Reseller
     */

    @OneToMany(targetEntity = tResellerNotification.class,mappedBy="reseller" , fetch = FetchType.LAZY)
    private Set<tResellerNotification> resellerNotification;
    /**
     * FK from device to Reseller
     */
    @OneToMany(targetEntity = tDevice.class,mappedBy="reseller" , fetch = FetchType.LAZY)
    private Set<tDevice> device;

    /**
     * FK from resellerLog to Reseller
     */
    @OneToMany(targetEntity = tResellerLog.class,mappedBy="reseller" , fetch = FetchType.LAZY)
    private Set<tResellerLog> resellerLog;

    public tReseller() {}

    public tReseller(UUID resellerId, tUser user, float currentBalance) {
        this.resellerId = resellerId;
        this.user = user;
        this.currentBalance = currentBalance;

    }

    /**
     * @return resellerId's id.
     */

    public UUID getResellerId() {

        return resellerId;
    }

    /**
     * @param resellerId the id to set to.
     */
    public void setResellerId(UUID resellerId) {
        this.resellerId = resellerId;}

    /**
     * @return the user ID.
     */

    public tUser getUserId() {

        return user;
    }

    /**
     * @param user to set to.
     */
    public void setUserId(tUser user) {

        this.user = user;
    }

    /**
     * @return the reseller balance.
     */

    public float getCurrentBalance() {

        return currentBalance;
    }

    /**
     * @param currentBalance  to set to.
     */
    public void setCurrentBalance(float currentBalance) {

        this.currentBalance = currentBalance;
    }

    /**
     * @return the totalDevices.
     */
    public float getTotalDevices() {
        return totalDevices;
    }

    /**
     * @param totalDevices  to set to.
     */
    public void setTotalDevices(float totalDevices) {
        this.totalDevices = totalDevices;
    }

    /**
     * @return the creationDate .
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate  to set to.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the activeDevices.
     */
    public float getActiveDevices() {
        return activeDevices;
    }

    /**
     * @param activeDevices  to set to.
     */
    public void setActiveDevices(float activeDevices) {
        this.activeDevices = activeDevices;
    }

    /**
     * @return the inactiveDevices .
     */
    public float getInactiveDevices() {
        return inactiveDevices;
    }

    /**
     * @param inactiveDevices  to set to.
     */
    public void setInactiveDevices(float inactiveDevices) {
        this.inactiveDevices = inactiveDevices;
    }

    /**
     * @return the freeDevices .
     */
    public float getFreeDevices() {
        return freeDevices;
    }

    /**
     * @param freeDevices  to set to.
     */
    public void setFreeDevices(float freeDevices) {
        this.freeDevices = freeDevices;
    }
}
