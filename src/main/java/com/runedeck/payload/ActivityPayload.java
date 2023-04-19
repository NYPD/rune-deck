package com.runedeck.payload;

import net.runelite.api.Client;

import java.util.Objects;

public class ActivityPayload extends Payload {
    private final boolean isInteracting;

    public ActivityPayload(Client client) {
        super(PayloadTypes.ACTIVITY);
        this.isInteracting = client.getLocalPlayer().isInteracting();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityPayload that = (ActivityPayload) o;
        return isInteracting == that.isInteracting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isInteracting);
    }

    public boolean isInteracting() {
        return isInteracting;
    }
}

