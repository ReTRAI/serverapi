package com.mob.serverapi.reseller.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "resellerAssociationLog")
public class tResellerAssociationLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resellerAssociationLogId", unique = true, nullable = false)
    public int resellerAssociationLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_RESELLERASSOCLOG_USERID"))
    private tUser user;

    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "alteredId", referencedColumnName = "resellerAssociationId",
            foreignKey = @ForeignKey(name="FK_RESELLERASSOCLOG_RESELLERID"))
    private tResellerAssociation resellerAssociation;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    private tResellerAssociationLog() {
    }

    public tResellerAssociationLog(int resellerAssociationLogId, String action, tUser user,
                        tResellerAssociation resellerAssociation, LocalDateTime alterationDate, String alterationDetail) {
        this.resellerAssociationLogId = resellerAssociationLogId;
        this.action = action;
        this.user = user;
        this.resellerAssociation = resellerAssociation;
        this.alterationDate = alterationDate;
        this.alterationDetail = alterationDetail;
    }

    /**
     * @return the resellerAssociationLogId.
     */
    public int getResellerLogId() {
        return resellerAssociationLogId;
    }

    /**
     * @param resellerAssociationLogId to set to.
     */
    public void setResellerLogId(int resellerAssociationLogId) {
        this.resellerAssociationLogId = resellerAssociationLogId;
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
     * @return the resellerAssociation.
     */
    public tResellerAssociation getAlteredId() {
        return resellerAssociation;
    }

    /**
     * @param resellerAssociation to set to.
     */
    public void setAlteredId(tResellerAssociation resellerAssociation) {
        this.resellerAssociation = resellerAssociation;
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
