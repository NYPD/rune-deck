package com.runedeck;

import com.runedeck.payload.Payload;
import net.runelite.api.Client;
import net.runelite.api.World;

public class WorldsPayload extends Payload {
    private final World[] worlds;

    public WorldsPayload(Client client) {
        super("worlds");
        this.worlds = client.getWorldList();
    }

    public World[] getWorlds() {
        return worlds;
    }

    @Override
    public boolean isNewPayload(Client client) {
        return true;
    }
}
