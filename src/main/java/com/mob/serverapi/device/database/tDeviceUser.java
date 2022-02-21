package com.mob.serverapi.device.database;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "userActivationPassword", nullable = false)
    @Lob
    private String userActivationPassword;

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


    /**
     * @return the deviceUserId.
     */
    public UUID getDeviceUserId() {
        return deviceUserId;
    }

    /**
     * @param deviceUserId to set to.
     */
    public void setDeviceUserId(UUID deviceUserId) {
        this.deviceUserId = deviceUserId;
    }

    /**
     * @return the nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname to set to.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the creationDate.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate to set to.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the device.
     */
    public tDevice getDevice() {
        return device;
    }

    /**
     * @param device to set to.
     */
    public void setDevice(tDevice device) {
        this.device = device;
    }

    /**
     * @return the userActivationPassword.
     */
    public String getUserActivationPassword() {
        return userActivationPassword;
    }

    /**
     * @param userActivationPassword to set to.
     */
    public void setUserActivationPassword(String userActivationPassword) {
        this.userActivationPassword = userActivationPassword;
    }
}
