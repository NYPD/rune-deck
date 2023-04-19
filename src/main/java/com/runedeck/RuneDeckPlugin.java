package com.runedeck;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.runedeck.payload.*;
import net.runelite.api.events.GameTick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provides;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(name = "Rune Deck")
public class RuneDeckPlugin extends Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuneDeckConfig.class);
    private final Gson GSON = new Gson();

    @Inject
    private Client client;
    @Inject
    private RuneDeckConfig config;

    private RuneDeckSocketServer runeDeckSocketServer;

    @Override
    protected void startUp() throws Exception {
        this.runeDeckSocketServer = new RuneDeckSocketServer(this.config.getSocketPort());
        this.runeDeckSocketServer.start();
    }

    @Override
    protected void shutDown() throws Exception {
        this.runeDeckSocketServer.stop();
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {
        if (gameStateChanged.getGameState() != GameState.LOGGED_IN && gameStateChanged.getGameState() != GameState.LOADING) {
            LogoutPayload logoutPayload = new LogoutPayload();
            String payloadJSON = this.GSON.toJson(logoutPayload);
            this.runeDeckSocketServer.broadcast(payloadJSON);
            PayloadCache.clearCache();
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
        // These are ordered in the most time sensitive to the least
        final MovementPayload movementPayload = new MovementPayload(this.client);
        if(!movementPayload.equals(PayloadCache.movementPayload)){
            PayloadCache.movementPayload = movementPayload;
            String movementPayloadJSON = this.GSON.toJson(movementPayload);
            this.runeDeckSocketServer.broadcast(movementPayloadJSON);
        }
        final OverheadPayload overheadPayload = new OverheadPayload(this.client);
        if(!overheadPayload.equals(PayloadCache.overheadPayload)){
            PayloadCache.overheadPayload = overheadPayload;
            String overheadPayloadJSON = this.GSON.toJson(overheadPayload);
            this.runeDeckSocketServer.broadcast(overheadPayloadJSON);
        }
        final SkillsPayload skillsPayload = new SkillsPayload(this.client);
        if(!skillsPayload.equals(PayloadCache.skillsPayload)){
            PayloadCache.skillsPayload = skillsPayload;
            String skillsPayloadJSON = this.GSON.toJson(skillsPayload);
            this.runeDeckSocketServer.broadcast(skillsPayloadJSON);
        }
        final PVPPayload pvpPayload = new PVPPayload(this.client);
        if(!pvpPayload.equals(PayloadCache.pvpPayload)){
            PayloadCache.pvpPayload = pvpPayload;
            String pvpPayloadJSON = this.GSON.toJson(pvpPayload);
            this.runeDeckSocketServer.broadcast(pvpPayloadJSON);
        }
        final EquipmentPayload equipmentPayload = new EquipmentPayload(this.client);
        if(!equipmentPayload.equals(PayloadCache.equipmentPayload)){
            PayloadCache.equipmentPayload = equipmentPayload;
            String equipmentPayloadJSON = this.GSON.toJson(equipmentPayload);
            this.runeDeckSocketServer.broadcast(equipmentPayloadJSON);
        }
        final FPSPayload fpsPayload = new FPSPayload(this.client);
        if(!fpsPayload.equals(PayloadCache.fpsPayload)){
            PayloadCache.fpsPayload = fpsPayload;
            String fpsPayloadJSON = this.GSON.toJson(fpsPayload);
            this.runeDeckSocketServer.broadcast(fpsPayloadJSON);
        }
        final ActivityPayload activityPayload = new ActivityPayload(this.client);
        if(!activityPayload.equals(PayloadCache.activityPayload)){
            PayloadCache.activityPayload = activityPayload;
            String activityPayloadJSON = this.GSON.toJson(activityPayload);
            this.runeDeckSocketServer.broadcast(activityPayloadJSON);
        }
        final GrandExchangePayload grandExchangePayload = new GrandExchangePayload(this.client);
        if(!grandExchangePayload.equals(PayloadCache.grandExchangePayload)){
            PayloadCache.grandExchangePayload = grandExchangePayload;
            String grandExchangePayloadJSON = this.GSON.toJson(grandExchangePayload);
            this.runeDeckSocketServer.broadcast(grandExchangePayloadJSON);
        }
    }

    @Provides
    RuneDeckConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneDeckConfig.class);
    }
}
