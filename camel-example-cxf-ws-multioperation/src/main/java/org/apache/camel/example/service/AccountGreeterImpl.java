package org.apache.camel.example.service;

import org.apache.camel.example.model.Account;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://apache.org/hello_world_soap_http",
        portName = "AccountGreeterPort",
        serviceName = "AccountGreeter"
)
public class AccountGreeterImpl implements AccountGreeter {

    @WebMethod
    public void pingMe(@WebParam(name = "ping") String ping) {
        System.out.println("Current time " + System.currentTimeMillis());
    }
    
    @WebMethod
    public String greetMe(@WebParam(name = "account") Account account) {
        return "Hello " + account.getName() + " " + account.getSurname();
    }

    @WebMethod
    public String sayHi() {
        return "Hi, the time is [" + System.currentTimeMillis() + "]";
    }
}
