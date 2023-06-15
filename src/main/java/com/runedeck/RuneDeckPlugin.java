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
    private PayloadCache payloadCache= PayloadCache.getInstance();

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
    	
    	GameState gameState = gameStateChanged.getGameState();
    	
    	boolean userLoggedIn = gameState == GameState.LOGGED_IN;
    	boolean gameIsLoading = gameState == GameState.LOADING;
    	
    	if(userLoggedIn || gameIsLoading) return;
    	
        this.runeDeckSocketServer.broadcast(new LogoutPayload());
        payloadCache.clearCache();
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
    	
        if (payloadCache.movementPayload.isNewPayload(this.client)) {
        	payloadCache.movementPayload = new MovementPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.skillsPayload);
        }

        if (payloadCache.overheadPayload.isNewPayload(this.client)) {
            payloadCache.overheadPayload = new OverheadPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.skillsPayload);
        }

        if (payloadCache.skillsPayload.isNewPayload(this.client)) {
            payloadCache.skillsPayload = new SkillsPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.skillsPayload);
        }

        if (payloadCache.pvpPayload.isNewPayload(this.client)) {
            payloadCache.pvpPayload = new PVPPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.pvpPayload);
        }

        if (payloadCache.equipmentPayload.isNewPayload(this.client)) {
            payloadCache.equipmentPayload = new EquipmentPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.equipmentPayload);
        }

        if (payloadCache.fpsPayload.isNewPayload(this.client)) {
            payloadCache.fpsPayload = new FPSPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.fpsPayload);
        }

        if (payloadCache.grandExchangePayload.isNewPayload(this.client)) {
            payloadCache.grandExchangePayload = new GrandExchangePayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.grandExchangePayload);
        }

        if (payloadCache.activityPayload.isNewPayload(this.client)) {
            payloadCache.activityPayload = new ActivityPayload(this.client);
            this.runeDeckSocketServer.broadcast(payloadCache.activityPayload);
        }

    }
    

    @Provides
    RuneDeckConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneDeckConfig.class);
    }
}
