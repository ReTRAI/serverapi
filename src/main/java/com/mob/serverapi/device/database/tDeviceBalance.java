package com.mob.serverapi.device.database;


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
@Table(name = "deviceBalance")
public class tDeviceBalance implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceBalanceId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceBalanceId;

    @Column(name = "debitCredit", nullable = false)
    private String debitCredit;

    @Column(name = "movementValue", nullable = false)
    private float movementValue;

    @Column(name = "movementDate", nullable = false)
    private LocalDateTime movementDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to device, column deviceId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICEBALANCE_DEVICEID"))
    private tDevice device;

    public tDeviceBalance() {}

}