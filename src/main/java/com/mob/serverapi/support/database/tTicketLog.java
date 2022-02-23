package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ticketLog")
public class tTicketLog {

    @Id
    @GeneratedValue
    @Column(name = "ticketLogId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID ticketLogId;

    @Column(name = "action", nullable = false)
    public String action;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "actionUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKETLOG_USERID"))
    private tUser user;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table ticket, column ticketId
    @JoinColumn(name = "alteredId", referencedColumnName = "ticketId",
            foreignKey = @ForeignKey(name="FK_TICKETLOG_TICKETID"))
    private tTicket ticket;

    @Column(name = "alterationDate", nullable = false)
    private LocalDateTime alterationDate;

    @Column(name = "alterationDetail", nullable = false)
    private String alterationDetail;

    public tTicketLog() {
    }


}
