package com.mob.serverapi.support.database;

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
@Table(name = "supportNotification")
public class tSupportNotification implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "supportNotificationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID supportNotificationId;

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
    //FK to support, column supportId
    @JoinColumn(name = "supportId", referencedColumnName = "supportId",
            foreignKey = @ForeignKey(name="FK_SUPPORTNOTIFICATION_SUPPORTID"))
    private tSupport support;

    public tSupportNotification() {
    }

}
