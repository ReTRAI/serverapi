package com.mob.serverapi.support.database;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ticketStatus")
public class tTicketStatus implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ticketStatusId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID ticketStatusId;

    @Column(name = "description", nullable = false)
    private String description;
    public enum TicketStatusEnum {
        OPEN,
        ONPROGRESS,
        PENDING,
        CANCELED,
        COMPLETED;
    }


    public tTicketStatus() {
    }


}
