package org.apache.camel.example.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthorizationProcessor implements Processor {
    
    @Override
    public void process(Exchange exchange) throws Exception {
        String authorization = (String) exchange.getIn().getHeader("Authorization");
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] values = credentials.split(":", 2);
            exchange.getIn().setHeader("currentuser", values[0]);
        }
    }
}
