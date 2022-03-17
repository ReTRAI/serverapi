//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.17 at 11:41:40 AM UTC 
//


package com.mob.serverapi.reseller.base;

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
 *         &lt;element name="resellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="debitCredit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="movementValue" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="movementType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="movementDetail" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "resellerId",
    "debitCredit",
    "movementValue",
    "movementType",
    "movementDetail",
    "actionUserId"
})
@XmlRootElement(name = "setResellerBalanceMovementRequest")
public class SetResellerBalanceMovementRequest {

    @XmlElement(required = true)
    protected String resellerId;
    @XmlElement(required = true)
    protected String debitCredit;
    protected float movementValue;
    @XmlElement(required = true)
    protected String movementType;
    @XmlElement(required = true)
    protected String movementDetail;
    @XmlElement(required = true)
    protected String actionUserId;

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

    /**
     * Gets the value of the movementValue property.
     * 
     */
    public float getMovementValue() {
        return movementValue;
    }

    /**
     * Sets the value of the movementValue property.
     * 
     */
    public void setMovementValue(float value) {
        this.movementValue = value;
    }

    /**
     * Gets the value of the movementType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementType() {
        return movementType;
    }

    /**
     * Sets the value of the movementType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementType(String value) {
        this.movementType = value;
    }

    /**
     * Gets the value of the movementDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovementDetail() {
        return movementDetail;
    }

    /**
     * Sets the value of the movementDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovementDetail(String value) {
        this.movementDetail = value;
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
