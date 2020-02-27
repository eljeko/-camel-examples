package com.redhat.fuse;

import com.redhat.fuse.model.Payment;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;


@Component
public class Route extends RouteBuilder {

    private static final String DATE_PATTERN = "dd/MM/yyyy @ HH:mm:ss";

    @Override
    public void configure() {

        DataFormat bindy = new BindyCsvDataFormat(Payment.class);
        
        // @formatter:off
        from("file://{{source.path}}?doneFileName=${file:name}.done&moveFailed=${file:name}.error")
            .unmarshal(bindy)
            .split(body()).streaming()
                .log(">> Current body: ${in.body} guid ${in.body.orderId} cc type: ${in.body.creditCardType}")
                .setHeader("cctype", simple("${in.body.creditCardType}"))
                .marshal(bindy)
                .to("file:{{destination.path}}export/?fileName=${header.cctype}-${date:now:yyyyMMdd}-transactions.txt&fileExist=Append")
        //autoCreate
        .end();
        // @formatter:on
    }

}