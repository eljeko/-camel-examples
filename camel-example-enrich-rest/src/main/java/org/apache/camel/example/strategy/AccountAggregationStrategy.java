package org.apache.camel.example.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.example.model.Account;
import org.apache.camel.example.model.AccountSummary;

public class AccountAggregationStrategy extends BaseAccountInfoStrategy{

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        Account account = (Account) newExchange.getIn().getBody();

        AccountSummary accountSummary = getAccountSummary(oldExchange);
        
        accountSummary.setAccount(account);

        updateExchange(oldExchange, accountSummary);
        return oldExchange;
    }

}
