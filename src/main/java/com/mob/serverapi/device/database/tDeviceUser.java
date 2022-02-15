package com.mob.serverapi.device.database;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "deviceUser")
public class tDeviceUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceUserId", unique = true, nullable = false)
    public int deviceUserId;

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
    @OneToMany(targetEntity = tDeviceUserLog.class,mappedBy="deviceUser" , fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<tDeviceUserLog> deviceUser;

    public tDeviceUser() {
    }



    public tDeviceUser(int deviceUserId, String nickname, LocalDateTime creationDate, String userActivationPassword,
                       tDevice device) {
        this.deviceUserId = deviceUserId;
        this.nickname = nickname;
        this.creationDate = creationDate;
        this.userActivationPassword = userActivationPassword;
        this.device = device;
    }

    /**
     * @return the deviceUserId.
     */
    public int getDeviceUserId() {
        return deviceUserId;
    }

    /**
     * @param deviceUserId to set to.
     */
    public void setDeviceUserId(int deviceUserId) {
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
