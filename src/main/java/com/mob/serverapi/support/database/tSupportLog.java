package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "supportLog")
public class tSupportLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "supportLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID supportLogId;

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


    /**
     * @return the supportLogId.
     */
    public UUID getSupportLogId() {
        return supportLogId;
    }
    /**
     * @param supportLogId to set to.
     */
    public void setSupportLogId(UUID supportLogId) {
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
