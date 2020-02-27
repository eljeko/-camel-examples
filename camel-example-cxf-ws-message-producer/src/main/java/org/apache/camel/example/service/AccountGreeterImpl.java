package org.apache.camel.example.service;

import org.apache.camel.*;
import org.apache.camel.example.model.Account;
import org.apache.camel.example.util.MessageProducer;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://apache.org/hello_world_soap_http",
        portName = "AccountGreeterPort",
        serviceName = "AccountGreeter"
)
public class AccountGreeterImpl implements AccountGreeter {

    private MessageProducer producer;

    @WebMethod
    public void pingMe(@WebParam(name = "ping") String ping) {
        producer.sendMessage("direct:greeterlog", ping).toString();
    }

    @WebMethod
    public String greetMe(@WebParam(name = "greet") Account account) {
        return producer.sendMessage("direct:greeter", account).toString();
    }

    @WebMethod
    public String sayHi() {
        return "Hi, the time is [" + System.currentTimeMillis() + "]";
    }

    public MessageProducer getProducer() {
        return producer;
    }

    public void setProducer(MessageProducer producer) {
        this.producer = producer;
    }
}
