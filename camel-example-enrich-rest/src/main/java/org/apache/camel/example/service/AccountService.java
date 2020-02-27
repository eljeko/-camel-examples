package org.apache.camel.example.service;

import org.apache.camel.example.model.Account;
import org.springframework.stereotype.Service;


@Service("accountService")
public class AccountService {
    
    public Account getAccount(String id) {
        return new Account("John", "Brown");
    }
}
