package com.mob.serverapi.reseller.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "resellerAssociation")
public class tResellerAssociation implements Serializable {

    @Id
    @GeneratedValue
    @JoinColumn(name = "resellerAssociationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID resellerAssociationId;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parentResellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLER_PARENTID"))
    private tReseller parentReseller;

    //FK to resellerId
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "childResellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLER_CHILDID"))
    private tReseller childReseller;

    public tResellerAssociation() {}

    public tResellerAssociation(UUID resellerAssociationId, tReseller parentReseller, tReseller childReseller) {
        this.resellerAssociationId = resellerAssociationId;
        this.parentReseller = parentReseller;
        this.childReseller = childReseller;

    }

    /**
     * @return the ResellerAssociationId.
     */

    public UUID getResellerAssociationId() {
        return resellerAssociationId;
    }

    /**
     * @param resellerAssociationId the id to set to.
     */
    public void setResellerAssociationId(UUID resellerAssociationId) {
        this.resellerAssociationId = resellerAssociationId;
    }

    /**
     * @return the parentReseller.
     */

    public tReseller getParentReseller() {
        return parentReseller;
    }

    /**
     * @param parentReseller  to set to.
     */
    public void setParentReseller(tReseller parentReseller) {
        this.parentReseller = parentReseller;
    }


    /**
     * @return the childReseller.
     */

    public tReseller getChildReseller() {
        return childReseller;
    }

    /**
     * @param childReseller to set to.
     */
    public void setChildReseller(tReseller childReseller) {
        this.childReseller = childReseller;
    }

}
