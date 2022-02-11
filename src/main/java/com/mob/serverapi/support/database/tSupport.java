package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "support")
public class tSupport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supportId", unique = true, nullable = false)
    private int supportId;

    @OneToOne(cascade = CascadeType.ALL,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_SUPPORT_USERID"))
    private tUser user;

    /**
     * FK from supportLog to support
     */
    @OneToOne(mappedBy = "support")
    private tSupportLog supportLog;

    /**
     * FK from support notification to support
     */
    @OneToOne(mappedBy = "support")
    private tSupportNotification supportNotification;

    public tSupport() {
    }

    public tSupport(int supportId, tUser user) {
        this.supportId = supportId;
        this.user = user;
    }

    /**
     * @return supportId.
     */

    public int getSupportId() {
        return supportId;
    }
    /**
     * @param supportId  to set to.
     */
    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }
    /**
     * @return user.
     */

    public tUser getUser() {
        return user;
    }
    /**
     * @param user  to set to.
     */
    public void setUser(tUser user) {
        this.user = user;
    }
}
