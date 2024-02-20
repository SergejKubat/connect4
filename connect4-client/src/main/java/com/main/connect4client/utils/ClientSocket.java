package com.main.connect4client.utils;

import com.main.connect4client.models.Request;
import com.main.connect4client.models.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    private Socket socket;

    public ClientSocket() {
        try {
            socket = new Socket("localhost", 5000);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void send(Request request) {
        try {
            ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Response read() {
        try {
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            return (Response) inSocket.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
