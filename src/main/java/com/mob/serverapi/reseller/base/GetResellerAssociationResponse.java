//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.17 at 05:42:51 PM UTC 
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
 *         &lt;element name="resellerAssociation" type="{http://www.mob.com/serverapi/reseller/base}resellerAssociation"/>
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
    "resellerAssociation"
})
@XmlRootElement(name = "getResellerAssociationResponse")
public class GetResellerAssociationResponse {

    @XmlElement(required = true)
    protected ResellerAssociation resellerAssociation;

    /**
     * Gets the value of the resellerAssociation property.
     * 
     * @return
     *     possible object is
     *     {@link ResellerAssociation }
     *     
     */
    public ResellerAssociation getResellerAssociation() {
        return resellerAssociation;
    }

    /**
     * Sets the value of the resellerAssociation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResellerAssociation }
     *     
     */
    public void setResellerAssociation(ResellerAssociation value) {
        this.resellerAssociation = value;
    }

}