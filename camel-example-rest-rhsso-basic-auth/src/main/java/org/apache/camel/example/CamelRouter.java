package org.apache.camel.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.model.Account;
import org.apache.camel.example.model.AccountSummary;
import org.apache.camel.example.processors.AuthorizationProcessor;
import org.apache.camel.model.dataformat.JsonLibrary;
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
                .bindingMode(RestBindingMode.off);

        from("direct:getAccountSummary")
                .process(new AuthorizationProcessor())
                .log("Current user is: ${in.headers.currentuser}")
                .process(exchange-> {
                    AccountSummary accountSummary = new AccountSummary();
                    Account account = new Account();
                    account.setName("Jhon");
                    account.setSurname("Green");
                    accountSummary.setAccount(account);
                    exchange.getIn().setBody(accountSummary);
                })
                .marshal().json(JsonLibrary.Gson);
        // @formatter:on
    }

}