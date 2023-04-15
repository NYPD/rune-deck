package com.runedeck;

import java.util.concurrent.atomic.AtomicBoolean;

import net.runelite.api.GameState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import net.runelite.api.Client;

public class WebSocketThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketThread.class);

    private final Gson GSON = new Gson();
    private AtomicBoolean continueRunning = new AtomicBoolean(true);
    private RuneDeckSocketServer runeDeckSocketServer;
    private Client client;

    public WebSocketThread(RuneDeckSocketServer runeDeckSocketServer, Client client) {
        this.runeDeckSocketServer = runeDeckSocketServer;
        this.client = client;
        this.start();
    }

    public void stopSocketThread() {

        try {
            this.continueRunning.set(false);
            this.runeDeckSocketServer.stop();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("RuneDeckSocketServer was interupted");
            LOGGER.error(e.getMessage());

        }
    }

    @Override
    public void run() {

        try {
            while (this.continueRunning.get()) {
                if(this.client.getGameState().equals(GameState.LOGGED_IN)) {
                    SkillsMessage skillsMessage = new SkillsMessage(this.client);
                    String skillsMessageJSON = this.GSON.toJson(skillsMessage);
                    this.runeDeckSocketServer.broadcast(skillsMessageJSON);
                }else{
                    LogoutMessage logoutMessage = new LogoutMessage();
                    String logoutMessageJSON = this.GSON.toJson(logoutMessage);
                    this.runeDeckSocketServer.broadcast(logoutMessageJSON);
                }
                Thread.sleep(1000L);
            }
        }
        catch (InterruptedException e) {
            LOGGER.error("WebSocketThread was interupted");
        }
    }

}
