package com.mob.serverapi.reseller.database;

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
@Table(name = "resellerNotification")
public class tResellerNotification implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerNotificationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerNotificationId;

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
    //FK to Reseller, column resellerId
    @JoinColumn(name = "resellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLENOTIFICATION_RESELLERID"))
    private tReseller reseller;

    protected tResellerNotification() {}

}
