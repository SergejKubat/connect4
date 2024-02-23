package com.main.connect4server.server;

import com.main.connect4server.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    public static int PLAYER1 = 1;
    public static int PLAYER2 = 2;
    public static int PLAYER1_WON = 5;
    public static int PLAYER2_WON = 8;
    public static int DRAW = 3;
    public static int CONTINUE = 4;

    public static final int COLS = 7;
    public static final int ROWS = 6;

    private final ServerSocket serverSocket;
    private final ArrayList<ClientThread> players;


    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.players = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();

                ClientThread handleClientRequest = new ClientThread(socket);

                players.add(handleClientRequest);

                handleClientRequest.start();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void stopServer() throws IOException {
        serverSocket.close();

        for (ClientThread player : players) {
            player.getSocket().close();
        }
    }
}
