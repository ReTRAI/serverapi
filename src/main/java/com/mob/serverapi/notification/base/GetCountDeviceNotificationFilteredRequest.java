//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.16 at 03:55:22 PM UTC 
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
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="checked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startCheckedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endCheckedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "startCreationDate",
    "endCreationDate",
    "checked",
    "startCheckedDate",
    "endCheckedDate"
})
@XmlRootElement(name = "getCountDeviceNotificationFilteredRequest")
public class GetCountDeviceNotificationFilteredRequest {

    @XmlElement(required = true)
    protected String deviceId;
    @XmlElement(required = true)
    protected String startCreationDate;
    @XmlElement(required = true)
    protected String endCreationDate;
    @XmlElement(required = true)
    protected String checked;
    @XmlElement(required = true)
    protected String startCheckedDate;
    @XmlElement(required = true)
    protected String endCheckedDate;

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
     * Gets the value of the startCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCreationDate() {
        return startCreationDate;
    }

    /**
     * Sets the value of the startCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCreationDate(String value) {
        this.startCreationDate = value;
    }

    /**
     * Gets the value of the endCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCreationDate() {
        return endCreationDate;
    }

    /**
     * Sets the value of the endCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCreationDate(String value) {
        this.endCreationDate = value;
    }

    /**
     * Gets the value of the checked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChecked() {
        return checked;
    }

    /**
     * Sets the value of the checked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChecked(String value) {
        this.checked = value;
    }

    /**
     * Gets the value of the startCheckedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCheckedDate() {
        return startCheckedDate;
    }

    /**
     * Sets the value of the startCheckedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCheckedDate(String value) {
        this.startCheckedDate = value;
    }

    /**
     * Gets the value of the endCheckedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCheckedDate() {
        return endCheckedDate;
    }

    /**
     * Sets the value of the endCheckedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCheckedDate(String value) {
        this.endCheckedDate = value;
    }

}