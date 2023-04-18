package com.runedeck;

import javax.inject.Inject;

import com.google.gson.Gson;
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
        } else {
            this.runeDeckSocketServer.setLastSentTickMessage("");
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick){
            TickPayload tickPayload = new TickPayload(this.client);
            String tickPayloadJSON = this.GSON.toJson(tickPayload);
            this.runeDeckSocketServer.sendMessage(tickPayloadJSON);
     }

    @Provides
    RuneDeckConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneDeckConfig.class);
    }
}
