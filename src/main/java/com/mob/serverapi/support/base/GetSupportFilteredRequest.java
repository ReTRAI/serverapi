//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.01 at 08:54:24 AM UTC 
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
 *         &lt;element name="supportId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="supportName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="onlyChildren" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="field" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberRecords" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "supportId",
    "supportName",
    "onlyChildren",
    "field",
    "orderField",
    "offset",
    "numberRecords"
})
@XmlRootElement(name = "GetSupportFilteredRequest")
public class GetSupportFilteredRequest {

    @XmlElement(required = true)
    protected String supportId;
    @XmlElement(required = true)
    protected String supportName;
    protected boolean onlyChildren;
    @XmlElement(required = true)
    protected String field;
    @XmlElement(required = true)
    protected String orderField;
    protected int offset;
    protected int numberRecords;

    /**
     * Gets the value of the supportId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportId() {
        return supportId;
    }

    /**
     * Sets the value of the supportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportId(String value) {
        this.supportId = value;
    }

    /**
     * Gets the value of the supportName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportName() {
        return supportName;
    }

    /**
     * Sets the value of the supportName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportName(String value) {
        this.supportName = value;
    }

    /**
     * Gets the value of the onlyChildren property.
     * 
     */
    public boolean isOnlyChildren() {
        return onlyChildren;
    }

    /**
     * Sets the value of the onlyChildren property.
     * 
     */
    public void setOnlyChildren(boolean value) {
        this.onlyChildren = value;
    }

    /**
     * Gets the value of the field property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setField(String value) {
        this.field = value;
    }

    /**
     * Gets the value of the orderField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderField() {
        return orderField;
    }

    /**
     * Sets the value of the orderField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderField(String value) {
        this.orderField = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     */
    public void setOffset(int value) {
        this.offset = value;
    }

    /**
     * Gets the value of the numberRecords property.
     * 
     */
    public int getNumberRecords() {
        return numberRecords;
    }

    /**
     * Sets the value of the numberRecords property.
     * 
     */
    public void setNumberRecords(int value) {
        this.numberRecords = value;
    }

}
