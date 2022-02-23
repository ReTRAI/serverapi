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
@Table(name = "deviceStatusLog")
public class tDeviceStatusLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceStatusLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID deviceStatusLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_DEVICESTATUSLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table deviceStatus, column deviceStatusId
    @JoinColumn(name = "alteredId", referencedColumnName = "deviceStatusId",
            foreignKey = @ForeignKey(name="FK_DEVICESTATUSLOG_DEVICESTATUSID"))
    private tDeviceStatus deviceStatus;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tDeviceStatusLog() {
    }


}
