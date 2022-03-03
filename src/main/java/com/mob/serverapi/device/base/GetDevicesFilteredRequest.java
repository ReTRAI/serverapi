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
 *         &lt;element name="deviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startActivationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endActivationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="field" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberRecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "resellerId",
    "status",
    "startCreationDate",
    "endCreationDate",
    "startActivationDate",
    "endActivationDate",
    "startExpirationDate",
    "endExpirationDate",
    "field",
    "orderField",
    "offset",
    "numberRecords"
})
@XmlRootElement(name = "getDevicesFilteredRequest")
public class GetDevicesFilteredRequest {

    @XmlElement(required = true)
    protected String deviceId;
    @XmlElement(required = true)
    protected String resellerId;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String startCreationDate;
    @XmlElement(required = true)
    protected String endCreationDate;
    @XmlElement(required = true)
    protected String startActivationDate;
    @XmlElement(required = true)
    protected String endActivationDate;
    @XmlElement(required = true)
    protected String startExpirationDate;
    @XmlElement(required = true)
    protected String endExpirationDate;
    @XmlElement(required = true)
    protected String field;
    @XmlElement(required = true)
    protected String orderField;
    protected int offset;
    protected int numberRecords;

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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     * Gets the value of the startActivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartActivationDate() {
        return startActivationDate;
    }

    /**
     * Sets the value of the startActivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartActivationDate(String value) {
        this.startActivationDate = value;
    }

    /**
     * Gets the value of the endActivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndActivationDate() {
        return endActivationDate;
    }

    /**
     * Sets the value of the endActivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndActivationDate(String value) {
        this.endActivationDate = value;
    }

    /**
     * Gets the value of the startExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartExpirationDate() {
        return startExpirationDate;
    }

    /**
     * Sets the value of the startExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartExpirationDate(String value) {
        this.startExpirationDate = value;
    }

    /**
     * Gets the value of the endExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndExpirationDate() {
        return endExpirationDate;
    }

    /**
     * Sets the value of the endExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndExpirationDate(String value) {
        this.endExpirationDate = value;
    }

    /**
     * Gets the value of the field property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField(String value) {
        this.field = value;
    }

    /**
     * Gets the value of the orderField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderField() {
        return orderField;
    }

    /**
     * Sets the value of the orderField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderField(String value) {
        this.orderField = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets the value of the numberRecords property.
     * 
     */
    public int getNumberRecords() {
        return numberRecords;
    }

    /**
     * Sets the value of the numberRecords property.
     * 
     */
    public void setNumberRecords(int value) {
        this.numberRecords = value;
    }

}
