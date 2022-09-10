package com.runedeck;

import java.util.concurrent.atomic.AtomicBoolean;

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
                StreamDeckResponse streamDeckResponse = new StreamDeckResponse(this.client);
                String streamDeckResponseJSON = this.GSON.toJson(streamDeckResponse);
                this.runeDeckSocketServer.broadcast(streamDeckResponseJSON);
                Thread.sleep(1000L);
            }
        }
        catch (InterruptedException e) {
            LOGGER.error("WebSocketThread was interupted");
        }
    }

}