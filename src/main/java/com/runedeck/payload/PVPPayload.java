package com.runedeck.payload;

import net.runelite.api.Client;
import net.runelite.api.SkullIcon;

import java.util.Objects;

public class PVPPayload extends Payload {

    private String skullIcon;
    
    public PVPPayload() {
        super(PayloadType.PVP);
    }

    public PVPPayload(Client client) {
        super(PayloadType.PVP);
        SkullIcon skullIcon = client.getLocalPlayer().getSkullIcon();
        this.skullIcon = skullIcon != null ? skullIcon.name() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PVPPayload that = (PVPPayload) o;
        return Objects.equals(skullIcon, that.skullIcon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skullIcon);
    }

    public String getSkullIcon() {
        return skullIcon;
    }

    @Override
    public boolean isNewPayload(Client client) {
        SkullIcon skullIcon = client.getLocalPlayer().getSkullIcon();
        return skullIcon == null ? this.getSkullIcon() != null : !skullIcon.name().equals(this.getSkullIcon());
    }
}
