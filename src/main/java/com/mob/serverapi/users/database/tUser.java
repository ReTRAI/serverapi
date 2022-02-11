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

    @Column(name = "password", nullable = false)
    @Lob
    private String password;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "languagePreference", length=2, nullable = false)
    private String languagePreference;

    @Column(name = "themePreference", length=1, nullable = false)
    private String themePreference;

    @OneToOne(cascade = CascadeType.ALL,optional = false)
    //FK to table UserType, column userTypeId
    @JoinColumn(name = "userStatusId", referencedColumnName = "userStatusId",
            foreignKey = @ForeignKey(name="FK_USER_USERSTATUSID"))
    private tUserStatus userStatus;

    @Column(name = "inactivationDate", nullable = true)
    private LocalDateTime inactivationDate;


    @OneToOne(cascade = CascadeType.ALL)
    //FK to table UserType, column userTypeId
    @JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId",
            foreignKey = @ForeignKey(name="FK_USER_USERTYPEID"))
    private tUserType userType;

    /**
     * FK from reseller to user
     */
    @OneToOne(mappedBy = "user")
    private tReseller reseller;

    /**
     * FK from UserLog to user
     */
    @OneToOne(mappedBy = "user")
    private tUserLog userLog;

    /**
     * FK from ResellerLog to user
     */
    @OneToOne(mappedBy = "user")
    private tResellerLog resellerLog;

    /**
     * FK from ResellerAssociationLog to user
     */
    @OneToOne(mappedBy = "user")
    private tResellerAssociationLog resellerAssociationLog;

    /**
     * FK from DeviceLog to user
     */
    @OneToOne(mappedBy = "user")
    private tDeviceLog deviceLog;

    /**
     * FK from DeviceStatusLog to user
     */
    @OneToOne(mappedBy = "user")
    private tDeviceStatusLog deviceStatusLog;

    /**
     * FK from DeviceUserLog to user
     */
    @OneToOne(mappedBy = "user")
    private tDeviceUserLog deviceUserLog;


    /**
     * FK from appLog to user
     */
    @OneToOne(mappedBy = "user")
    private tAppLog appLog;


    /**
     * FK from deviceAppLog to user
     */
    @OneToOne(mappedBy = "user")
    private tDeviceAppLog deviceAppLog;


    /**
     * FK from support to user
     */
    @OneToOne(mappedBy = "user")
    private tSupport support;

    /**
     * FK from supportLog to user
     */
    @OneToOne(mappedBy = "user")
    private tSupportLog supportLog;

    /**
     * FK from ticketStatusLog to user
     */
    @OneToOne(mappedBy = "user")
    private tTicketStatusLog ticketStatusLog;

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
    @OneToOne(mappedBy = "user")
    private tTicketDetail ticketDetail;

    /**
     * FK from userLoginLog to user
     */
    @OneToOne(mappedBy = "user")
    private tUserLoginLog userLoginLog;

    /**
     * FK from TicketLog to user
     */
    @OneToOne(mappedBy = "user")
    private tTicketLog ticketLog;

    protected tUser() {}

    public tUser(int userId, String userName, String userEmail, String password, LocalDateTime creationDate,
                 String languagePreference, String themePreference, tUserStatus userStatus,
                 LocalDateTime inactivationDate, tUserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
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
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password to set to.
     */
    public void setPassword(String password) {
        this.password = password;
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
