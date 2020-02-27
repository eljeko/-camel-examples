package org.apache.camel.example.util;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

public class MessageProducer {

    @Produce
    private ProducerTemplate template;

    public Object sendMessage(String destination, Object body) {
        return template.requestBody(destination, body);
    }
}
