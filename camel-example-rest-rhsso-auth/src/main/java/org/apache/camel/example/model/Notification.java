package org.apache.camel.example.model;

public class Notification {

    private String info = "";

    public Notification() {
    }

    public Notification(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
