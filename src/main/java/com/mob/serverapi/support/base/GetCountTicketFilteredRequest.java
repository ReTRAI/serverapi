//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.23 at 04:55:54 PM UTC 
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
    "endCreationDate"
})
@XmlRootElement(name = "getCountTicketFilteredRequest")
public class GetCountTicketFilteredRequest {

    @XmlElement(required = true)
    protected String ticketId;
    @XmlElement(required = true)
    protected String ticketStatus;
    @XmlElement(required = true)
    protected String startCreationDate;
    @XmlElement(required = true)
    protected String endCreationDate;

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

}
