//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.03.02 at 10:18:07 AM UTC 
//


package com.mob.serverapi.dashboard.base;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mob.serverapi.dashboard.base package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mob.serverapi.dashboard.base
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDashboardByResellerIdRequest }
     * 
     */
    public GetDashboardByResellerIdRequest createGetDashboardByResellerIdRequest() {
        return new GetDashboardByResellerIdRequest();
    }

    /**
     * Create an instance of {@link GetDashboardByResellerIdResponse }
     * 
     */
    public GetDashboardByResellerIdResponse createGetDashboardByResellerIdResponse() {
        return new GetDashboardByResellerIdResponse();
    }

    /**
     * Create an instance of {@link Global }
     * 
     */
    public Global createGlobal() {
        return new Global();
    }

    /**
     * Create an instance of {@link Inactive }
     * 
     */
    public Inactive createInactive() {
        return new Inactive();
    }

    /**
     * Create an instance of {@link Expiring }
     * 
     */
    public Expiring createExpiring() {
        return new Expiring();
    }

    /**
     * Create an instance of {@link Active }
     * 
     */
    public Active createActive() {
        return new Active();
    }

}
