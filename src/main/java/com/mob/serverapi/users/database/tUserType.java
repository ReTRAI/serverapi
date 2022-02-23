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


}
