//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.04 at 12:17:07 PM UTC 
//


package com.mob.serverapi.dashboard.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Expiring complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Expiring">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="renewed" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="in30Days" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="in15Days" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="in7Days" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Expiring", propOrder = {
    "renewed",
    "in30Days",
    "in15Days",
    "in7Days"
})
public class Expiring {

    protected long renewed;
    protected long in30Days;
    protected long in15Days;
    protected long in7Days;

    /**
     * Gets the value of the renewed property.
     * 
     */
    public long getRenewed() {
        return renewed;
    }

    /**
     * Sets the value of the renewed property.
     * 
     */
    public void setRenewed(long value) {
        this.renewed = value;
    }

    /**
     * Gets the value of the in30Days property.
     * 
     */
    public long getIn30Days() {
        return in30Days;
    }

    /**
     * Sets the value of the in30Days property.
     * 
     */
    public void setIn30Days(long value) {
        this.in30Days = value;
    }

    /**
     * Gets the value of the in15Days property.
     * 
     */
    public long getIn15Days() {
        return in15Days;
    }

    /**
     * Sets the value of the in15Days property.
     * 
     */
    public void setIn15Days(long value) {
        this.in15Days = value;
    }

    /**
     * Gets the value of the in7Days property.
     * 
     */
    public long getIn7Days() {
        return in7Days;
    }

    /**
     * Sets the value of the in7Days property.
     * 
     */
    public void setIn7Days(long value) {
        this.in7Days = value;
    }

}
