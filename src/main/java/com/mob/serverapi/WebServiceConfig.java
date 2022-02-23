package com.mob.serverapi;

import com.mob.serverapi.device.repositories.endpoints.DeviceRepository;
import com.mob.serverapi.reseller.repositories.endpoints.ResellerRepository;
import com.mob.serverapi.servicefault.DetailSoapFaultDefinitionExceptionResolver;
import com.mob.serverapi.servicefault.ServiceFaultException;
import com.mob.serverapi.support.repositories.endpoints.SupportRepository;
import com.mob.serverapi.users.repositories.endpoints.UserRepository;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    // bean definitions

    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver(){
        SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        errorMappings.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/*");
    }

    /**
     * DIRECTORY BEANS
     */

    @Bean(name = "users")
    public DefaultWsdl11Definition usersWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UsersPort");
        wsdl11Definition.setLocationUri("/");
        wsdl11Definition.setTargetNamespace("http://www.mob.com/serverapi/users/base");
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean(name = "resellers")
    public DefaultWsdl11Definition resellerWsdl11Definition(XsdSchema resellersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ResellerPort");
        wsdl11Definition.setLocationUri("/");
        wsdl11Definition.setTargetNamespace("http://www.mob.com/serverapi/reseller/base");
        wsdl11Definition.setSchema(resellersSchema);
        return wsdl11Definition;
    }

    @Bean(name = "support")
    public DefaultWsdl11Definition supportWsdl11Definition(XsdSchema supportSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SupportPort");
        wsdl11Definition.setLocationUri("/");
        wsdl11Definition.setTargetNamespace("http://www.mob.com/serverapi/support/base");
        wsdl11Definition.setSchema(supportSchema);
        return wsdl11Definition;
    }

    @Bean(name = "devices")
    public DefaultWsdl11Definition deviceWsdl11Definition(XsdSchema deviceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DevicesPort");
        wsdl11Definition.setLocationUri("/");
        wsdl11Definition.setTargetNamespace("http://www.mob.com/serverapi/device/base");
        wsdl11Definition.setSchema(deviceSchema);
        return wsdl11Definition;
    }

    /**
     * ENDPOINT BEANS
     */
    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("endpoints/users.xsd"));
    }

    @Bean
    public XsdSchema resellersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("endpoints/resellers.xsd"));
    }

    @Bean
    public XsdSchema supportSchema() {
        return new SimpleXsdSchema(new ClassPathResource("endpoints/support.xsd"));
    }

    @Bean
    public XsdSchema deviceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("endpoints/devices.xsd"));
    }

    /**
     * REPOSITORY BEANS
     */
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public ResellerRepository resellerRepository() {
        return new ResellerRepository();
    }

    @Bean
    public SupportRepository supportRepository() {
        return new SupportRepository();
    }

    @Bean
    public DeviceRepository deviceRepository() {
        return new DeviceRepository();
    }
}
