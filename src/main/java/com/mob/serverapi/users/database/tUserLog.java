package com.mob.serverapi.users.database;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "userLog")
public class tUserLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userLogId", unique = true, nullable = false)
    public int userLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @OneToOne(cascade = CascadeType.ALL,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOG_USERID"))
    private tUser user;

    @Column(name = "alteredId", nullable = false)
    private int alteredId;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tUserLog() {
    }

    public tUserLog(int userLogId, String action, tUser user,
                    int alteredId, LocalDateTime alterationDate, String alterationDetail) {
        this.userLogId = userLogId;
        this.action = action;
        this.user = user;
        this.alteredId = alteredId;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }

    /**
     * @return the userLogId.
     */
    public int getUserLogId() {
        return userLogId;
    }

    /**
     * @param userLogId to set to.
     */
    public void setUserLogId(int userLogId) {
        this.userLogId = userLogId;
    }

    /**
     * @return the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action to set to.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the actionUserId.
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
     * @return the alteredId.
     */
    public int getAlteredId() {
        return alteredId;
    }

    /**
     * @param alteredId to set to.
     */
    public void setAlteredId(int alteredId) {
        this.alteredId = alteredId;
    }

    /**
     * @return the alterationDate.
     */
    public LocalDateTime getAlterationDate() {
        return alterationDate;
    }

    /**
     * @param alterationDate to set to.
     */
    public void setAlterationDate(LocalDateTime alterationDate) {
        this.alterationDate = alterationDate;
    }

    /**
     * @return the alterationDetail.
     */
    public String getAlterationDetail() {
        return alterationDetail;
    }

    /**
     * @param alterationDetail to set to.
     */
    public void setAlterationDetail(String alterationDetail) {
        this.alterationDetail = alterationDetail;
    }
}
