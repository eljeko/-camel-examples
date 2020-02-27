package org.apache.camel.example.service;

public class SayHiImpl {
    public String sayHi() {
        return "Hi from smart route the time is [" + System.currentTimeMillis() + "]";
    }
}
