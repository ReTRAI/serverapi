//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.04 at 12:17:07 PM UTC 
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
 *         &lt;element name="parentResellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childResellerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "parentResellerId",
    "childResellerId",
    "actionUserId"
})
@XmlRootElement(name = "RemoveResellerAssociationRequest")
public class RemoveResellerAssociationRequest {

    @XmlElement(required = true)
    protected String parentResellerId;
    @XmlElement(required = true)
    protected String childResellerId;
    @XmlElement(required = true)
    protected String actionUserId;

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
