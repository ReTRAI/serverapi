//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.31 at 01:14:17 PM UTC 
//


package com.mob.serverapi.reseller.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResellerAssociation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResellerAssociation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resellerAssociationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parentResellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childResellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResellerAssociation", propOrder = {
    "resellerAssociationId",
    "parentResellerId",
    "childResellerId"
})
public class ResellerAssociation {

    @XmlElement(required = true)
    protected String resellerAssociationId;
    @XmlElement(required = true)
    protected String parentResellerId;
    @XmlElement(required = true)
    protected String childResellerId;

    /**
     * Gets the value of the resellerAssociationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResellerAssociationId() {
        return resellerAssociationId;
    }

    /**
     * Sets the value of the resellerAssociationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResellerAssociationId(String value) {
        this.resellerAssociationId = value;
    }

    /**
     * Gets the value of the parentResellerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentResellerId() {
        return parentResellerId;
    }

    /**
     * Sets the value of the parentResellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentResellerId(String value) {
        this.parentResellerId = value;
    }

    /**
     * Gets the value of the childResellerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildResellerId() {
        return childResellerId;
    }

    /**
     * Sets the value of the childResellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChildResellerId(String value) {
        this.childResellerId = value;
    }

}
