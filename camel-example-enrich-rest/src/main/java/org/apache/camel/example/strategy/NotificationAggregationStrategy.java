package org.apache.camel.example.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.example.model.AccountSummary;
import org.apache.camel.example.model.Notification;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.List;

public class NotificationAggregationStrategy extends BaseAccountInfoStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<Notification> notifications = (List<Notification>) newExchange.getIn().getBody();

        AccountSummary accountSummary = getAccountSummary(oldExchange);

        accountSummary.setNotifications(notifications);

        return updateExchange(oldExchange, accountSummary);

    }

}
