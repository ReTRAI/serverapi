//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.16 at 03:55:22 PM UTC 
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
 *         &lt;element name="parentSupportId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childSupportId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "parentSupportId",
    "childSupportId",
    "actionUserId"
})
@XmlRootElement(name = "removeSupportAssociationRequest")
public class RemoveSupportAssociationRequest {

    @XmlElement(required = true)
    protected String parentSupportId;
    @XmlElement(required = true)
    protected String childSupportId;
    @XmlElement(required = true)
    protected String actionUserId;

    /**
     * Gets the value of the parentSupportId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentSupportId() {
        return parentSupportId;
    }

    /**
     * Sets the value of the parentSupportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentSupportId(String value) {
        this.parentSupportId = value;
    }

    /**
     * Gets the value of the childSupportId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildSupportId() {
        return childSupportId;
    }

    /**
     * Sets the value of the childSupportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChildSupportId(String value) {
        this.childSupportId = value;
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
