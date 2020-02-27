package org.apache.camel.example.service;

import org.apache.camel.example.model.Account;

import javax.jws.WebMethod;
import javax.jws.WebParam;

public interface AccountGreeter {

    public void pingMe(String ping);

    public String greetMe(Account account);

    public String sayHi();
}
