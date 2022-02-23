package com.mob.serverapi.reseller.database;

import com.mob.serverapi.users.database.tUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "resellerAssociationLog")
public class tResellerAssociationLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerAssociationLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID resellerAssociationLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_RESELLERASSOCLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table resellerAssociation, column resellerAssociationId
    @JoinColumn(name = "alteredId", referencedColumnName = "resellerAssociationId",
            foreignKey = @ForeignKey(name="FK_RESELLERASSOCLOG_RESELLERID"))
    private tResellerAssociation resellerAssociation;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tResellerAssociationLog() {
    }
}
