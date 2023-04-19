package com.runedeck.payload;

abstract public class Payload {
    private final String type;

    protected Payload(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
