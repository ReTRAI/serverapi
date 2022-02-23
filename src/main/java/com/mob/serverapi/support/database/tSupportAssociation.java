package com.mob.serverapi.support.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "supportAssociation")
public class tSupportAssociation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "supportAssociationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID supportAssociationId;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parentSupportId", referencedColumnName = "supportId",
            foreignKey = @ForeignKey(name = "FK_SUPPORT_PARENTID"))
    private tSupport parentSupport;

    //FK to resellerId
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "childSupportId", referencedColumnName = "supportId",
            foreignKey = @ForeignKey(name = "FK_SUPPORT_CHILDID"))
    private tSupport childSupport;

    public tSupportAssociation() {
    }


}
