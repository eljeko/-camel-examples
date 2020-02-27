
package org.apache.account;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.apache.account package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindAccount_QNAME = new QName("http://apache.org/account", "findAccount");
    private final static QName _FindAccountResponse_QNAME = new QName("http://apache.org/account", "findAccountResponse");
    private final static QName _Ping_QNAME = new QName("http://apache.org/account", "ping");
    private final static QName _PingResponse_QNAME = new QName("http://apache.org/account", "pingResponse");
    private final static QName _AccountException_QNAME = new QName("http://apache.org/account", "accountException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.apache.account
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindAccount }
     * 
     */
    public FindAccount createFindAccount() {
        return new FindAccount();
    }

    /**
     * Create an instance of {@link FindAccountResponse }
     * 
     */
    public FindAccountResponse createFindAccountResponse() {
        return new FindAccountResponse();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link AccountException }
     * 
     */
    public AccountException createAccountException() {
        return new AccountException();
    }

    /**
     * Create an instance of {@link AccountRequest }
     * 
     */
    public AccountRequest createAccountRequest() {
        return new AccountRequest();
    }

    /**
     * Create an instance of {@link AccountRespone }
     * 
     */
    public AccountRespone createAccountRespone() {
        return new AccountRespone();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAccount }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAccount }{@code >}
     */
    @XmlElementDecl(namespace = "http://apache.org/account", name = "findAccount")
    public JAXBElement<FindAccount> createFindAccount(FindAccount value) {
        return new JAXBElement<FindAccount>(_FindAccount_QNAME, FindAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAccountResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindAccountResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://apache.org/account", name = "findAccountResponse")
    public JAXBElement<FindAccountResponse> createFindAccountResponse(FindAccountResponse value) {
        return new JAXBElement<FindAccountResponse>(_FindAccountResponse_QNAME, FindAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}
     */
    @XmlElementDecl(namespace = "http://apache.org/account", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://apache.org/account", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AccountException }{@code >}
     */
    @XmlElementDecl(namespace = "http://apache.org/account", name = "accountException")
    public JAXBElement<AccountException> createAccountException(AccountException value) {
        return new JAXBElement<AccountException>(_AccountException_QNAME, AccountException.class, null, value);
    }

}
