package com.runedeck;

import net.runelite.api.Client;
import net.runelite.api.Skill;

public class StreamDeckResponse {

    private int currentHealth;
    private int totalHealth;

    public StreamDeckResponse(Client client) {
        this.currentHealth = client.getBoostedSkillLevel(Skill.HITPOINTS);
        this.totalHealth = client.getRealSkillLevel(Skill.HITPOINTS);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getTotalHealth() {
        return this.totalHealth;
    }

    @Override
    public String toString() {
        return "StreamDeckResponse [currentHealth=" + this.currentHealth + ", totalHealth=" + this.totalHealth + "]";
    }
}
