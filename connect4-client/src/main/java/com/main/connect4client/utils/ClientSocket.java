package com.main.connect4client.utils;

import com.main.connect4shared.request.Request;
import com.main.connect4shared.response.Response;

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
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Response read() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return (Response) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
