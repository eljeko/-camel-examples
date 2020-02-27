package com.redhat.integration.services;

import com.redhat.integration.model.ResponseType;

public class ResponseBean {

    public ResponseType mailHandling() {
        ResponseType responseType = new ResponseType();
        responseType.setStatus("OK");
        responseType.setMessage("We can handle the mail shipment");
        return responseType;
    }

    public ResponseType boxHandling() {
        ResponseType responseType = new ResponseType();
        responseType.setStatus("OK");
        responseType.setMessage("We can handle the box shipment");
        return responseType;
    }
}