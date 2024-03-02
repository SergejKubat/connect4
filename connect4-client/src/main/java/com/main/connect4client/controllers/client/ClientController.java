package com.main.connect4client.controllers.client;

import com.main.connect4shared.domain.ClickedColumn;
import com.main.connect4shared.domain.GameMove;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.request.Request;
import com.main.connect4shared.request.RequestOperation;
import com.main.connect4shared.response.Response;
import com.main.connect4shared.response.ResponseStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientController {
    private static ClientController instance;

    private Socket socket;

    private ClientController() {
        try {
            socket = new Socket("localhost", 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }

        return instance;
    }

    public Player signIn(String username, String password) throws Exception {
        Player player = new Player(username, password);

        Request request = new Request(RequestOperation.SIGN_IN, player);

        send(request);

        Response response = read();

        assert response != null;

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (Player) response.getResult();
        }

        throw response.getException();
    }

    public Player signUp(String username, String email, String password) throws Exception {
        Player player = new Player(username, email, password);

        Request request = new Request(RequestOperation.SIGN_UP, player);

        send(request);

        Response response = read();

        assert response != null;

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (Player) response.getResult();
        }

        throw response.getException();
    }

    public int startMatch() {
        Request request = new Request(RequestOperation.START_MATCH, null);

        send(request);

        Response response = read();

        assert response != null;

        return (int) response.getResult();
    }

    public int getAvailableRow(int column) {
        ClickedColumn chooseColumn = new ClickedColumn(column);

        Request request = new Request(RequestOperation.GET_AVAILABLE_ROW, chooseColumn);

        send(request);

        Response response = read();

        assert response != null;

        return (int) response.getResult();
    }

    public Response sendMove(int row, int column) {
        GameMove move = new GameMove(row, column);

        Request request = new Request(RequestOperation.SEND_MOVE, move);

        send(request);

        return read();
    }

    public GameMove receiveMove() {
        Response response = read();

        assert response != null;

        return (GameMove) response.getResult();
    }

    private void send(Request request) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Response read() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return (Response) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
