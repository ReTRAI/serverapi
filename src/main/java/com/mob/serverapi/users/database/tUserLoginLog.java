package com.mob.serverapi.users.database;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "userLoginLog")
public class tUserLoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userLoginLogId", unique = true, nullable = false)
    private int userLoginLogId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOGINLOG_USERID"))
    private tUser user;

    @Column(name = "loginDate", nullable = false)
    private LocalDateTime loginDate;

    @Column(name = "validAuthentication", nullable = false)
    private boolean validAuthentication;

    @Column(name = "authenticationDetail", nullable = false)
    private String authenticationDetail;

    public tUserLoginLog() {
    }

    public tUserLoginLog(int userLoginLogId, tUser user, LocalDateTime loginDate,
                         boolean validAuthentication, String authenticationDetail) {
        this.userLoginLogId = userLoginLogId;
        this.user = user;
        this.loginDate = loginDate;
        this.validAuthentication = validAuthentication;
        this.authenticationDetail = authenticationDetail;
    }

    /**
     * @return the userLoginLogId.
     */
    public int getUserLoginLogId() {
        return userLoginLogId;
    }
    /**
     * @param userLoginLogId to set to.
     */
    public void setUserLoginLogId(int userLoginLogId) {
        this.userLoginLogId = userLoginLogId;
    }
    /**
     * @return the user.
     */
    public tUser getUser() {
        return user;
    }
    /**
     * @param user to set to.
     */
    public void setUser(tUser user) {
        this.user = user;
    }
    /**
     * @return the loginDate.
     */
    public LocalDateTime getLoginDate() {
        return loginDate;
    }
    /**
     * @param loginDate to set to.
     */
    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }
    /**
     * @return the validAuthentication.
     */
    public boolean isValidAuthentication() {
        return validAuthentication;
    }
    /**
     * @param validAuthentication to set to.
     */
    public void setValidAuthentication(boolean validAuthentication) {
        this.validAuthentication = validAuthentication;
    }
    /**
     * @return the authenticationDetail.
     */
    public String getAuthenticationDetail() {
        return authenticationDetail;
    }
    /**
     * @param authenticationDetail to set to.
     */
    public void setAuthenticationDetail(String authenticationDetail) {
        this.authenticationDetail = authenticationDetail;
    }
}
