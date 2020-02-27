
package org.apache.account;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findAccountResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findAccountResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountResponse" type="{http://apache.org/account}AccountRespone" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAccountResponse", propOrder = {
    "accountResponse"
})
public class FindAccountResponse {

    protected AccountRespone accountResponse;

    /**
     * Gets the value of the accountResponse property.
     * 
     * @return
     *     possible object is
     *     {@link AccountRespone }
     *     
     */
    public AccountRespone getAccountResponse() {
        return accountResponse;
    }

    /**
     * Sets the value of the accountResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountRespone }
     *     
     */
    public void setAccountResponse(AccountRespone value) {
        this.accountResponse = value;
    }

}
