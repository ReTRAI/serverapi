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
 * <p>Java class for Inactive complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Inactive">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wiped" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="blocked" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="suspended" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="remaining" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Inactive", propOrder = {
    "wiped",
    "blocked",
    "suspended",
    "remaining"
})
public class Inactive {

    protected long wiped;
    protected long blocked;
    protected long suspended;
    protected long remaining;

    /**
     * Gets the value of the wiped property.
     * 
     */
    public long getWiped() {
        return wiped;
    }

    /**
     * Sets the value of the wiped property.
     * 
     */
    public void setWiped(long value) {
        this.wiped = value;
    }

    /**
     * Gets the value of the blocked property.
     * 
     */
    public long getBlocked() {
        return blocked;
    }

    /**
     * Sets the value of the blocked property.
     * 
     */
    public void setBlocked(long value) {
        this.blocked = value;
    }

    /**
     * Gets the value of the suspended property.
     * 
     */
    public long getSuspended() {
        return suspended;
    }

    /**
     * Sets the value of the suspended property.
     * 
     */
    public void setSuspended(long value) {
        this.suspended = value;
    }

    /**
     * Gets the value of the remaining property.
     * 
     */
    public long getRemaining() {
        return remaining;
    }

    /**
     * Sets the value of the remaining property.
     * 
     */
    public void setRemaining(long value) {
        this.remaining = value;
    }

}
