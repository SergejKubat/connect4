package com.main.connect4server.models;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class Receiver implements Serializable {
    private final Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public Object receive() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}