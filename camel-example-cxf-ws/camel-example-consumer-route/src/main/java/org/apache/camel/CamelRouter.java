package org.apache.camel;

import org.apache.account.Account;
import org.apache.camel.builder.AccountRequestBuilder;
import org.apache.account.AccountRespone;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        List<AccountRespone> defaultResponse = new ArrayList<AccountRespone>();
        AccountRespone accountRespone = new AccountRespone();
        accountRespone.setAccount(new Account());
        defaultResponse.add(accountRespone);



        // @formatter:off
        restConfiguration()
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Greeting REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiProperty("base.path", "camel/")
                .apiProperty("api.path", "/")
                .apiProperty("host", "")
                .apiContextRouteId("doc-api")
                .component("servlet")             ;
               // .bindingMode(RestBindingMode.auto);
        
        rest("/account").id("account-rest").description("Account finder {id}")
                .get("/{id}").produces("application/json").param()
                    .dataType("long")
                    .type(RestParamType.path)
                    .name("id")
                .endParam()                 
                .outType(Account.class)
                    .route().id("account-by-id")
                        .setBody(simple("${header.id}"))
                        .log("${routeId}> New request = ${body}")
                /***/
                        .bean(AccountRequestBuilder.class)
                        .setHeader("operationName", constant("findAccount"))
                .hystrix()
                    //.hystrixConfiguration()
                        //.executionTimeoutInMilliseconds(3000)
                        //.circuitBreakerRequestVolumeThreshold(1)
                        //.circuitBreakerSleepWindowInMilliseconds(10000)
                          //  .end()
                    .to("cxf://http://localhost:8080/servizi/AccountPort"
                        + "?serviceClass=org.apache.account.AccountServiceImpl")
                .onFallback()
                    .log("${routeId}> to fallback")
                    .process(new MyStrategy())
                    .transform().constant(defaultResponse)
                .end()
                .to("direct:prepare-response")

                .endRest();



        from("direct:prepare-response")
                .log("${routeId}> result ${body}")
                .process(new ResponseInspector())
                .transform(simple("${in.body[0].account}")).marshal().json(JsonLibrary.Gson)
               // .log("${routeId}> Hystrix passed ${body[0].account}")
                                /***/
               // .log("${routeId}>  The Response is: ${body[0]}")

                .log("${routeId}> Sending Response = ${body}") ;
                        /*
        from("direct:ws-invoker")
            .id("ws-invoker")
            .log("${routeId}> request = ${body}")
            .bean(AccountRequestBuilder.class)
            .setHeader("operationName", constant("findAccount"))
            .hystrix()
                .hystrixConfiguration()
                    .executionTimeoutInMilliseconds(10000)
                    .circuitBreakerRequestVolumeThreshold(1)
                    .circuitBreakerSleepWindowInMilliseconds(10000)
                        .end()
                        .to("cxf://http://localhost:8080/servizi/AccountPort"
                                + "?serviceClass=org.apache.account.AccountServiceImpl")
                    .onFallback()
                        .log("${routeId}> to fallback")
                        .transform().constant(defaultResponse)
                    .end()
                    .log("${routeId}> Hystrix passed ${body[0].account}");
          */
    // @formatter:on
    }

    private class ResponseInspector implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            System.out.println(exchange);
        }
    }
}
