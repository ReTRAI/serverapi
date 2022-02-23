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
@Table(name = "ticketDetail")
public class tTicketDetail {

    @Id
    @GeneratedValue
    @Column(name = "ticketDetailId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID ticketDetailId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table ticket, column ticketId
    @JoinColumn(name = "ticketId", referencedColumnName = "ticketId",
            foreignKey = @ForeignKey(name="FK_TICKETDETAIL_TICKETID"))
    private tTicket ticket;

    @Lob
    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "originalMessage", nullable = false)
    private boolean originalMessage;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "responseUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKETDETAIL_RESPONCEUSERID"))
    private tUser user;

    @Column(name = "detailDate", nullable = false)
    private LocalDateTime detailDate;

    public tTicketDetail() {
    }

}
