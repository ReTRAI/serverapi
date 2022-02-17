package com.mob.serverapi.users.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

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

    public tUserRole(UUID userRoleId, tUser user, tUserType userType) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.userType = userType;
    }

    /**
     * @return the userRoleId.
     */
    public UUID getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId to set to.
     */
    public void setUserRoleId(UUID userRoleId) {
        this.userRoleId = userRoleId;
    }

    /**
     * @return the user.
     */
    public tUser getUser() {
        return user;
    }

    /**
     * @param user to set to.
     */
    public void setUser(tUser user) {
        this.user = user;
    }

    /**
     * @return the userType.
     */
    public tUserType getUserType() {
        return userType;
    }

    /**
     * @param userType to set to.
     */
    public void setUserType(tUserType userType) {
        this.userType = userType;
    }
}
