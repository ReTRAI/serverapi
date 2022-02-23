package com.mob.serverapi.apps.database;

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
@Table(name = "appLog")
public class tAppLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "appLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID appLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_APPLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table App, column appId
    @JoinColumn(name = "appId", referencedColumnName = "appId",
            foreignKey = @ForeignKey(name="FK_APPLOG_DEVICEID"))
    private tApp app;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tAppLog() {
    }


}
