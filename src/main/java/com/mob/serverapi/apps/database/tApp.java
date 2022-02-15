package com.mob.serverapi.apps.database;

import com.mob.serverapi.device.database.tDeviceApp;
import com.mob.serverapi.users.database.tUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "app")
public class tApp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appId", unique = true, nullable = false)
    private int appId;

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

    public tApp(int appId, String name, String alias, String thumbnailPath, String apkPath,
                LocalDateTime creationDate, LocalDateTime updatedDate, String version) {
        this.appId = appId;
        this.name = name;
        this.alias = alias;
        this.thumbnailPath = thumbnailPath;
        this.apkPath = apkPath;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.version = version;
    }

    /**
     * @return the appId.
     */
    public int getAppId() {
        return appId;
    }
    /**
     * @param appId to set to.
     */
    public void setAppId(int appId) {
        this.appId = appId;
    }

    /**
     * @return the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name to set to.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the alias.
     */
    public String getAlias() {
        return alias;
    }
    /**
     * @param alias to set to.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the thumbnailPath.
     */
    public String getThumbnailPath() {
        return thumbnailPath;
    }
    /**
     * @param thumbnailPath to set to.
     */
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
    /**
     * @return the apkPath.
     */
    public String getApkPath() {
        return apkPath;
    }
    /**
     * @param apkPath to set to.
     */
    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
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
     * @return the updatedDate.
     */
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    /**
     * @param updatedDate to set to.
     */
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    /**
     * @return the version.
     */
    public String getVersion() {
        return version;
    }
    /**
     * @param version to set to.
     */
    public void setVersion(String version) {
        this.version = version;
    }


}
