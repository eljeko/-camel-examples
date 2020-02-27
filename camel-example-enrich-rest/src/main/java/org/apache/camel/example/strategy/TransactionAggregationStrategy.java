package org.apache.camel.example.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.example.model.AccountSummary;
import org.apache.camel.example.model.Notification;
import org.apache.camel.example.model.Transaction;

import java.util.List;

public class TransactionAggregationStrategy extends BaseAccountInfoStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<Transaction> transactions = (List<Transaction>) newExchange.getIn().getBody();

        AccountSummary accountSummary = getAccountSummary(oldExchange);

        accountSummary.setTransactions(transactions);

        return updateExchange(oldExchange, accountSummary);

    }

}
