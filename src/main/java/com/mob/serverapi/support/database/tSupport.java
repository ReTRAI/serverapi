package com.mob.serverapi.support.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerLog;
import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "support")
public class tSupport implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "supportId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID supportId;

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

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    /**
     * FK from SupportAssociation to Support
     */
    @OneToMany(targetEntity = tSupportAssociation.class,mappedBy="parentSupport" , fetch = FetchType.LAZY)
    private Set<tSupportAssociation> supportAssociationParent;

    @OneToMany(targetEntity = tSupportAssociation.class,mappedBy="childSupport" , fetch = FetchType.LAZY)
    private Set<tSupportAssociation> supportAssociationChild;


    public tSupport() {
    }

    /**
     * @return supportId.
     */

    public UUID getSupportId() {
        return supportId;
    }
    /**
     * @param supportId  to set to.
     */
    public void setSupportId(UUID supportId) {
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


    /**
     * @return getCreationDate.
     */

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate  to set to.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
