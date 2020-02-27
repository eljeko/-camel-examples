package org.apache.camel.example.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.service.AccountGreeterImpl;
import org.apache.camel.example.service.GreetMeImpl;
import org.apache.camel.example.service.SayHiImpl;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        final String cxfUri =
                "cxf:http://localhost:8080/accountGreeter?"
                        + "serviceClass=" + AccountGreeterImpl.class.getCanonicalName();


        from(cxfUri)
                .id("vanilla-jax-ws")
                .transform(simple("${in.body[0]}"))
                .log("${routeId}> request = ${body}")
                .bean(AccountGreeterImpl.class)
                .log("${routeId}> response = ${body}");


        final String cxfUriOperation =
                "cxf:http://localhost:8080/accountGreeterOperation?"
                        + "serviceClass=" + AccountGreeterImpl.class.getCanonicalName();


        from(cxfUriOperation)
                .id("operations-routed")
                .transform(simple("${in.body[0]}"))
                .log("${routeId}> request = ${body}")
                    .choice()
                        .when(simple(
                                "${in.header.operationName} == 'greetMe'"
                        ))
                        .bean(GreetMeImpl.class)
                        .when(simple(
                                "${in.header.operationName} == 'sayHi'"
                        ))
                        .bean(SayHiImpl.class)
                    .otherwise()
                        .log("${routeId}> Unknown operation")
                        .setFaultBody(constant("Unknown operation"))
                    .end()
                .log("${routeId}> response = ${body}");

    }

}