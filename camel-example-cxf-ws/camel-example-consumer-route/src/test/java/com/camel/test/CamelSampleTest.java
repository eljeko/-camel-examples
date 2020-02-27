package com.camel.test;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CamelSampleTest extends CamelTestSupport {

    @Test
    public void testRoute() throws Exception {
        Object out = template.requestBody("direct:sampleTest", "10");
        assertEquals("LAST CHANGE", out);
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new TestRoute();
    }

    private class TestRoute extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("direct:sampleTest")
                    .transform(simple("LAST CHANGE"))
                    .log("Last call");
        }
    }

}