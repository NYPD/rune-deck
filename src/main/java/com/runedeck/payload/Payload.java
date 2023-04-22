package com.runedeck.payload;

import net.runelite.api.Client;

abstract public class Payload {
    private final String type;

    protected Payload(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract boolean isNewPayload(Client client);
}
