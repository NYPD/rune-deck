package com.runedeck.payload;

import net.runelite.api.Client;

import java.util.Objects;

public class FPSPayload extends Payload{
    private final int fps;

    public FPSPayload(Client client){
        super(PayloadTypes.FPS);
        this.fps = client.getFPS();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FPSPayload that = (FPSPayload) o;
        return fps == that.fps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fps);
    }

    public int getFps() {
        return fps;
    }
}
