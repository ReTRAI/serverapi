package com.mob.serverapi.support.database;

import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticketDetail")
public class tTicketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketDetailId", unique = true, nullable = false)
    private int ticketDetailId;

    @OneToOne(cascade = CascadeType.MERGE,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "ticketId", referencedColumnName = "ticketId",
            foreignKey = @ForeignKey(name="FK_TICKETDETAIL_TICKETID"))
    private tTicket ticket;


    @Lob
    @Column(name = "detail", nullable = false)
    private String detail;


    @OneToOne(cascade = CascadeType.ALL,optional = false)
    //FK to table User, column userId
    @JoinColumn(name = "responseUserId", referencedColumnName = "userId",
            foreignKey = @ForeignKey(name="FK_TICKETDETAIL_RESPONCEUSERID"))
    private tUser user;

    @Column(name = "responseDate", nullable = false)
    private LocalDateTime responseDate;

    public tTicketDetail() {
    }

    public tTicketDetail(int ticketDetailId, tTicket ticket, String detail, tUser user,
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
    public int getTicketDetailId() {
        return ticketDetailId;
    }
    /**
     * @param ticketDetailId to set to.
     */
    public void setTicketDetailId(int ticketDetailId) {
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
