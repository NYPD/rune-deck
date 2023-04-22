package com.runedeck.payload;

import net.runelite.api.Client;

public class LogoutPayload extends Payload {
    public LogoutPayload() {
        super(PayloadTypes.LOGOUT);
    }

    @Override
    public boolean isNewPayload(Client client) {
        return true;
    }
}
