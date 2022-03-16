//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.16 at 03:55:22 PM UTC 
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
 *         &lt;element name="startMovementDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endMovementDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="minValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="debitCredit" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "startMovementDate",
    "endMovementDate",
    "minValue",
    "maxValue",
    "debitCredit"
})
@XmlRootElement(name = "getCountDeviceBalanceMovementsRequest")
public class GetCountDeviceBalanceMovementsRequest {

    @XmlElement(required = true)
    protected String deviceId;
    @XmlElement(required = true)
    protected String startMovementDate;
    @XmlElement(required = true)
    protected String endMovementDate;
    @XmlElement(required = true)
    protected String minValue;
    @XmlElement(required = true)
    protected String maxValue;
    @XmlElement(required = true)
    protected String debitCredit;

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
     * Gets the value of the startMovementDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartMovementDate() {
        return startMovementDate;
    }

    /**
     * Sets the value of the startMovementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartMovementDate(String value) {
        this.startMovementDate = value;
    }

    /**
     * Gets the value of the endMovementDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndMovementDate() {
        return endMovementDate;
    }

    /**
     * Sets the value of the endMovementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndMovementDate(String value) {
        this.endMovementDate = value;
    }

    /**
     * Gets the value of the minValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinValue(String value) {
        this.minValue = value;
    }

    /**
     * Gets the value of the maxValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxValue(String value) {
        this.maxValue = value;
    }

    /**
     * Gets the value of the debitCredit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCredit() {
        return debitCredit;
    }

    /**
     * Sets the value of the debitCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCredit(String value) {
        this.debitCredit = value;
    }

}
