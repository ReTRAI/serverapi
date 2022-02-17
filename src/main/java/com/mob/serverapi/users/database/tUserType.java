package com.mob.serverapi.users.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "userType")
public class tUserType implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "userTypeId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID userTypeId;

    @Column(name = "description", nullable = false)
    private String description;
    public enum UserTypeEnum {
        ADMIN,
        RESELLER,
        SUPPORT;
    }

    /**
     * FK from userRole to userType
     */
    @OneToMany(targetEntity = tUserRole.class,mappedBy="userType" , fetch = FetchType.LAZY)
    private Set<tUserRole> userRoles;

    public tUserType() {}

    public tUserType(UUID userTypeId, String description) {
        this.userTypeId = userTypeId;
        this.description = description;
    }

    /**
     * @return the userTypeId.
     */
    public UUID getUserTypeId() {
        return userTypeId;
    }

    /**
     * @param userTypeId to set to.
     */
    public void setUserTypeId(UUID userTypeId) {
        this.userTypeId = userTypeId;
    }

    /**
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description to set to.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
