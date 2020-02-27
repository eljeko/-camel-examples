
package org.apache.account;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.3.2
 * 2019-05-15T14:09:53.948+02:00
 * Generated source version: 3.3.2
 */

@WebFault(name = "accountException", targetNamespace = "http://apache.org/account")
public class AccountException_Exception extends Exception {

    private org.apache.account.AccountException accountException;

    public AccountException_Exception() {
        super();
    }

    public AccountException_Exception(String message) {
        super(message);
    }

    public AccountException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public AccountException_Exception(String message, org.apache.account.AccountException accountException) {
        super(message);
        this.accountException = accountException;
    }

    public AccountException_Exception(String message, org.apache.account.AccountException accountException, java.lang.Throwable cause) {
        super(message, cause);
        this.accountException = accountException;
    }

    public org.apache.account.AccountException getFaultInfo() {
        return this.accountException;
    }
}
