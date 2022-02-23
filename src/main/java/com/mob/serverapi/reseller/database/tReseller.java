package com.mob.serverapi.reseller.database;

import com.mob.serverapi.device.database.*;
import com.mob.serverapi.users.database.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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
    private long totalDevices;

    @Column(name = "activeDevices", nullable = false)
    private long activeDevices;

    @Column(name = "inactiveDevices", nullable = false)
    private long inactiveDevices;

    @Column(name = "freeDevices", nullable = false)
    private long freeDevices;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    /**
     * FK from ResellerAssociation to Reseller
     */
    @OneToMany(targetEntity = tResellerAssociation.class,mappedBy="parentReseller" , fetch = FetchType.EAGER)
    private Set<tResellerAssociation> resellerAssociationParent;

    @OneToMany(targetEntity = tResellerAssociation.class,mappedBy="childReseller" , fetch = FetchType.EAGER)
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


}
