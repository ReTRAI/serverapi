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

}
