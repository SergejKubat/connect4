package com.main.connect4client.controllers.client;

import com.main.connect4client.models.*;
import com.main.connect4client.models.enums.RequestOperation;
import com.main.connect4client.models.enums.ResponseStatus;
import com.main.connect4client.utils.ClientSocket;

public class ClientController {
    private static ClientController instance;
    private final ClientSocket clientSocket;

    private ClientController() {
        clientSocket = new ClientSocket();
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            return new ClientController();
        }
        return instance;
    }

    public Player signIn(String username, String password) throws Exception {
        Player player = new Player(username, password);

        Request request = new Request(RequestOperation.SIGN_IN, player);

        clientSocket.send(request);

        Response response = clientSocket.read();

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (Player) response.getResult();
        }

        throw response.getException();
    }

    public Player signUp(String username, String email, String password) throws Exception {
        Player player = new Player(username, email, password);

        Request request = new Request(RequestOperation.SIGN_UP, player);

        clientSocket.send(request);

        Response response = clientSocket.read();

        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (Player) response.getResult();
        }

        throw response.getException();
    }

    public int startMatch() {
        Request request = new Request(RequestOperation.START_MATCH, null);

        clientSocket.send(request);

        Response response = clientSocket.read();

        return (int) response.getResult();
    }

    public int getAvailableRow(int column) {
        ClickedColumn chooseColumn = new ClickedColumn(column);

        Request request = new Request(RequestOperation.GET_AVAILABLE_ROW, chooseColumn);

        clientSocket.send(request);

        Response response = clientSocket.read();

        return (int) response.getResult();
    }

    public Response sendMove(int row, int column) {
        GameMove move = new GameMove(row, column);

        Request request = new Request(RequestOperation.SEND_MOVE, move);

        clientSocket.send(request);

        return clientSocket.read();
    }

    public GameMove receiveMove() {
        Response response = clientSocket.read();

        return (GameMove) response.getResult();
    }
}
