package com.main.connect4server.server;

import com.main.connect4server.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final List<ClientHandler> clientThreads;
    private ServerSocket serverSocket;
    private boolean running;

    public Server() {
        clientThreads = new ArrayList<>();
        running = true;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(5000);

            ExecutorService executorService = Executors.newCachedThreadPool();

            while (running) {
                Socket socket = serverSocket.accept();

                ClientHandler clientThread = new ClientHandler(socket);

                clientThreads.add(clientThread);

                executorService.execute(clientThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
            shutdown();
        }
    }

    public void shutdown() {
        try {
            running = false;

            // close server socket
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }

            // close all clients socket
            for (ClientHandler clientThread : clientThreads) {
                clientThread.shutdown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
