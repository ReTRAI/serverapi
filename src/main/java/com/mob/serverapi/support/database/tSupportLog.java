package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "supportLog")
public class tSupportLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supportLogId", unique = true, nullable = false)
    public int supportLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_SUPPORTLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table support, column supportId
    @JoinColumn(name = "alteredId", referencedColumnName = "supportId",
            foreignKey = @ForeignKey(name="FK_SUPPORTLOG_SUPPORTID"))
    private tSupport support;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tSupportLog() {
    }

    public tSupportLog(int supportLogId, String action, tUser user, tSupport support,
                       LocalDateTime alterationDate, String alterationDetail) {
        this.supportLogId = supportLogId;
        this.action = action;
        this.user = user;
        this.support = support;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }

    /**
     * @return the supportLogId.
     */
    public int getSupportLogId() {
        return supportLogId;
    }
    /**
     * @param supportLogId to set to.
     */
    public void setSupportLogId(int supportLogId) {
        this.supportLogId = supportLogId;
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
     * @return the support.
     */
    public tSupport getSupport() {
        return support;
    }
    /**
     * @param support to set to.
     */
    public void setSupport(tSupport support) {
        this.support = support;
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
