# Hystrix example - Client

## Client overview

This is a route that consumes the WebService Account from (camel-example-webservice-account)


## Generate the client classes

Be sure the service is up and running on:

[http://localhost:8080/servizi/AccountPort?WSDL](http://localhost:8080/servizi/AccountPort?WSDL)

You should see the wsdl

Now you need to generate the client classes, on the root run:

> mvn clean package -Pgenerate-client

## Request builder

You need a bean to invoke the service

```
public class AccountRequestBuilder {

    public AccountRequest getAccount(long id) {
        AccountRequest request = new AccountRequest();
        request.setId(id);
        return request;
    }
}
```

## Json libraries

Add maven dependencies for Json

<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-gson</artifactId>
</dependency>


## RestBindingMode

We are not using RestBindingMode in the route dsl:

> .bindingMode(RestBindingMode.json);

This because we are customiezed the  marshalling:

>  .transform(simple("${body[0].account}")).marshal().json(JsonLibrary.Gson);