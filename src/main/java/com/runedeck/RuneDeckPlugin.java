package com.runedeck;

import javax.inject.Inject;

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

    @Inject
    private Client client;
    @Inject
    private RuneDeckConfig config;

    private RuneDeckSocketServer runeDeckSocketServer;
    private WebSocketThread webSocketThread;

    @Override
    protected void startUp() throws Exception {
        this.runeDeckSocketServer = new RuneDeckSocketServer(this.config.getSocketPort());
        this.runeDeckSocketServer.start();
    }

    @Override
    protected void shutDown() throws Exception {
        this.webSocketThread.stopSocketThread();
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {

        if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
            this.webSocketThread = new WebSocketThread(this.runeDeckSocketServer, this.client);
        }

    }

    @Provides
    RuneDeckConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneDeckConfig.class);
    }

}
