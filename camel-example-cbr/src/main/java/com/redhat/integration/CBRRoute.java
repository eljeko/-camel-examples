package com.redhat.integration;

import com.redhat.integration.model.ResponseType;
import com.redhat.integration.model.DeliveryType;
import com.redhat.integration.services.ResponseBean;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CBRRoute extends RouteBuilder {

    private static final String DATE_PATTERN = "dd/MM/yyyy @ HH:mm:ss";

    @Value("${applicationapi.host}")
    private String host;

    @Override
    public void configure() {


        // @formatter:off
            onException(Exception.class)
                .setBody(simple("{result:Error}"))
                    .log("--->  ${body}")
                    .end();
            restConfiguration()
                                //Enable swagger endpoint.
                .contextPath("/cbr").apiContextPath("/api-doc")
                    .apiProperty("api.title", "Fuse REST API")
                    .apiProperty("api.version", "1.0")
                    .enableCORS(true)
                    .apiProperty("cors", "true")
                    .apiProperty("api.specification.contentType.json", "application/vnd.oai.openapi+json;version=2.0")
                    .apiProperty("api.specification.contentType.yaml", "application/vnd.oai.openapi;version=2.0")
                    .apiProperty("host", host) //by default 0.0.0.0
                    .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json);


            rest("/ingestion").description("Product REST service")
                    .consumes("application/json")
                .post("/").description("Invokes a batch processing")
                    .type(DeliveryType.class)
                    .outType(ResponseType.class)
                    .route()
                    .choice()
                       .when(
                            simple("${body.type} == 'box'"))
                            .log("${routeId}> type ${body.type}")
                            .marshal().json()
                            .to("file:{{destination.path}}export?fileName=${date:now:yyyyMMdd-HH_mm_ss}-box-order.txt")
                            .to("bean:responseBean?method=boxHandling")
                        .when(
                            simple("${body.type} == 'mail'"))
                            .log("${routeId}> type ${body.type}")
                            .marshal().json(JsonLibrary.Jackson)
                            .to("file:{{destination.path}}export?fileName=${date:now:yyyyMMdd-HH_mm_ss}-mail-order.txt")
                            .to("bean:responseBean?method=mailHandling")
                        .otherwise()
                            .log("Unknown type")
                            .marshal().json(JsonLibrary.Jackson)
                            .to("file:{{destination.path}}export?fileName=${date:now:yyyyMMdd-HH_mm_ss}-error.txt")
                            .setFaultBody(constant("Unknown type"))
                   .endChoice()
                    .end()
                   
               .log("${routeId}> response = ${body}")
                .end()
            .endRest();

            // @formatter:on
    }

    @Bean(name = "responseBean")
    public ResponseBean getResponseBean() {
        return new ResponseBean();
    }
}