package com.mob.serverapi.users.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "userLog")
public class tUserLog implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "userLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID userLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOG_ACTIONUSERID"))
    private tUser actionUser;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "alteredId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOG_ALTEREDUSERID"))
    private tUser alteredUser;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tUserLog() {
    }


}
