package com.runedeck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provides;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
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

    private WebSocketThread webSocketThread;
    private boolean stopSocket = false;

    @Override
    protected void startUp() throws Exception {

        //        Runnable helloRunnable = new Runnable() {
        //
        //            public void run() {
        //                int currentHealth = RuneDeckPlugin.this.client.getBoostedSkillLevel(Skill.HITPOINTS);
        //                int totalHealth = RuneDeckPlugin.this.client.getRealSkillLevel(Skill.HITPOINTS);
        //                System.out.println("" + currentHealth + "/" + totalHealth + " Health");
        //            }
        //        };
        //
        //        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        //        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);

    }

    @Override
    protected void shutDown() throws Exception {
        this.webSocketThread.stopSocket = true;
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {
        if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {

            //            try {
            //
            //                ServerSocket serverSocket = new ServerSocket(42069);
            //                this.echoClientHandler = new EchoClientHandler(serverSocket, this.client);
            //                this.echoClientHandler.start();
            //            }
            //            catch (IOException e) {
            //                e.printStackTrace();
            //            }

            this.webSocketThread = new WebSocketThread(this.client);
            this.webSocketThread.start();

        }

    }

    @Provides
    RuneDeckConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RuneDeckConfig.class);
    }

    private static class WebSocketThread extends Thread {

        private Client client;
        private boolean stopSocket = false;

        public WebSocketThread(Client client) {
            this.client = client;
        }

        @Override
        public void run() {
            int port = 42069; // 843 flash policy port

            try {
                RuneDeckSocketServer s = new RuneDeckSocketServer(port);
                s.start();
                System.out.println("ChatServer started on port: " + s.getPort());

                while (this.stopSocket == false) {
                    int currentHealth = this.client.getBoostedSkillLevel(Skill.HITPOINTS);
                    int totalHealth = this.client.getRealSkillLevel(Skill.HITPOINTS);
                    System.out.println("" + currentHealth + "/" + totalHealth + " Health");
                    s.broadcast("" + currentHealth + "/" + totalHealth + " Health");
                    Thread.sleep(1000L);
                }
            }
            catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private static class EchoClientHandler extends Thread {

        private Client client;

        private ServerSocket serverSocket;
        private Socket clientSocket;
        private boolean stopSocket = false;

        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(ServerSocket socket, Client client) {
            this.serverSocket = socket;
            this.client = client;
        }

        @Override
        public void run() {

            //            try {
            //                System.out.println("waiting for connection");
            //                this.clientSocket = this.serverSocket.accept();
            //                System.out.println("connected");
            //                this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            //                this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            //
            //                String key = null;
            //
            //                for (String line = this.in.readLine(); line != null; line = this.in.readLine()) {
            //                    if (!line.startsWith("Sec-WebSocket-Key: ")) continue;
            //                    key = line.replace("Sec-WebSocket-Key: ", "");
            //                    System.out.println("key: " + key);
            //                    break;
            //                }
            //
            //                key = key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
            //
            //                String hashstr = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA1").digest(key.getBytes("UTF-8")));
            //
            //                this.out.append("HTTP/1.1 101 Switching Protocols\r\n");
            //                this.out.append("Date: Thu, 08 Sep 2022 03:34:45 GMT\r\n");
            //                this.out.append("Connection: upgrade\r\n");
            //                this.out.append("Upgrade: websocket\r\n");
            //                this.out.append("Sec-WebSocket-Accept: " + hashstr + "\r\n");
            //                this.out.append("X-Powered-By: Ratchet/0.4.4\r\n");
            //                this.out.append("\r\n");
            //
            //                this.out.flush();
            //
            //                while (this.stopSocket == false) {
            //                    int currentHealth = this.client.getBoostedSkillLevel(Skill.HITPOINTS);
            //                    int totalHealth = this.client.getRealSkillLevel(Skill.HITPOINTS);
            //                    System.out.println("" + currentHealth + "/" + totalHealth + " Health");
            //
            //                    this.out.append("\r\n");
            //                    this.out.append("" + currentHealth + "/" + totalHealth + " Health");
            //
            //                    this.out.flush();
            //                    Thread.sleep(1000L);
            //
            //                }
            //
            //                this.in.close();
            //                this.out.close();
            //                this.clientSocket.close();
            //            }
            //            catch (Exception e) {
            //                System.out.println(e);
            //            }

            int port = 42069; // 843 flash policy port

            try {
                RuneDeckSocketServer s = new RuneDeckSocketServer(port);
                s.start();
                System.out.println("ChatServer started on port: " + s.getPort());

                BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

                while (this.stopSocket == false) {
                    int currentHealth = this.client.getBoostedSkillLevel(Skill.HITPOINTS);
                    int totalHealth = this.client.getRealSkillLevel(Skill.HITPOINTS);
                    System.out.println("" + currentHealth + "/" + totalHealth + " Health");
                    s.broadcast("" + currentHealth + "/" + totalHealth + " Health");
                    Thread.sleep(1000L);
                }
            }
            catch (Exception e) {
                // TODO: handle exception
            }

        }

        public void stopSocket() {
            System.out.println("stopSocket called");
            this.stopSocket = true;
        }
    }

}
