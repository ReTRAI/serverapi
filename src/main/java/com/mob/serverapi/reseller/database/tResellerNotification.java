package com.mob.serverapi.reseller.database;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "resellerNotification")
public class tResellerNotification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resellerNotificationId", unique = true, nullable = false)
    private int resellerNotificationId;

    @Column(name = "creationDate", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "detail", nullable = false)
    @Lob
    private String detail;

    @Column(name = "checked", nullable = false)
    private boolean checked;

    @Column(name = "checkedDate")
    private LocalDateTime checkedDate;

    @OneToOne(cascade = CascadeType.MERGE,optional = false)
    //FK to Reseller, column resellerId
    @JoinColumn(name = "resellerId", referencedColumnName = "resellerId",
            foreignKey = @ForeignKey(name="FK_RESELLENOTIFICATION_RESELLERID"))
    private tReseller reseller;

    protected tResellerNotification() {}

    public tResellerNotification(int resellerNotificationId, LocalDateTime creationDate,
                                 String detail,boolean checked,
                                 LocalDateTime checkedDate, tReseller reseller) {
        this.resellerNotificationId = resellerNotificationId;
        this.creationDate = creationDate;
        this.detail = detail;
        this.checked = checked;
        this.checkedDate = checkedDate;
        this.reseller = reseller;
    }

    /**
     * @return the resellerNotificationId.
     */
    public int getResellerNotificationId() {
        return resellerNotificationId;
    }

    /**
     * @param resellerNotificationId to set to.
     */
    public void setResellerNotificationId(int resellerNotificationId) {
        this.resellerNotificationId = resellerNotificationId;
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
     * @return the reseller.
     */
    public tReseller getReseller() {
        return reseller;
    }

    /**
     * @param reseller to set to.
     */
    public void setReseller(tReseller reseller) {
        this.reseller = reseller;
    }
}
