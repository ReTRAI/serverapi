//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.15 at 02:37:22 PM UTC 
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
 *         &lt;element name="reseller" type="{http://www.mob.com/serverapi/reseller/base}reseller"/>
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
    "reseller"
})
@XmlRootElement(name = "setResellerResponse")
public class SetResellerResponse {

    @XmlElement(required = true)
    protected Reseller reseller;

    /**
     * Gets the value of the reseller property.
     * 
     * @return
     *     possible object is
     *     {@link Reseller }
     *     
     */
    public Reseller getReseller() {
        return reseller;
    }

    /**
     * Sets the value of the reseller property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reseller }
     *     
     */
    public void setReseller(Reseller value) {
        this.reseller = value;
    }

}
