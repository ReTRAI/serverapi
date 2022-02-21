package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "supportAssociationLog")
public class tSupportAssociationLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "supportAssociationLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID supportAssociationLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name = "FK_SUPPORTASSOCLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table resellerAssociation, column resellerAssociationId
    @JoinColumn(name = "alteredId", referencedColumnName = "supportAssociationId",
            foreignKey = @ForeignKey(name = "FK_SUPPORTASSOCLOG_SUPPORTID"))
    private tSupportAssociation supportAssociation;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tSupportAssociationLog() {
    }


    /**
     * @return the supportAssociationLogId.
     */
    public UUID getSupportAssociationLogId() {
        return supportAssociationLogId;
    }

    /**
     * @param supportAssociationLogId to set to.
     */
    public void setSupportAssociationLogId(UUID supportAssociationLogId) {
        this.supportAssociationLogId = supportAssociationLogId;
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
     * @return the supportAssociation.
     */
    public tSupportAssociation getAlteredId() {
        return supportAssociation;
    }

    /**
     * @param supportAssociation to set to.
     */
    public void setAlteredId(tSupportAssociation supportAssociation) {
        this.supportAssociation = supportAssociation;
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
