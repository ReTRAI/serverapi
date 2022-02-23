package com.mob.serverapi.users.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "userRole")
public class tUserRole implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "userRoleId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID userRoleId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERROLE_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId",
            foreignKey = @ForeignKey(name="FK_USERROLE_USERTYPEID"))
    private tUserType userType;

    public tUserRole() {
    }
}
