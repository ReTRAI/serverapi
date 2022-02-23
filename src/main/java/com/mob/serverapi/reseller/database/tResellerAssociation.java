package com.mob.serverapi.reseller.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "resellerAssociation")
public class tResellerAssociation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resellerAssociationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerAssociationId;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parentResellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLER_PARENTID"))
    private tReseller parentReseller;

    //FK to resellerId
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "childResellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLER_CHILDID"))
    private tReseller childReseller;

    public tResellerAssociation() {}



}
