package com.mob.serverapi.reseller.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resellerAssociation")
public class tResellerAssociation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "resellerAssociationId", unique = true, nullable = false)
    private int resellerAssociationId;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parentResellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLER_PARENTID"))
    private tReseller parentReseller;

    //FK to resellerId
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "childResellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLER_CHILDID"))
    private tReseller childReseller;

    protected tResellerAssociation() {}

    public tResellerAssociation(int resellerAssociationId, tReseller parentReseller, tReseller childReseller) {
        this.resellerAssociationId = resellerAssociationId;
        this.parentReseller = parentReseller;
        this.childReseller = childReseller;

    }

    /**
     * @return the ResellerAssociationId.
     */

    public int getResellerAssociationId() {
        return resellerAssociationId;
    }

    /**
     * @param resellerAssociationId the id to set to.
     */
    public void setResellerAssociationId(int resellerAssociationId) {
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
