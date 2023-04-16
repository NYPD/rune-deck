package com.runedeck;

import net.runelite.api.Client;
import net.runelite.api.World;

public class WorldsPayload extends StreamDeckPayload {
    private final World[] worlds;
    public WorldsPayload(Client client){
        super("worlds");
        this.worlds = client.getWorldList();
    }

    public World[] getWorlds() {
        return worlds;
    }
}