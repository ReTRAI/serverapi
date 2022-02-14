package com.mob.serverapi.users.database;

import com.mob.serverapi.reseller.database.*;
import com.mob.serverapi.support.database.*;
import com.mob.serverapi.device.database.*;
import com.mob.serverapi.apps.database.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
public class tUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", unique = true, nullable = false)
    private int userId;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userEmail", length=50, nullable = false)
    private String userEmail;

    @Column(name = "passwordHash", nullable = false)
    @Lob
    private byte[] passwordHash;

    @Column(name = "passwordSalt", nullable = false)
    @Lob
    private byte[] passwordSalt;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "languagePreference", length=2, nullable = false)
    private String languagePreference;

    @Column(name = "themePreference", length=1, nullable = false)
    private String themePreference;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table userStatus, column userStatusId
    @JoinColumn(name = "userStatusId", referencedColumnName = "userStatusId",
            foreignKey = @ForeignKey(name="FK_USER_USERSTATUSID"))
    private tUserStatus userStatus;

    @Column(name = "inactivationDate", nullable = true)
    private LocalDateTime inactivationDate;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table UserType, column userTypeId
    @JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId",
            foreignKey = @ForeignKey(name="FK_USER_USERTYPEID"))
    private tUserType userType;

    /**
     * FK from reseller to user
     */
    @OneToMany(targetEntity = tReseller.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tReseller> reseller;

    /**
     * FK from UserLog to user
     */
    @OneToMany(targetEntity = tUserLog.class,mappedBy="actionUser" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tUserLog> actionUser;

    @OneToMany(targetEntity = tUserLog.class,mappedBy="alteredUser" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tUserLog> alteredUser;

    /**
     * FK from ResellerLog to user
     */

    @OneToMany(targetEntity = tResellerLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tResellerLog> resellerLog;

    /**
     * FK from ResellerAssociationLog to user
     */
    @OneToMany(targetEntity = tResellerAssociationLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tResellerAssociationLog> resellerAssociationLog;

    /**
     * FK from DeviceLog to user
     */
    @OneToMany(targetEntity = tDeviceLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceLog> deviceLog;


    /**
     * FK from DeviceStatusLog to user
     */
    @OneToMany(targetEntity = tDeviceStatusLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceStatusLog> deviceStatusLog;

    /**
     * FK from DeviceUserLog to user
     */
    @OneToMany(targetEntity = tDeviceUserLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceUserLog> deviceUserLog;

    /**
     * FK from appLog to user
     */
    @OneToMany(targetEntity = tAppLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tAppLog> appLog;

    /**
     * FK from deviceAppLog to user
     */
    @OneToMany(targetEntity = tDeviceAppLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceAppLog> deviceAppLog;

    /**
     * FK from support to user
     */
    @OneToMany(targetEntity = tSupport.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tSupport> support;

    /**
     * FK from supportLog to user
     */
    @OneToMany(targetEntity = tSupportLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tSupportLog> supportLog;

    /**
     * FK from ticketStatusLog to user
     */
    @OneToMany(targetEntity = tTicketStatusLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicketStatusLog> ticketStatusLog;

    /**
     * FK from Ticket to User
     */
    @OneToMany(targetEntity = tTicket.class,mappedBy="openUser" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicket> openUser;

    @OneToMany(targetEntity = tTicket.class,mappedBy="assignedUser" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicket> assignedUser;

    /**
     * FK from ticketDetail to user
     */
    @OneToMany(targetEntity = tTicketDetail.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicketDetail> ticketDetail;

    /**
     * FK from userLoginLog to user
     */
    @OneToMany(targetEntity = tUserLoginLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tUserLoginLog> userLoginLog;

    /**
     * FK from TicketLog to user
     */
    @OneToMany(targetEntity = tTicketLog.class,mappedBy="user" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tTicketLog> ticketLog;

    public tUser() {}

    public tUser(int userId, String userName, String userEmail, byte[] passwordHash, byte[] passwordSalt,
                 LocalDateTime creationDate,
                 String languagePreference, String themePreference, tUserStatus userStatus,
                 LocalDateTime inactivationDate, tUserType userType) {
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
        this.userType = userType;
    }

    /**
     * @return the userId.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId to set to.
     */
    public void setUserId(int userId) {
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
     * @return the userType.
     */
    public tUserType getUserType() {
        return userType;
    }

    /**
     * @param userType to set to.
     */
    public void setUserType(tUserType userType) {
        this.userType = userType;
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
