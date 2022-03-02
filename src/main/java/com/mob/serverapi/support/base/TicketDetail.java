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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ticketDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ticketDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticketId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ticketDetailId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="originalMessage" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="detailDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detailUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ticketDetail", propOrder = {
    "ticketId",
    "ticketDetailId",
    "detail",
    "originalMessage",
    "detailDate",
    "detailUserId"
})
public class TicketDetail {

    @XmlElement(required = true)
    protected String ticketId;
    @XmlElement(required = true)
    protected String ticketDetailId;
    @XmlElement(required = true)
    protected String detail;
    protected boolean originalMessage;
    @XmlElement(required = true)
    protected String detailDate;
    @XmlElement(required = true)
    protected String detailUserId;

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
     * Gets the value of the ticketDetailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketDetailId() {
        return ticketDetailId;
    }

    /**
     * Sets the value of the ticketDetailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketDetailId(String value) {
        this.ticketDetailId = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetail(String value) {
        this.detail = value;
    }

    /**
     * Gets the value of the originalMessage property.
     * 
     */
    public boolean isOriginalMessage() {
        return originalMessage;
    }

    /**
     * Sets the value of the originalMessage property.
     * 
     */
    public void setOriginalMessage(boolean value) {
        this.originalMessage = value;
    }

    /**
     * Gets the value of the detailDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailDate() {
        return detailDate;
    }

    /**
     * Sets the value of the detailDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailDate(String value) {
        this.detailDate = value;
    }

    /**
     * Gets the value of the detailUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailUserId() {
        return detailUserId;
    }

    /**
     * Sets the value of the detailUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailUserId(String value) {
        this.detailUserId = value;
    }

}
