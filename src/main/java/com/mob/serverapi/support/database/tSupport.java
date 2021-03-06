package com.mob.serverapi.support.database;

import com.mob.serverapi.reseller.database.tResellerAssociation;
import com.mob.serverapi.reseller.database.tResellerLog;
import com.mob.serverapi.users.database.tUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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
    @OneToMany(targetEntity = tSupportLog.class,mappedBy="support" , fetch = FetchType.LAZY)
    private Set<tSupportLog> supportLog;



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

}
