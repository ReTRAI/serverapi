//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.15 at 03:35:18 PM UTC 
//


package com.mob.serverapi.reseller.base;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mob.serverapi.reseller.base package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mob.serverapi.reseller.base
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetResellerByIdRequest }
     * 
     */
    public GetResellerByIdRequest createGetResellerByIdRequest() {
        return new GetResellerByIdRequest();
    }

    /**
     * Create an instance of {@link GetResellerByIdResponse }
     * 
     */
    public GetResellerByIdResponse createGetResellerByIdResponse() {
        return new GetResellerByIdResponse();
    }

    /**
     * Create an instance of {@link Reseller }
     * 
     */
    public Reseller createReseller() {
        return new Reseller();
    }

    /**
     * Create an instance of {@link RemoveResellerRequest }
     * 
     */
    public RemoveResellerRequest createRemoveResellerRequest() {
        return new RemoveResellerRequest();
    }

    /**
     * Create an instance of {@link GetResellerFilteredRequest }
     * 
     */
    public GetResellerFilteredRequest createGetResellerFilteredRequest() {
        return new GetResellerFilteredRequest();
    }

    /**
     * Create an instance of {@link RemoveResellerResponse }
     * 
     */
    public RemoveResellerResponse createRemoveResellerResponse() {
        return new RemoveResellerResponse();
    }

    /**
     * Create an instance of {@link SetResellerResponse }
     * 
     */
    public SetResellerResponse createSetResellerResponse() {
        return new SetResellerResponse();
    }

    /**
     * Create an instance of {@link GetResellerFilteredResponse }
     * 
     */
    public GetResellerFilteredResponse createGetResellerFilteredResponse() {
        return new GetResellerFilteredResponse();
    }

    /**
     * Create an instance of {@link SetResellerRequest }
     * 
     */
    public SetResellerRequest createSetResellerRequest() {
        return new SetResellerRequest();
    }

}
