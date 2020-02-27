package org.apache.camel.example.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.example.model.Account;
import org.apache.camel.example.model.AccountSummary;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public abstract class BaseAccountInfoStrategy implements AggregationStrategy {

    protected AccountSummary getAccountSummary(Exchange oldExchange) {
        AccountSummary accountSummary;

        if (oldExchange == null || oldExchange.getIn().getBody().equals("")) {
            accountSummary = new AccountSummary();
        } else {
            accountSummary = (AccountSummary) oldExchange.getIn().getBody();
        }
        return accountSummary;
    }

    protected Exchange updateExchange(Exchange oldExchange, AccountSummary accountSummary) {
        if (oldExchange.getPattern().isOutCapable()) {
            oldExchange.getOut().setBody(accountSummary);
        } else {
            oldExchange.getIn().setBody(accountSummary);
        }
        return oldExchange;
    }
}
