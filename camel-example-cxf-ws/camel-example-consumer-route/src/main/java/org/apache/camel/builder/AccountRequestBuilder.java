package org.apache.camel.builder;

import org.apache.account.AccountRequest;

public class AccountRequestBuilder {

    public AccountRequest getAccount(long id) {
        AccountRequest request = new AccountRequest();
        request.setId(id);
        return request;
    }
}