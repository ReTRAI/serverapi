//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.03 at 05:38:10 PM UTC 
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
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imeiNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="simNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="androidId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "brand",
    "model",
    "serialNumber",
    "imeiNumber",
    "simNumber",
    "androidId",
    "actionUserId"
})
@XmlRootElement(name = "setDeviceRequest")
public class SetDeviceRequest {

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
    protected String actionUserId;

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
