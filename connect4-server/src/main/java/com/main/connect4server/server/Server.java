package com.main.connect4server.server;

import com.main.connect4server.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                Socket socket = serverSocket.accept();

                // handle client request
                ClientThread clientThread = new ClientThread(socket);

                clientThread.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
