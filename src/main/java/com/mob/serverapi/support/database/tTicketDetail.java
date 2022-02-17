package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "responseUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKETDETAIL_RESPONCEUSERID"))
    private tUser user;

    @Column(name = "responseDate", nullable = false)
    private LocalDateTime responseDate;

    public tTicketDetail() {
    }

    public tTicketDetail(UUID ticketDetailId, tTicket ticket, String detail, tUser user,
                         LocalDateTime responseDate) {
        this.ticketDetailId = ticketDetailId;
        this.ticket = ticket;
        this.detail = detail;
        this.user = user;
        this.responseDate = responseDate;
    }

    /**
     * @return the ticketDetailId.
     */
    public UUID getTicketDetailId() {
        return ticketDetailId;
    }
    /**
     * @param ticketDetailId to set to.
     */
    public void setTicketDetailId(UUID ticketDetailId) {
        this.ticketDetailId = ticketDetailId;
    }
    /**
     * @return the ticket.
     */
    public tTicket getTicket() {
        return ticket;
    }
    /**
     * @param ticket to set to.
     */
    public void setTicket(tTicket ticket) {
        this.ticket = ticket;
    }
    /**
     * @return the detail.
     */
    public String getDetail() {
        return detail;
    }
    /**
     * @param detail to set to.
     */
    public void setDetail(String detail) {
        this.detail = detail;
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
     * @return the responseDate.
     */
    public LocalDateTime getResponseDate() {
        return responseDate;
    }
    /**
     * @param responseDate to set to.
     */
    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }
}
