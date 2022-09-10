package com.runedeck;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuneDeckSocketServer extends WebSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuneDeckSocketServer.class);

    public RuneDeckSocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //conn.send("connected");
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected!");
        LOGGER.info(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        LOGGER.info(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " clsoed connection");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {}

    @Override
    public void onError(WebSocket conn, Exception ex) {
        LOGGER.error(ex.getMessage());
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        LOGGER.info("RuneDeckSocketServer started on port: " + this.getPort());
        System.out.println("RuneDeckSocketServer started on port: " + this.getPort());
        this.setConnectionLostTimeout(60);
    }

}