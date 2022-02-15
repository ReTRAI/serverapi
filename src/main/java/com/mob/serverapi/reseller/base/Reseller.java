//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.15 at 10:20:42 AM UTC 
//


package com.mob.serverapi.reseller.base;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reseller complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reseller">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resellerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resellerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currentBalance" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="activeDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inactiveDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="freeDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reseller", propOrder = {
    "resellerId",
    "userId",
    "resellerName",
    "currentBalance",
    "totalDevices",
    "activeDevices",
    "inactiveDevices",
    "freeDevices"
})
public class Reseller {

    protected int resellerId;
    protected int userId;
    @XmlElement(required = true)
    protected String resellerName;
    @XmlElement(required = true)
    protected BigDecimal currentBalance;
    protected int totalDevices;
    protected int activeDevices;
    protected int inactiveDevices;
    protected int freeDevices;

    /**
     * Gets the value of the resellerId property.
     * 
     */
    public int getResellerId() {
        return resellerId;
    }

    /**
     * Sets the value of the resellerId property.
     * 
     */
    public void setResellerId(int value) {
        this.resellerId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Gets the value of the resellerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResellerName() {
        return resellerName;
    }

    /**
     * Sets the value of the resellerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResellerName(String value) {
        this.resellerName = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentBalance(BigDecimal value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the totalDevices property.
     * 
     */
    public int getTotalDevices() {
        return totalDevices;
    }

    /**
     * Sets the value of the totalDevices property.
     * 
     */
    public void setTotalDevices(int value) {
        this.totalDevices = value;
    }

    /**
     * Gets the value of the activeDevices property.
     * 
     */
    public int getActiveDevices() {
        return activeDevices;
    }

    /**
     * Sets the value of the activeDevices property.
     * 
     */
    public void setActiveDevices(int value) {
        this.activeDevices = value;
    }

    /**
     * Gets the value of the inactiveDevices property.
     * 
     */
    public int getInactiveDevices() {
        return inactiveDevices;
    }

    /**
     * Sets the value of the inactiveDevices property.
     * 
     */
    public void setInactiveDevices(int value) {
        this.inactiveDevices = value;
    }

    /**
     * Gets the value of the freeDevices property.
     * 
     */
    public int getFreeDevices() {
        return freeDevices;
    }

    /**
     * Sets the value of the freeDevices property.
     * 
     */
    public void setFreeDevices(int value) {
        this.freeDevices = value;
    }

}
