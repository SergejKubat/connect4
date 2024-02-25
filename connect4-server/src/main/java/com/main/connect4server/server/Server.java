package com.main.connect4server.server;

import com.main.connect4server.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    private final List<ClientThread> players;


    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.players = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                Socket socket = serverSocket.accept();

                System.out.println("Socket exists: " + socket.toString());

                // handle client request
                ClientThread clientThread = new ClientThread(socket);

                players.add(clientThread);

                clientThread.start();
            }
            System.out.println("No longer accepting connections!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
