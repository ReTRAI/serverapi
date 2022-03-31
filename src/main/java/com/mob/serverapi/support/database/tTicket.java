package com.mob.serverapi.support.database;

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
@Table(name = "ticket")
public class tTicket implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ticketId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID ticketId;

    @Column(name = "openDate", nullable = false)
    private LocalDateTime openDate;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,optional = false)
    //FK to table user, column userId
    @JoinColumn(name = "openUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKET_OPENUSERID"))
    private tUser openUser;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table ticketStatus, column ticketStatusId
    @JoinColumn(name = "ticketStatusId", referencedColumnName = "ticketStatusId",
            foreignKey = @ForeignKey(name="FK_TICKET_TICKETSTATUSID"))
    private tTicketStatus ticketStatus;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, optional = true)
    //FK to table user, column userId
    @JoinColumn(name = "assignedUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKET_ASSIGNEDUSERID"))
    private tUser assignedUser;

    @Column(name = "title", length = 255, nullable = true)
    private String title;

    @Lob
    @Column(name = "attachPath", nullable = true)
    private String attachPath;

    /**
     * FK from ticketDetail to ticket
     */
    @OneToMany(targetEntity = tTicketDetail.class,mappedBy="ticket" , fetch = FetchType.LAZY)
    private Set<tTicketDetail> ticketDetail;

    /**
     * FK from TicketLog to user
     */
    @OneToMany(targetEntity = tTicketLog.class,mappedBy="ticket" , fetch = FetchType.LAZY)
    private Set<tTicketLog> ticketLog;

    public tTicket() {
    }

}
