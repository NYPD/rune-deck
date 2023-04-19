package com.runedeck.payload;

import net.runelite.api.Client;
import net.runelite.api.PlayerComposition;

import java.util.Arrays;

public class EquipmentPayload extends Payload
{
    private final int[] playerEquipmentIds;

    public EquipmentPayload(Client client)
    {
        super(PayloadTypes.EQUIPMENT);

        PlayerComposition composition = client.getLocalPlayer().getPlayerComposition();
        this.playerEquipmentIds = composition != null ? composition.getEquipmentIds() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentPayload that = (EquipmentPayload) o;
        return Arrays.equals(playerEquipmentIds, that.playerEquipmentIds);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(playerEquipmentIds);
    }

    public int[] getPlayerEquipmentIds()
    {
        return playerEquipmentIds;
    }

}