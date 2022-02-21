package com.mob.serverapi.reseller.database;


import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resellerLog")
public class tResellerLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID resellerLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_RESELLERLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table reseller, column resellerId
    @JoinColumn(name = "alteredId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLERLOG_RESELLERID"))
    private tReseller reseller;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tResellerLog() {
    }


    /**
     * @return the userLogId.
     */
    public UUID getResellerLogId() {
        return resellerLogId;
    }

    /**
     * @param resellerLogId to set to.
     */
    public void setResellerLogId(UUID resellerLogId) {
        this.resellerLogId = resellerLogId;
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
     * @return the reseller.
     */
    public tReseller getAlteredId() {
        return reseller;
    }

    /**
     * @param reseller to set to.
     */
    public void setAlteredId(tReseller reseller) {
        this.reseller = reseller;
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
