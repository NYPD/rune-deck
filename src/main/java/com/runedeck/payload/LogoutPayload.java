package com.runedeck.payload;

import com.runedeck.payload.Payload;

public class LogoutPayload extends Payload {
    public LogoutPayload() {
        super(PayloadTypes.LOGOUT);
    }
}
