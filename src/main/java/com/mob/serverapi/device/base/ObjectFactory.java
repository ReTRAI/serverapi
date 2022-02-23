//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.23 at 04:55:54 PM UTC 
//


package com.mob.serverapi.device.base;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mob.serverapi.device.base package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mob.serverapi.device.base
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetDeviceResponse }
     * 
     */
    public SetDeviceResponse createSetDeviceResponse() {
        return new SetDeviceResponse();
    }

    /**
     * Create an instance of {@link Device }
     * 
     */
    public Device createDevice() {
        return new Device();
    }

    /**
     * Create an instance of {@link GetDeviceByIdRequest }
     * 
     */
    public GetDeviceByIdRequest createGetDeviceByIdRequest() {
        return new GetDeviceByIdRequest();
    }

    /**
     * Create an instance of {@link GetDeviceByIdResponse }
     * 
     */
    public GetDeviceByIdResponse createGetDeviceByIdResponse() {
        return new GetDeviceByIdResponse();
    }

    /**
     * Create an instance of {@link SetDeviceRequest }
     * 
     */
    public SetDeviceRequest createSetDeviceRequest() {
        return new SetDeviceRequest();
    }

}