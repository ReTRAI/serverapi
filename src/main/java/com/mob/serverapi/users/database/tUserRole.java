package com.mob.serverapi.users.database;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userRole")
public class tUserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userRoleId", unique = true, nullable = false)
    private int userRoleId;

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

    public tUserRole(int userRoleId, tUser user, tUserType userType) {
        this.userRoleId = userRoleId;
        this.user = user;
        this.userType = userType;
    }

    /**
     * @return the userRoleId.
     */
    public int getUserRoleId() {
        return userRoleId;
    }

    /**
     * @param userRoleId to set to.
     */
    public void setUserRoleId(int userRoleId) {
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
