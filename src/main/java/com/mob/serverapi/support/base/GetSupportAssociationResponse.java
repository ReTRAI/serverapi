//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.02 at 11:13:17 AM UTC 
//


package com.mob.serverapi.support.base;

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
 *         &lt;element name="supportAssociation" type="{http://www.mob.com/serverapi/support/base}supportAssociation"/>
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
    "supportAssociation"
})
@XmlRootElement(name = "getSupportAssociationResponse")
public class GetSupportAssociationResponse {

    @XmlElement(required = true)
    protected SupportAssociation supportAssociation;

    /**
     * Gets the value of the supportAssociation property.
     * 
     * @return
     *     possible object is
     *     {@link SupportAssociation }
     *     
     */
    public SupportAssociation getSupportAssociation() {
        return supportAssociation;
    }

    /**
     * Sets the value of the supportAssociation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportAssociation }
     *     
     */
    public void setSupportAssociation(SupportAssociation value) {
        this.supportAssociation = value;
    }

}
