package org.apache.camel.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.model.AccountSummary;
import org.apache.camel.example.model.Notification;
import org.apache.camel.example.model.Transaction;
import org.apache.camel.example.strategy.AccountAggregationStrategy;
import org.apache.camel.example.strategy.NotificationAggregationStrategy;
import org.apache.camel.example.strategy.TransactionAggregationStrategy;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {


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
                .component("servlet")
                .bindingMode(RestBindingMode.json);


        rest("/accountsummary")
                .get("/{id}").outType(AccountSummary.class)
                .to("direct:enrichAccountSummary");


        from("direct:enrichAccountSummary")
                .enrich("bean:accountService?method=getAccount", new AccountAggregationStrategy())
                .enrich("bean:notificationService?method=getNotifications", new NotificationAggregationStrategy())
                .enrich("bean:transactionService?method=getTransactions", new TransactionAggregationStrategy());
        ;
        //.enrich("bean:accountTransaction?method=getTransaction", new TransactionAggregationStrategy())


        // @formatter:on
    }

}