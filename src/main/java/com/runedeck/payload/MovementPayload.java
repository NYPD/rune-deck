package com.runedeck.payload;

import com.runedeck.PayloadCache;
import net.runelite.api.Client;

import java.util.Objects;

public class MovementPayload extends Payload {
    private final int energy;
    private final int coordinateX;
    private final int coordinateY;

    public MovementPayload(Client client) {
        super(PayloadTypes.MOVEMENT);
        this.energy = client.getEnergy();
        this.coordinateX = client.getLocalPlayer().getWorldLocation().getX();
        this.coordinateY = client.getLocalPlayer().getWorldLocation().getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementPayload that = (MovementPayload) o;
        return energy == that.energy && coordinateX == that.coordinateX && coordinateY == that.coordinateY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(energy, coordinateX, coordinateY);
    }

    public int getEnergy() {
        return energy;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }


    @Override
    public boolean isNewPayload(Client client) {
        return this.getCoordinateX() != client.getLocalPlayer().getWorldLocation().getX()
                || this.getCoordinateY() != client.getLocalPlayer().getWorldLocation().getY()
                || this.getEnergy() != client.getEnergy();

    }
}
