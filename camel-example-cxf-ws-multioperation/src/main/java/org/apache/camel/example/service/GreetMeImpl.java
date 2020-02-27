package org.apache.camel.example.service;

import org.apache.camel.example.model.Account;

public class GreetMeImpl {

    public String greetMe(Account account) {
        return "Hello from smart route" + account.getName() + " " + account.getSurname();
    }
}
