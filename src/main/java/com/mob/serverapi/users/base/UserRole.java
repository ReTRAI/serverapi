//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.23 at 04:55:54 PM UTC 
//


package com.mob.serverapi.users.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userRole">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userRoleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userRoleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userRole", propOrder = {
    "userRoleId",
    "userRoleName"
})
public class UserRole {

    @XmlElement(required = true)
    protected String userRoleId;
    @XmlElement(required = true)
    protected String userRoleName;

    /**
     * Gets the value of the userRoleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRoleId() {
        return userRoleId;
    }

    /**
     * Sets the value of the userRoleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRoleId(String value) {
        this.userRoleId = value;
    }

    /**
     * Gets the value of the userRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * Sets the value of the userRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRoleName(String value) {
        this.userRoleName = value;
    }

}
