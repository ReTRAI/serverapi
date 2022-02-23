package com.mob.serverapi.users.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "userLoginLog")
public class tUserLoginLog {

    @Id
    @GeneratedValue
    @Column(name = "userLoginLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID userLoginLogId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "userId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_USERLOGINLOG_USERID"))
    private tUser user;

    @Column(name = "loginDate", nullable = false)
    private LocalDateTime loginDate;

    @Column(name = "validAuthentication", nullable = false)
    private boolean validAuthentication;

    @Column(name = "authenticationDetail", nullable = false)
    private String authenticationDetail;

    public tUserLoginLog() {
    }

}
