package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
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

}
