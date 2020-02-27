package org.apache.camel.wsaccount.service;

import org.apache.camel.wsaccount.model.Account;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://apache.org/account",
        portName = "AccountPort",
        serviceName = "AccountService"
)
public class AccountServiceImpl implements AccountService {

    @WebMethod
    public void ping(@WebParam(name = "ping") String ping) {
        System.out.println("Received [" + ping + "] current time " + System.currentTimeMillis());
    }

    @WebMethod
    @WebResult(name = "accountResponse", partName = "response")
    public AccountRespone findAccount(
            @WebParam(partName = "request", name = "accountRequest")
                    AccountRequest request) throws AccountException {

        if (request.getId() == 10) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (request.getId() == 20) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (request.getId() == 1000) {
            throw new AccountException("Error reading account");
        }


        Account account = new Account(request.getId(), "Jhon-" + request.getId(), "Brown");
        System.out.println("Sending back " + account.getName() + ", " + account.getSurname());
        return new AccountRespone(account);
    }


}
