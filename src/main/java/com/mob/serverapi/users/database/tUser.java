package com.mob.serverapi.users.database;

import com.mob.serverapi.apps.database.tAppLog;
import com.mob.serverapi.device.database.tDeviceAppLog;
import com.mob.serverapi.device.database.tDeviceLog;
import com.mob.serverapi.device.database.tDeviceStatusLog;
import com.mob.serverapi.device.database.tDeviceUserLog;
import com.mob.serverapi.reseller.database.tReseller;
import com.mob.serverapi.reseller.database.tResellerAssociationLog;
import com.mob.serverapi.reseller.database.tResellerLog;
import com.mob.serverapi.support.database.*;
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
@Table(name = "user")
public class tUser implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "userId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID userId;


    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(name = "userEmail", length = 50, nullable = false)
    private String userEmail;

    @Column(name = "passwordHash", nullable = false)
    @Lob
    private byte[] passwordHash;

    @Column(name = "passwordSalt", nullable = false)
    @Lob
    private byte[] passwordSalt;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "languagePreference", length = 2, nullable = false)
    private String languagePreference;

    @Column(name = "themePreference", length = 1, nullable = false)
    private String themePreference;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table userStatus, column userStatusId
    @JoinColumn(name = "userStatusId", referencedColumnName = "userStatusId",
            foreignKey = @ForeignKey(name = "FK_USER_USERSTATUSID"))
    private tUserStatus userStatus;

    @Column(name = "inactivationDate", nullable = true)
    private LocalDateTime inactivationDate;


    /**
     * FK from userRoles to user
     */
    @OneToMany(targetEntity = tReseller.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tUserRole> userRoles;

    /**
     * FK from reseller to user
     */
    @OneToMany(targetEntity = tReseller.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tReseller> reseller;

    /**
     * FK from UserLog to user
     */
    @OneToMany(targetEntity = tUserLog.class, mappedBy = "actionUser", fetch = FetchType.LAZY)
    private Set<tUserLog> actionUser;

    @OneToMany(targetEntity = tUserLog.class, mappedBy = "alteredUser", fetch = FetchType.LAZY)
    private Set<tUserLog> alteredUser;

    /**
     * FK from ResellerLog to user
     */

    @OneToMany(targetEntity = tResellerLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tResellerLog> resellerLog;

    /**
     * FK from ResellerAssociationLog to user
     */
    @OneToMany(targetEntity = tResellerAssociationLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tResellerAssociationLog> resellerAssociationLog;

    /**
     * FK from DeviceLog to user
     */
    @OneToMany(targetEntity = tDeviceLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tDeviceLog> deviceLog;


    /**
     * FK from DeviceStatusLog to user
     */
    @OneToMany(targetEntity = tDeviceStatusLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tDeviceStatusLog> deviceStatusLog;

    /**
     * FK from DeviceUserLog to user
     */
    @OneToMany(targetEntity = tDeviceUserLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tDeviceUserLog> deviceUserLog;

    /**
     * FK from appLog to user
     */
    @OneToMany(targetEntity = tAppLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tAppLog> appLog;

    /**
     * FK from deviceAppLog to user
     */
    @OneToMany(targetEntity = tDeviceAppLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tDeviceAppLog> deviceAppLog;

    /**
     * FK from support to user
     */
    @OneToMany(targetEntity = tSupport.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tSupport> support;

    /**
     * FK from supportLog to user
     */
    @OneToMany(targetEntity = tSupportLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tSupportLog> supportLog;

    /**
     * FK from SUPPORTAssociationLog to user
     */
    @OneToMany(targetEntity = tSupportAssociationLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tSupportAssociationLog> supportAssociationLog;

    /**
     * FK from Ticket to User
     */
    @OneToMany(targetEntity = tTicket.class, mappedBy = "openUser", fetch = FetchType.LAZY)
    private Set<tTicket> openUser;

    @OneToMany(targetEntity = tTicket.class, mappedBy = "assignedUser", fetch = FetchType.LAZY)
    private Set<tTicket> assignedUser;

    /**
     * FK from ticketDetail to user
     */
    @OneToMany(targetEntity = tTicketDetail.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tTicketDetail> ticketDetail;

    /**
     * FK from userLoginLog to user
     */
    @OneToMany(targetEntity = tUserLoginLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tUserLoginLog> userLoginLog;

    /**
     * FK from TicketLog to user
     */
    @OneToMany(targetEntity = tTicketLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tTicketLog> ticketLog;

    public tUser() {
    }


}
