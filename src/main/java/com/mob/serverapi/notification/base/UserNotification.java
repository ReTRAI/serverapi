//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.09 at 05:12:05 PM UTC 
//


package com.mob.serverapi.notification.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userNotification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userNotification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userNotificationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="checked" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="checkedDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userNotification", propOrder = {
    "userNotificationId",
    "userId",
    "creationDate",
    "detail",
    "checked",
    "checkedDate"
})
public class UserNotification {

    protected long userNotificationId;
    protected long userId;
    protected long creationDate;
    protected long detail;
    protected long checked;
    protected long checkedDate;

    /**
     * Gets the value of the userNotificationId property.
     * 
     */
    public long getUserNotificationId() {
        return userNotificationId;
    }

    /**
     * Sets the value of the userNotificationId property.
     * 
     */
    public void setUserNotificationId(long value) {
        this.userNotificationId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     */
    public long getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     */
    public void setCreationDate(long value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     */
    public long getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     */
    public void setDetail(long value) {
        this.detail = value;
    }

    /**
     * Gets the value of the checked property.
     * 
     */
    public long getChecked() {
        return checked;
    }

    /**
     * Sets the value of the checked property.
     * 
     */
    public void setChecked(long value) {
        this.checked = value;
    }

    /**
     * Gets the value of the checkedDate property.
     * 
     */
    public long getCheckedDate() {
        return checkedDate;
    }

    /**
     * Sets the value of the checkedDate property.
     * 
     */
    public void setCheckedDate(long value) {
        this.checkedDate = value;
    }

}
