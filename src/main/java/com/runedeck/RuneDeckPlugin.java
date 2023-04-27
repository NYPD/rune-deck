package com.runedeck;

import com.google.gson.Gson;
import com.google.inject.Provides;
import com.runedeck.payload.*;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

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
        if (PayloadCache.movementPayload == null || PayloadCache.movementPayload.isNewPayload(this.client)) {
            PayloadCache.movementPayload = new MovementPayload(this.client);
            String movementPayloadJSON = this.GSON.toJson(PayloadCache.movementPayload);
            this.runeDeckSocketServer.broadcast(movementPayloadJSON);
        }

        if (PayloadCache.overheadPayload == null || PayloadCache.overheadPayload.isNewPayload(this.client)) {
            PayloadCache.overheadPayload = new OverheadPayload(this.client);
            String overheadPayloadJSON = this.GSON.toJson(PayloadCache.overheadPayload);
            this.runeDeckSocketServer.broadcast(overheadPayloadJSON);
        }

        if (PayloadCache.skillsPayload == null || PayloadCache.skillsPayload.isNewPayload(this.client)) {
            PayloadCache.skillsPayload = new SkillsPayload(this.client);
            String skillsPayloadJSON = this.GSON.toJson(PayloadCache.skillsPayload);
            this.runeDeckSocketServer.broadcast(skillsPayloadJSON);
        }

        if (PayloadCache.pvpPayload == null || PayloadCache.pvpPayload.isNewPayload(this.client)) {
            PayloadCache.pvpPayload = new PVPPayload(this.client);
            String pvpPayloadJSON = this.GSON.toJson(PayloadCache.pvpPayload);
            this.runeDeckSocketServer.broadcast(pvpPayloadJSON);
        }

        if (PayloadCache.equipmentPayload == null || PayloadCache.equipmentPayload.isNewPayload(this.client)) {
            PayloadCache.equipmentPayload = new EquipmentPayload(this.client);
            String equipmentPayloadJSON = this.GSON.toJson(PayloadCache.equipmentPayload);
            this.runeDeckSocketServer.broadcast(equipmentPayloadJSON);
        }

        if (PayloadCache.fpsPayload == null || PayloadCache.fpsPayload.isNewPayload(this.client)) {
            PayloadCache.fpsPayload = new FPSPayload(this.client);
            String fpsPayloadJSON = this.GSON.toJson(PayloadCache.fpsPayload);
            this.runeDeckSocketServer.broadcast(fpsPayloadJSON);
        }

        if (PayloadCache.grandExchangePayload == null || PayloadCache.grandExchangePayload.isNewPayload(this.client)) {
            PayloadCache.grandExchangePayload = new GrandExchangePayload(this.client);
            String grandExchangePayloadJSON = this.GSON.toJson(PayloadCache.grandExchangePayload);
            this.runeDeckSocketServer.broadcast(grandExchangePayloadJSON);
        }

        if (PayloadCache.activityPayload == null || PayloadCache.activityPayload.isNewPayload(this.client)) {
            PayloadCache.activityPayload = new ActivityPayload(this.client);
            String activityPayloadJSON = this.GSON.toJson(PayloadCache.activityPayload);
            this.runeDeckSocketServer.broadcast(activityPayloadJSON);
        }

    }

    @Provides
    RuneDeckConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneDeckConfig.class);
    }
}
