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
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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
     * FK from ticketStatusLog to user
     */
    @OneToMany(targetEntity = tTicketStatusLog.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<tTicketStatusLog> ticketStatusLog;

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

    public tUser(UUID userId, String userName, String userEmail, byte[] passwordHash, byte[] passwordSalt,
                 LocalDateTime creationDate,
                 String languagePreference, String themePreference, tUserStatus userStatus,
                 LocalDateTime inactivationDate) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.creationDate = creationDate;
        this.languagePreference = languagePreference;
        this.themePreference = themePreference;
        this.userStatus = userStatus;
        this.inactivationDate = inactivationDate;
    }

    /**
     * @return the userId.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * @param userId to set to.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * @return the userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName to set to.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userEmail.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail to set to.
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return the passwordHash.
     */
    public byte[] getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash to set to.
     */
    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return the passwordHash.
     */
    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * @param passwordSalt to set to.
     */
    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
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
     * @return the languagePreference.
     */
    public String getLanguagePreference() {
        return languagePreference;
    }

    /**
     * @param languagePreference to set to.
     */
    public void setLanguagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
    }

    /**
     * @return the themePreference.
     */
    public String getThemePreference() {
        return themePreference;
    }

    /**
     * @param themePreference to set to.
     */
    public void setThemePreference(String themePreference) {
        this.themePreference = themePreference;
    }

    /**
     * @return the inactivationDate.
     */
    public LocalDateTime getInactivationDate() {
        return inactivationDate;
    }

    /**
     * @param inactivationDate to set to.
     */
    public void setInactivationDate(LocalDateTime inactivationDate) {
        this.inactivationDate = inactivationDate;
    }

    /**
     * @return the userStatus.
     */
    public tUserStatus getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus to set to.
     */
    public void setUserStatus(tUserStatus userStatus) {
        this.userStatus = userStatus;
    }


}
