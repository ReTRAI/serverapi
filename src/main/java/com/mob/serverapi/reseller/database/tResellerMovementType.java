package com.mob.serverapi.reseller.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "resellerMovementType")
public class tResellerMovementType implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerMovementTypeId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerMovementTypeId;

    @Column(name = "description", nullable = false)
    private String description;
    /**
     * FK from tResellerBalance to resellerMovementType
     */
    @OneToMany(targetEntity = tResellerBalance.class, mappedBy = "resellerMovementType", fetch = FetchType.LAZY)
    private Set<tResellerBalance> resellerBalance;

    public tResellerMovementType() {
    }

    public enum ResellerMovementTypeEnum {
        MANUAL_DEBIT,
        MANUAL_CREDIT,
        TRANSFER,
        ACTIVATE_DEVICE
    }

}
