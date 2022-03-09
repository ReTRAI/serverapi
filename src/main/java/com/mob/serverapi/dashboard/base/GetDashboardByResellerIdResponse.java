//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.09 at 05:12:05 PM UTC 
//


package com.mob.serverapi.dashboard.base;

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
 *         &lt;element name="global" type="{http://www.mob.com/serverapi/dashboard/base}global"/>
 *         &lt;element name="inactive" type="{http://www.mob.com/serverapi/dashboard/base}inactive"/>
 *         &lt;element name="expiring" type="{http://www.mob.com/serverapi/dashboard/base}expiring"/>
 *         &lt;element name="active" type="{http://www.mob.com/serverapi/dashboard/base}active"/>
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
    "global",
    "inactive",
    "expiring",
    "active"
})
@XmlRootElement(name = "getDashboardByResellerIdResponse")
public class GetDashboardByResellerIdResponse {

    @XmlElement(required = true)
    protected Global global;
    @XmlElement(required = true)
    protected Inactive inactive;
    @XmlElement(required = true)
    protected Expiring expiring;
    @XmlElement(required = true)
    protected Active active;

    /**
     * Gets the value of the global property.
     * 
     * @return
     *     possible object is
     *     {@link Global }
     *     
     */
    public Global getGlobal() {
        return global;
    }

    /**
     * Sets the value of the global property.
     * 
     * @param value
     *     allowed object is
     *     {@link Global }
     *     
     */
    public void setGlobal(Global value) {
        this.global = value;
    }

    /**
     * Gets the value of the inactive property.
     * 
     * @return
     *     possible object is
     *     {@link Inactive }
     *     
     */
    public Inactive getInactive() {
        return inactive;
    }

    /**
     * Sets the value of the inactive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Inactive }
     *     
     */
    public void setInactive(Inactive value) {
        this.inactive = value;
    }

    /**
     * Gets the value of the expiring property.
     * 
     * @return
     *     possible object is
     *     {@link Expiring }
     *     
     */
    public Expiring getExpiring() {
        return expiring;
    }

    /**
     * Sets the value of the expiring property.
     * 
     * @param value
     *     allowed object is
     *     {@link Expiring }
     *     
     */
    public void setExpiring(Expiring value) {
        this.expiring = value;
    }

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Active }
     *     
     */
    public Active getActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Active }
     *     
     */
    public void setActive(Active value) {
        this.active = value;
    }

}
