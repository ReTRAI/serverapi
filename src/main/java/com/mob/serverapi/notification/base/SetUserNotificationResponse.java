//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.17 at 11:41:40 AM UTC 
//


package com.mob.serverapi.notification.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userNotification" type="{http://www.mob.com/serverapi/notification/base}userNotification"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userNotification"
})
@XmlRootElement(name = "setUserNotificationResponse")
public class SetUserNotificationResponse {

    @XmlElement(required = true)
    protected UserNotification userNotification;

    /**
     * Gets the value of the userNotification property.
     * 
     * @return
     *     possible object is
     *     {@link UserNotification }
     *     
     */
    public UserNotification getUserNotification() {
        return userNotification;
    }

    /**
     * Sets the value of the userNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserNotification }
     *     
     */
    public void setUserNotification(UserNotification value) {
        this.userNotification = value;
    }

}
