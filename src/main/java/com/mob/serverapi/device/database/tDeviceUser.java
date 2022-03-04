package com.mob.serverapi.device.database;


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
@Table(name = "deviceUser")
public class tDeviceUser implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "deviceUserId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    public UUID deviceUserId;

    @Column(name = "nickname", nullable = false)
    public String nickname;


    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;


    @Column(name = "userActivationPasswordSalt", nullable = false)
    @Lob
    private byte[] userActivationPasswordSalt;

    @Column(name = "userActivationPasswordHash", nullable = false)
    @Lob
    private byte[] userActivationPasswordHash;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to table device, column deviceId
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId",
            foreignKey = @ForeignKey(name="FK_DEVICEUSER_DEVICEID"))
    private tDevice device;


    /**
     * FK from deviceUserLog to deviceUser
     */
    @OneToMany(targetEntity = tDeviceUserLog.class,mappedBy="deviceUser" , fetch = FetchType.LAZY)
    private Set<tDeviceUserLog> deviceUser;

    public tDeviceUser() {
    }

}
