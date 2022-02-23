package com.mob.serverapi.apps.database;

import com.mob.serverapi.device.database.tDeviceApp;
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
@Table(name = "app")
public class tApp implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "appId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID appId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "alias", length = 255, nullable = false)
    private String alias;

    @Lob
    @Column(name = "thumbnailPath", nullable = false)
    private String thumbnailPath;

    @Lob
    @Column(name = "apkPath")
    private String apkPath;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "version", length = 100, nullable = false)
    private String version;

    /**
     * FK from appLog to app
     */
    @OneToMany(targetEntity = tAppLog.class,mappedBy="app" , fetch = FetchType.LAZY)
    private Set<tAppLog> appLog;

    /**
     * FK from deviceApp to app
     */
    @OneToMany(targetEntity = tDeviceApp.class,mappedBy="app" , fetch = FetchType.LAZY)
    private Set<tDeviceApp> deviceApp;

    public tApp() {
    }



}
