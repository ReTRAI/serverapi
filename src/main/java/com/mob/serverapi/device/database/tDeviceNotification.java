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
@Table(name = "deviceNotification")
public class tDeviceNotification implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceNotificationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID deviceNotificationId;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "detail", nullable = false)
    @Lob
    private String detail;

    @Column(name = "checked", nullable = false)
    private boolean checked;

    @Column(name = "checkedDate")
    private LocalDateTime checkedDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to device, column deviceId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICENOTIFICATION_DEVICEID"))
    private tDevice device;

    public tDeviceNotification() {}

}
