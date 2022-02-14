package com.mob.serverapi.support.database;

import com.mob.serverapi.reseller.database.tResellerLog;
import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "support")
public class tSupport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supportId", unique = true, nullable = false)
    private int supportId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_SUPPORT_USERID"))
    private tUser user;

    /**
     * FK from supportLog to support
     */
    @OneToMany(targetEntity = tSupportLog.class,mappedBy="support" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tSupportLog> supportLog;


    /**
     * FK from support notification to support
     */
    @OneToMany(targetEntity = tSupportNotification.class,mappedBy="support" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tSupportNotification> supportNotification;

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
