package com.mob.serverapi.device.database;

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
@Table(name = "deviceAppLog")
public class tDeviceAppLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceAppLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID deviceAppLogId;

    @Column(name = "action")
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table deviceApp, column deviceAppId
    @JoinColumn(name = "deviceAppId", referencedColumnName = "deviceAppId",
            foreignKey = @ForeignKey(name="FK_DEVICEAPPLOG_DEVICEAPPID"))
    private tDeviceApp deviceApp;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tDeviceAppLog() {
    }


}
