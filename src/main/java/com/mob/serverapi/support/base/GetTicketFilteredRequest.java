//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.02 at 10:18:07 AM UTC 
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
 *         &lt;element name="ticketId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ticketStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endCreationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "ticketId",
    "ticketStatus",
    "startCreationDate",
    "endCreationDate",
    "field",
    "orderField",
    "offset",
    "numberRecords"
})
@XmlRootElement(name = "getTicketFilteredRequest")
public class GetTicketFilteredRequest {

    @XmlElement(required = true)
    protected String ticketId;
    @XmlElement(required = true)
    protected String ticketStatus;
    @XmlElement(required = true)
    protected String startCreationDate;
    @XmlElement(required = true)
    protected String endCreationDate;
    @XmlElement(required = true)
    protected String field;
    @XmlElement(required = true)
    protected String orderField;
    protected int offset;
    protected int numberRecords;

    /**
     * Gets the value of the ticketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Sets the value of the ticketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the value of the ticketStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketStatus() {
        return ticketStatus;
    }

    /**
     * Sets the value of the ticketStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketStatus(String value) {
        this.ticketStatus = value;
    }

    /**
     * Gets the value of the startCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartCreationDate() {
        return startCreationDate;
    }

    /**
     * Sets the value of the startCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartCreationDate(String value) {
        this.startCreationDate = value;
    }

    /**
     * Gets the value of the endCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndCreationDate() {
        return endCreationDate;
    }

    /**
     * Sets the value of the endCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndCreationDate(String value) {
        this.endCreationDate = value;
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
