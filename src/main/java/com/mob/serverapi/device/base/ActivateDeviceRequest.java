//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.04 at 06:25:39 PM UTC 
//


package com.mob.serverapi.device.base;

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
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerNickname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "deviceId",
    "ownerNickname",
    "actionUserId"
})
@XmlRootElement(name = "activateDeviceRequest")
public class ActivateDeviceRequest {

    @XmlElement(required = true)
    protected String deviceId;
    @XmlElement(required = true)
    protected String ownerNickname;
    @XmlElement(required = true)
    protected String actionUserId;

    /**
     * Gets the value of the deviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the value of the deviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceId(String value) {
        this.deviceId = value;
    }

    /**
     * Gets the value of the ownerNickname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerNickname() {
        return ownerNickname;
    }

    /**
     * Sets the value of the ownerNickname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerNickname(String value) {
        this.ownerNickname = value;
    }

    /**
     * Gets the value of the actionUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionUserId() {
        return actionUserId;
    }

    /**
     * Sets the value of the actionUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionUserId(String value) {
        this.actionUserId = value;
    }

}