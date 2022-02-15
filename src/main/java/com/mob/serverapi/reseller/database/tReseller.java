package com.mob.serverapi.reseller.database;

import com.mob.serverapi.device.database.*;
import com.mob.serverapi.users.database.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "reseller")
public class tReseller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resellerId", unique = true, nullable = false)
    private int resellerId;

    @OneToOne(cascade = CascadeType.MERGE,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_RESELLER_USERID"))
    private tUser user;

    @Column(name = "currentBalance", nullable = false)
    private float currentBalance;

    /**
     * FK from ResellerAssociation to Reseller
     */
    @OneToMany(targetEntity = tResellerAssociation.class,mappedBy="parentReseller" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tResellerAssociation> resellerAssociationParent;

    @OneToMany(targetEntity = tResellerAssociation.class,mappedBy="childReseller" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tResellerAssociation> resellerAssociationChild;

    /**
     * FK from resellerBalance to Reseller
     */
    @OneToMany(targetEntity = tResellerBalance.class,mappedBy="reseller" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tResellerBalance> resellerBalance;

    /**
     * FK from resellerNotification to Reseller
     */

    @OneToMany(targetEntity = tResellerNotification.class,mappedBy="reseller" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tResellerNotification> resellerNotification;
    /**
     * FK from device to Reseller
     */
    @OneToMany(targetEntity = tDevice.class,mappedBy="reseller" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDevice> device;

    /**
     * FK from resellerLog to Reseller
     */
    @OneToMany(targetEntity = tResellerLog.class,mappedBy="reseller" , fetch = FetchType.LAZY)
    private Set<tResellerLog> resellerLog;

    public tReseller() {}

    public tReseller(int resellerId, tUser user, float currentBalance) {
        this.resellerId = resellerId;
        this.user = user;
        this.currentBalance = currentBalance;

    }

    /**
     * @return resellerId's id.
     */

    public int getResellerId() {

        return resellerId;
    }

    /**
     * @param resellerId the id to set to.
     */
    public void setResellerId(int resellerId) {
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


}
