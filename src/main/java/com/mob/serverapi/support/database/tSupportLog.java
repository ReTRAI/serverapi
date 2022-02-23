package com.mob.serverapi.support.database;

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


}
