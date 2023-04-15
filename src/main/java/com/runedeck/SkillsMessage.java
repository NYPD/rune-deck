package com.runedeck;

import net.runelite.api.Client;
import net.runelite.api.Skill;

public class SkillsMessage extends StreamDeckMessage {
    private final int currentHealth;
    private final int totalHealth;
    private final int currentAttack;
    private final int totalAttack;
    private final int currentStrength;
    private final int totalStrength;
    private final int currentDefence;
    private final int totalDefence;
    private final int currentRange;
    private final int totalRange;
    private final int currentPrayer;
    private final int totalPrayer;
    private final int currentMagic;
    private final int totalMagic;
    private final int currentRunecrafting;
    private final int totalRunecrafting;
    private final int currentConstruction;
    private final int totalConstruction;
    private final int currentAgility;
    private final int totalAgility;
    private final int currentHerblore;
    private final int totalHerblore;
    private final int currentThieving;
    private final int totalThieving;
    private final int currentCrafting;
    private final int totalCrafting;
    private final int currentFletching;
    private final int totalFletching;
    private final int currentSlayer;
    private final int totalSlayer;
    private final int currentHunter;
    private final int totalHunter;
    private final int currentMining;
    private final int totalMining;
    private final int currentSmithing;
    private final int totalSmithing;
    private final int currentFishing;
    private final int totalFishing;
    private final int currentCooking;
    private final int totalCooking;
    private final int currentFiremaking;
    private final int totalFiremaking;
    private final int currentWoodcutting;
    private final int totalWoodcutting;
    private final int currentFarming;
    private final int totalFarming;

    public SkillsMessage(Client client) {
		super("skills");
		this.currentHealth = client.getBoostedSkillLevel(Skill.HITPOINTS);
        this.totalHealth = client.getRealSkillLevel(Skill.HITPOINTS);
        this.currentAttack = client.getBoostedSkillLevel(Skill.ATTACK);
		this.totalAttack = client.getRealSkillLevel(Skill.ATTACK);
		this.currentStrength = client.getBoostedSkillLevel(Skill.STRENGTH);
		this.totalStrength = client.getRealSkillLevel(Skill.STRENGTH);
		this.currentDefence = client.getBoostedSkillLevel(Skill.DEFENCE);
		this.totalDefence = client.getRealSkillLevel(Skill.DEFENCE);
		this.currentRange = client.getBoostedSkillLevel(Skill.RANGED);
		this.totalRange = client.getRealSkillLevel(Skill.RANGED);
		this.currentPrayer = client.getBoostedSkillLevel(Skill.PRAYER);
		this.totalPrayer = client.getRealSkillLevel(Skill.PRAYER);
		this.currentMagic = client.getBoostedSkillLevel(Skill.MAGIC);
		this.totalMagic = client.getRealSkillLevel(Skill.MAGIC);
		this.currentRunecrafting = client.getBoostedSkillLevel(Skill.RUNECRAFT);
		this.totalRunecrafting = client.getRealSkillLevel(Skill.RUNECRAFT);
		this.currentConstruction = client.getBoostedSkillLevel(Skill.CONSTRUCTION);
		this.totalConstruction = client.getRealSkillLevel(Skill.CONSTRUCTION);
		this.currentAgility = client.getBoostedSkillLevel(Skill.AGILITY);
		this.totalAgility = client.getRealSkillLevel(Skill.AGILITY);
		this.currentHerblore = client.getBoostedSkillLevel(Skill.HERBLORE);
		this.totalHerblore = client.getRealSkillLevel(Skill.HERBLORE);
		this.currentThieving = client.getBoostedSkillLevel(Skill.THIEVING);
		this.totalThieving = client.getRealSkillLevel(Skill.THIEVING);
		this.currentCrafting = client.getBoostedSkillLevel(Skill.CRAFTING);
		this.totalCrafting = client.getRealSkillLevel(Skill.CRAFTING);
		this.currentFletching = client.getBoostedSkillLevel(Skill.FLETCHING);
		this.totalFletching = client.getRealSkillLevel(Skill.FLETCHING);
		this.currentSlayer = client.getBoostedSkillLevel(Skill.SLAYER);
		this.totalSlayer = client.getRealSkillLevel(Skill.SLAYER);
		this.currentHunter = client.getBoostedSkillLevel(Skill.HUNTER);
		this.totalHunter = client.getRealSkillLevel(Skill.HUNTER);
		this.currentMining = client.getBoostedSkillLevel(Skill.MINING);
		this.totalMining = client.getRealSkillLevel(Skill.MINING);
		this.currentSmithing = client.getBoostedSkillLevel(Skill.SMITHING);
		this.totalSmithing = client.getRealSkillLevel(Skill.SMITHING);
		this.currentFishing = client.getBoostedSkillLevel(Skill.FISHING);
		this.totalFishing = client.getRealSkillLevel(Skill.FISHING);
		this.currentCooking = client.getBoostedSkillLevel(Skill.COOKING);
		this.totalCooking = client.getRealSkillLevel(Skill.COOKING);
		this.currentFiremaking = client.getBoostedSkillLevel(Skill.FIREMAKING);
		this.totalFiremaking = client.getRealSkillLevel(Skill.FIREMAKING);
		this.currentWoodcutting = client.getBoostedSkillLevel(Skill.WOODCUTTING);
		this.totalWoodcutting = client.getRealSkillLevel(Skill.WOODCUTTING);
		this.currentFarming = client.getBoostedSkillLevel(Skill.FARMING);
		this.totalFarming = client.getRealSkillLevel(Skill.FARMING);
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getTotalHealth() {
        return this.totalHealth;
    }

	public int getCurrentAttack() {
		return this.currentAttack;
	}

	public int getTotalAttack() {
		return this.totalAttack;
	}

	public int getCurrentStrength() {
		return this.currentStrength;
	}

	public int getTotalStrength() {
		return this.totalStrength;
	}

	public int getCurrentDefence() {
		return this.currentDefence;
	}

	public int getTotalDefence() {
		return this.totalDefence;
	}

	public int getCurrentRange() {
		return this.currentRange;
	}

	public int getTotalRange() {
		return this.totalRange;
	}

	public int getCurrentPrayer() {
		return this.currentPrayer;
	}

	public int getTotalPrayer() {
		return this.totalPrayer;
	}

	public int getCurrentMagic() {
		return this.currentMagic;
	}

	public int getTotalMagic() {
		return this.totalMagic;
	}

	public int getCurrentRunecrafting() {
		return this.currentRunecrafting;
	}

	public int getTotalRunecrafting() {
		return this.totalRunecrafting;
	}

	public int getCurrentConstruction() {
		return this.currentConstruction;
	}

	public int getTotalConstruction() {
		return this.totalConstruction;
	}

	public int getCurrentAgility() {
		return this.currentAgility;
	}

	public int getTotalAgility() {
		return this.totalAgility;
	}

	public int getCurrentHerblore() {
		return this.currentHerblore;
	}

	public int getTotalHerblore() {
		return this.totalHerblore;
	}

	public int getCurrentThieving() {
		return this.currentThieving;
	}

	public int getTotalThieving() {
		return this.totalThieving;
	}

	public int getCurrentCrafting() {
		return this.currentCrafting;
	}

	public int getTotalCrafting() {
		return this.totalCrafting;
	}

	public int getCurrentFletching() {
		return this.currentFletching;
	}

	public int getTotalFletching() {
		return this.totalFletching;
	}

	public int getCurrentSlayer() {
		return this.currentSlayer;
	}

	public int getTotalSlayer() {
		return this.totalSlayer;
	}

	public int getCurrentHunter() {
		return this.currentHunter;
	}

	public int getTotalHunter() {
		return this.totalHunter;
	}

	public int getCurrentMining() {
		return this.currentMining;
	}

	public int getTotalMining() {
		return this.totalMining;
	}

	public int getCurrentSmithing() {
		return this.currentSmithing;
	}

	public int getTotalSmithing() {
		return this.totalSmithing;
	}

	public int getCurrentFishing() {
		return this.currentFishing;
	}

	public int getTotalFishing() {
		return this.totalFishing;
	}

	public int getCurrentCooking() {
		return this.currentCooking;
	}

	public int getTotalCooking() {
		return this.totalCooking;
	}

	public int getCurrentFiremaking() {
		return this.currentFiremaking;
	}

	public int getTotalFiremaking() {
		return this.totalFiremaking;
	}

	public int getCurrentWoodcutting() {
		return this.currentWoodcutting;
	}

	public int getTotalWoodcutting() {
		return this.totalWoodcutting;
	}

	public int getCurrentFarming() {
		return this.currentFarming;
	}

	public int getTotalFarming() {
		return this.totalFarming;
	}

    @Override
    public String toString() {
        return "StreamDeckResponse [currentHealth=" + this.currentHealth + ", totalHealth=" + this.totalHealth + "]";
    }
}
