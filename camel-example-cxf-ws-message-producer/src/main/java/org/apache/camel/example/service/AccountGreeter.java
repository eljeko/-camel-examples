package org.apache.camel.example.service;

import org.apache.camel.example.model.Account;

public interface AccountGreeter {

    public void pingMe(String ping);


    public String greetMe(Account account);


    public String sayHi();
}
