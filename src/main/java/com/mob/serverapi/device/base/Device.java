//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.02 at 11:13:17 AM UTC 
//


package com.mob.serverapi.device.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for device complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="device">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deviceStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imeiNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="androidId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstSimNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastConnection" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastBackup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expireDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currentBalance" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="currentMinutes" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="osVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "device", propOrder = {
    "deviceId",
    "resellerId",
    "deviceStatus",
    "brand",
    "model",
    "serialNumber",
    "imeiNumber",
    "simNumber",
    "androidId",
    "firstSimNumber",
    "creationDate",
    "lastConnection",
    "lastBackup",
    "activationDate",
    "expireDate",
    "currentBalance",
    "currentMinutes",
    "notes",
    "osVersion"
})
public class Device {

    @XmlElement(required = true)
    protected String deviceId;
    @XmlElement(required = true)
    protected String resellerId;
    @XmlElement(required = true)
    protected String deviceStatus;
    @XmlElement(required = true)
    protected String brand;
    @XmlElement(required = true)
    protected String model;
    @XmlElement(required = true)
    protected String serialNumber;
    @XmlElement(required = true)
    protected String imeiNumber;
    @XmlElement(required = true)
    protected String simNumber;
    @XmlElement(required = true)
    protected String androidId;
    @XmlElement(required = true)
    protected String firstSimNumber;
    @XmlElement(required = true)
    protected String creationDate;
    @XmlElement(required = true)
    protected String lastConnection;
    @XmlElement(required = true)
    protected String lastBackup;
    @XmlElement(required = true)
    protected String activationDate;
    @XmlElement(required = true)
    protected String expireDate;
    protected float currentBalance;
    protected float currentMinutes;
    @XmlElement(required = true)
    protected String notes;
    @XmlElement(required = true)
    protected String osVersion;

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
     * Gets the value of the resellerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResellerId() {
        return resellerId;
    }

    /**
     * Sets the value of the resellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResellerId(String value) {
        this.resellerId = value;
    }

    /**
     * Gets the value of the deviceStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * Sets the value of the deviceStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceStatus(String value) {
        this.deviceStatus = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the imeiNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImeiNumber() {
        return imeiNumber;
    }

    /**
     * Sets the value of the imeiNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImeiNumber(String value) {
        this.imeiNumber = value;
    }

    /**
     * Gets the value of the simNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimNumber() {
        return simNumber;
    }

    /**
     * Sets the value of the simNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimNumber(String value) {
        this.simNumber = value;
    }

    /**
     * Gets the value of the androidId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAndroidId() {
        return androidId;
    }

    /**
     * Sets the value of the androidId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAndroidId(String value) {
        this.androidId = value;
    }

    /**
     * Gets the value of the firstSimNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstSimNumber() {
        return firstSimNumber;
    }

    /**
     * Sets the value of the firstSimNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstSimNumber(String value) {
        this.firstSimNumber = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationDate(String value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the lastConnection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastConnection() {
        return lastConnection;
    }

    /**
     * Sets the value of the lastConnection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastConnection(String value) {
        this.lastConnection = value;
    }

    /**
     * Gets the value of the lastBackup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastBackup() {
        return lastBackup;
    }

    /**
     * Sets the value of the lastBackup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastBackup(String value) {
        this.lastBackup = value;
    }

    /**
     * Gets the value of the activationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationDate() {
        return activationDate;
    }

    /**
     * Sets the value of the activationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationDate(String value) {
        this.activationDate = value;
    }

    /**
     * Gets the value of the expireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the value of the expireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     */
    public float getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     */
    public void setCurrentBalance(float value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the currentMinutes property.
     * 
     */
    public float getCurrentMinutes() {
        return currentMinutes;
    }

    /**
     * Sets the value of the currentMinutes property.
     * 
     */
    public void setCurrentMinutes(float value) {
        this.currentMinutes = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the osVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * Sets the value of the osVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsVersion(String value) {
        this.osVersion = value;
    }

}
