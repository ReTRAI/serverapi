package com.mob.serverapi.users.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userType")
public class tUserType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userTypeId", unique = true, nullable = false)
    private int userTypeId;

    @Column(name = "description", nullable = false)
    private UserTypeEnum description;
    public enum UserTypeEnum {
        ADMIN,
        RESELLER,
        SUPPORT;
    }

    /**
     * FK from user to userType
     */
    @OneToOne(mappedBy = "userType")
    private tUser user;

    public tUserType() {}

    public tUserType(int userTypeId, UserTypeEnum description) {
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
    public UserTypeEnum getDescription() {
        return description;
    }

    /**
     * @param description to set to.
     */
    public void setDescription(UserTypeEnum description) {
        this.description = description;
    }
}
