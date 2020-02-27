package org.jboss.camel;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.cdi.ContextName;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.component.properties.PropertiesComponent;

@ApplicationScoped
@ContextName("reverse-proxy-demo-route")
public class BasicReverseProxyRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        HttpComponent httpComponent = new HttpComponent();
        getContext().addComponent("http", httpComponent);

        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation("classpath:proxyreverse.properties");
        getContext().addComponent("properties", pc);

        from("servlet:proxy?matchOnUriPrefix=true")
        .log("About to proxy: {{proxedurl}}")
        .to("http4:{{proxedurl}}?bridgeEndpoint=true");
    }

}
