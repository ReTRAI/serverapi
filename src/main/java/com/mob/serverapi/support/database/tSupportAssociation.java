package com.mob.serverapi.support.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

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


    /**
     * @return the supportAssociationId.
     */

    public UUID getSupportAssociationId() {
        return supportAssociationId;
    }

    /**
     * @param supportAssociationId the id to set to.
     */
    public void setSupportAssociationId(UUID supportAssociationId) {
        this.supportAssociationId = supportAssociationId;
    }

    /**
     * @return the parentSupport.
     */

    public tSupport getParentSupport() {
        return parentSupport;
    }

    /**
     * @param parentSupport to set to.
     */
    public void setParentSupport(tSupport parentSupport) {
        this.parentSupport = parentSupport;
    }


    /**
     * @return the childSupport.
     */

    public tSupport getChildSupport() {
        return childSupport;
    }

    /**
     * @param childSupport to set to.
     */
    public void setChildSupport(tSupport childSupport) {
        this.childSupport = childSupport;
    }

}
