package com.mob.serverapi.users.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
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
    @OneToMany(targetEntity = tUser.class, mappedBy="userStatus" , fetch = FetchType.LAZY)
    private Set<tUser> user;

    public tUserStatus() {
    }

}
