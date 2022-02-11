package com.mob.serverapi.users.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userStatus")
public class tUserStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userStatusId", unique = true, nullable = false)
    private int userStatusId;

    @Column(name = "description", nullable = false)
    private UserStatusEnum description;
    public enum UserStatusEnum {
        ACTIVE,
        INACTIVE,
        BLOCKED,
        CHANGEPW;
    }
    /**
     * FK from user to userStatus
     */
    @OneToOne(mappedBy = "userStatus")
    private tUser user;


    public tUserStatus() {
    }

    public tUserStatus(int userStatusId, UserStatusEnum description) {
        this.userStatusId = userStatusId;
        this.description = description;
    }

    /**
     * @return the userStatusId.
     */
    public int getUserStatusId() {
        return userStatusId;
    }

    /**
     * @param userStatusId to set to.
     */
    public void setUserStatusId(int userStatusId) {
        this.userStatusId = userStatusId;
    }

    /**
     * @return the description.
     */
    public UserStatusEnum getDescription() {
        return description;
    }

    /**
     * @param description to set to.
     */
    public void setDescription(UserStatusEnum description) {
        this.description = description;
    }
}
