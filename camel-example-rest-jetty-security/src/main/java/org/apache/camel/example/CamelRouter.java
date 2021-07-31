package org.apache.camel.example;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.model.Account;
import org.apache.camel.example.model.AccountSummary;
import org.apache.camel.example.processors.AuthorizationProcessor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        // @formatter:off
        
        

        from("direct:getAccountSummary")
                .process( new AuthorizationProcessor())
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