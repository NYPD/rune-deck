package com.runedeck;

abstract public class StreamDeckPayload {
    private final String type;

    protected StreamDeckPayload(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
