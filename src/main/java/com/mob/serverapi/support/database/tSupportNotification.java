package com.mob.serverapi.support.database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "supportNotification")
public class tSupportNotification implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "supportNotificationId", columnDefinition = "BINARY(16)", unique = true, updatable = false, nullable = false)
    private UUID supportNotificationId;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "detail", nullable = false)
    @Lob
    private String detail;

    @Column(name = "checked", nullable = false)
    private boolean checked;

    @Column(name = "checkedDate")
    private LocalDateTime checkedDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false)
    //FK to support, column supportId
    @JoinColumn(name = "supportId", referencedColumnName = "supportId",
            foreignKey = @ForeignKey(name="FK_SUPPORTNOTIFICATION_SUPPORTID"))
    private tSupport support;

    public tSupportNotification() {
    }


    /**
     * @return the supportNotificationId.
     */
    public UUID getSupportNotificationId() {
        return supportNotificationId;
    }
    /**
     * @param supportNotificationId to set to.
     */
    public void setSupportNotificationId(UUID supportNotificationId) {
        this.supportNotificationId = supportNotificationId;
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
     * @return the checked.
     */
    public boolean isChecked() {
        return checked;
    }
    /**
     * @param checked to set to.
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    /**
     * @return the checkedDate.
     */
    public LocalDateTime getCheckedDate() {
        return checkedDate;
    }
    /**
     * @param checkedDate to set to.
     */
    public void setCheckedDate(LocalDateTime checkedDate) {
        this.checkedDate = checkedDate;
    }
    /**
     * @return the support.
     */
    public tSupport getSupport() {
        return support;
    }
    /**
     * @param support to set to.
     */
    public void setSupport(tSupport support) {
        this.support = support;
    }
}
