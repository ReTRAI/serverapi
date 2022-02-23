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
@Table(name = "resellerBalance")
public class tResellerBalance implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerBalanceId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerBalanceId;

    @Column(name = "debitCredit", nullable = false)
    private String debitCredit;

    @Column(name = "movementValue", nullable = false)
    private float movementValue;

    @Column(name = "movementDate", nullable = false)
    private LocalDateTime movementDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "resellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLERBALANCE_RESELLERID"))
    private tReseller reseller;

    public tResellerBalance() {}

}
