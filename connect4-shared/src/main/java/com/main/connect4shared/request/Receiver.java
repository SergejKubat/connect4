package com.main.connect4shared.request;

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
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}