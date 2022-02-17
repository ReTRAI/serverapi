package com.mob.serverapi.users.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "userStatus")
public class tUserStatus implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "userStatusId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID userStatusId;

    @Column(name = "description", nullable = false)
    private String description;

    public enum UserStatusEnum {
        ACTIVE,
        INACTIVE,
        BLOCKED,
        CHANGEPW;
    }
    /**
     * FK from user to userStatus
     */
    @OneToMany(targetEntity = tUser.class,mappedBy="userStatus" , fetch = FetchType.LAZY)
    private Set<tUser> user;

    public tUserStatus() {
    }

    public tUserStatus(UUID userStatusId, String description) {
        this.userStatusId = userStatusId;
        this.description = description;
    }

    /**
     * @return the userStatusId.
     */
    public UUID getUserStatusId() {
        return userStatusId;
    }

    /**
     * @param userStatusId to set to.
     */
    public void setUserStatusId(UUID userStatusId) {
        this.userStatusId = userStatusId;
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
