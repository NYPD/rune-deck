package com.runedeck;

abstract public class StreamDeckMessage {
    private final String messageType;

    protected StreamDeckMessage(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
