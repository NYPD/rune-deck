package com.runedeck.payload;

import com.runedeck.AnimationCollections;
import net.runelite.api.Client;
import net.runelite.api.Player;

public class ActivityPayload extends Payload {
    static int inactiveTicks = 0;
    private final boolean isActive;


    public ActivityPayload(Client client) {
        super(PayloadTypes.ACTIVITY);
        this.isActive = ActivityPayload.inactiveTicks < 3 || client.getLocalPlayer().isInteracting();
    }

    public ActivityPayload(boolean isInteracting) {
        super(PayloadTypes.ACTIVITY);
        this.isActive = isInteracting;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean isNewPayload(Client client) {
        Player player = client.getLocalPlayer();
        int animation = player.getAnimation();

        // Ignore fishing animations because they are handled by isInteracting()
        if (animation == -1 || AnimationCollections.isFishingAnimation(animation)) {
            ActivityPayload.inactiveTicks++;
        } else {
            ActivityPayload.inactiveTicks = 0;
        }

        return this.isActive != (ActivityPayload.inactiveTicks < 3 || client.getLocalPlayer().isInteracting());
    }
}

