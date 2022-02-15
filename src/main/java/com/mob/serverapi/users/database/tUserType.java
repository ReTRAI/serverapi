package com.mob.serverapi.users.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "userType")
public class tUserType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTypeId", unique = true, nullable = false)
    private int userTypeId;

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

    public tUserType(int userTypeId, String description) {
        this.userTypeId = userTypeId;
        this.description = description;
    }

    /**
     * @return the userTypeId.
     */
    public int getUserTypeId() {
        return userTypeId;
    }

    /**
     * @param userTypeId to set to.
     */
    public void setUserTypeId(int userTypeId) {
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
