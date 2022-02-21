package com.mob.serverapi.users.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "userLog")
public class tUserLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "userLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID userLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOG_ACTIONUSERID"))
    private tUser actionUser;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "alteredId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOG_ALTEREDUSERID"))
    private tUser alteredUser;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tUserLog() {
    }


    /**
     * @return the userLogId.
     */
    public UUID getUserLogId() {
        return userLogId;
    }

    /**
     * @param userLogId to set to.
     */
    public void setUserLogId(UUID userLogId) {
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
    public tUser getActionUser() {
        return actionUser;
    }

    /**
     * @param actionUser to set to.
     */
    public void setActionUser(tUser actionUser) {
        this.actionUser = actionUser;
    }

    /**
     * @return the alteredId.
     */
    public tUser getAlteredUser() {
        return alteredUser;
    }

    /**
     * @param alteredUser to set to.
     */
    public void setAlteredUser(tUser alteredUser) {
        this.alteredUser = alteredUser;
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
