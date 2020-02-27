/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.camel.test;

import org.apache.account.Account;
import org.apache.account.AccountRespone;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AccountRequestBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Hystrix using timeout and fallback with Java DSL
 */
public class HystrixTimeoutWithFallbackRestTest extends CamelTestSupport {




    @Test
    public void testTimeOut() throws Exception {
        // this calls the slow route and therefore causes a timeout which triggers the fallback


        Object out = template.requestBody("direct:start", "20");
        assertEquals("LAST CHANGE", out);
    }

    @Test
      public void testTimeOutManyRequests() throws Exception {
          // this calls the slow route and therefore causes a timeout which triggers the fallback
        
          Object out1 = template.requestBody("direct:start", "20");
          Object out2 = template.requestBody("direct:start", "20");
          Object out3 = template.requestBody("direct:start", "20");
          assertEquals("LAST CHANGE", out3);
      }

    @Test
    public void testFast() throws Exception {
        // this calls the slow route and therefore causes a timeout which triggers the fallback


        Object out = template.requestBody("direct:start", "1");
        assertEquals("LAST CHANGE", out);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                List<AccountRespone> defaultResponse = new ArrayList<AccountRespone>();
                AccountRespone accountRespone = new AccountRespone();
                accountRespone.setAccount(new Account());
                defaultResponse.add(accountRespone);





                // @formatter:off
                from("direct:start")
                .bean(AccountRequestBuilder.class)
                    .setHeader("operationName", constant("findAccount"))
                    .hystrix()
                        .hystrixConfiguration()
                        .executionTimeoutInMilliseconds(2000)
                        .circuitBreakerRequestVolumeThreshold(1)
                        .circuitBreakerSleepWindowInMilliseconds(10000)
                        .end()
                            .to("cxf://http://localhost:8080/servizi/AccountPort"
                              + "?serviceClass=org.apache.account.AccountServiceImpl")
                        .onFallback()
                            .log("${routeId}> to fallback")
                            .transform().constant(defaultResponse)
                        .end()
                        .transform(simple("${body[0].account}")).marshal().json(JsonLibrary.Gson)
                        .transform(simple("LAST CHANGE"))
                        .log("Last call");
             
            }
        };
    }

}