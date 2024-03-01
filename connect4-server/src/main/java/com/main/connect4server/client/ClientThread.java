package com.main.connect4server.client;

import com.main.connect4server.controllers.server.ServerController;
import com.main.connect4server.database.DatabaseConnection;
import com.main.connect4server.models.enums.GameState;
import com.main.connect4shared.domain.ClickedColumn;
import com.main.connect4shared.domain.GameMove;
import com.main.connect4shared.domain.GenericEntity;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.request.Receiver;
import com.main.connect4shared.request.Request;
import com.main.connect4shared.request.RequestOperation;
import com.main.connect4shared.response.Response;
import com.main.connect4shared.response.ResponseStatus;
import com.main.connect4shared.response.Sender;

import java.net.Socket;
import java.util.List;
import java.util.Random;

public class ClientThread extends Thread {
    private final Receiver receiver;

    private final Sender sender;

    ComputerPlayer computerPlayer;

    GameState gameState;

    public ClientThread(Socket socket) {
        this.receiver = new Receiver(socket);
        this.sender = new Sender(socket);
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRequest() throws Exception {
        while (!this.isInterrupted()) {
            try {
                Request request = (Request) this.receiver.receive();

                Response response = new Response();

                RequestOperation requestOperation = request.getOperation();

                switch (requestOperation) {
                    case SIGN_UP -> {
                        try {
                            GenericEntity player = ServerController.getInstance().signUp(request.getData());

                            ClientSession.getInstance().getPlayers().add((Player) player);

                            response.setResult(player);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setException(ex);
                            response.setStatus(ResponseStatus.ERROR);
                        }

                        this.computerPlayer = new ComputerPlayer();

                        sender.send(response);
                    }
                    case SIGN_IN -> {
                        try {
                            GenericEntity player = ServerController.getInstance().signIn(request.getData());

                            ClientSession.getInstance().getPlayers().add((Player) player);

                            response.setResult(player);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setException(ex);
                            response.setStatus(ResponseStatus.ERROR);
                        }

                        this.computerPlayer = new ComputerPlayer();

                        sender.send(response);
                    }
                    case GET_PLAYERS -> {
                        try {
                            List<GenericEntity> players = ServerController.getInstance().getAllPlayers(new Player());

                            response.setResult(players);
                            response.setStatus(ResponseStatus.SUCCESS);
                        } catch (Exception ex) {
                            response.setException(ex);
                            response.setStatus(ResponseStatus.ERROR);
                        }

                        sender.send(response);
                    }
                    case GET_AVAILABLE_ROW -> {
                        ClickedColumn clickedColumn = (ClickedColumn) request.getData();

                        System.out.println("Clicked column: " + clickedColumn.getColumn());

                        int firstEmptyRow = this.computerPlayer.getFirstEmptyRow(clickedColumn.getColumn());

                        response.setResult(firstEmptyRow);

                        sender.send(response);
                    }
                    case SEND_MOVE -> {
                        GameMove move = (GameMove) request.getData();

                        int column = move.getCol();

                        this.gameState = this.computerPlayer.updateBoard(column, 'X');

                        // Check if human player wins
                        if (this.gameState == GameState.XWin) {
                            Player winner = ClientSession.getInstance().getPlayers().get(0);

                            int currentWins = winner.getWins();

                            winner.setWins(++currentWins);

                            ServerController.getInstance().updatePlayerWins(winner);

                            response.setStatus(ResponseStatus.PLAYER_1_WON);

                            sender.send(response);

                            break;
                        } else if (this.gameState == GameState.DRAW) {
                            response.setResult(ResponseStatus.DRAW);

                            sender.send(response);

                            break;
                        }

                        Random rand = new Random();

                        int computerColumn;
                        int computerRow;

                        int columns = 7;

                        do {
                            computerColumn = Math.abs(rand.nextInt() % columns);
                            computerRow = this.computerPlayer.getFirstEmptyRow(computerColumn);
                        } while (computerRow == -1);

                        // Generate a move from computer player
                        this.gameState = computerPlayer.updateBoard(computerColumn, 'O');

                        // Check if computer player wins
                        if (this.gameState == GameState.OWin) {
                            response.setStatus(ResponseStatus.PLAYER_2_WON);
                            response.setResult(new GameMove(computerRow, computerColumn));

                            Player loser = ClientSession.getInstance().getPlayers().get(0);

                            int currentDefeats = loser.getDefeats();

                            loser.setDefeats(++currentDefeats);

                            ServerController.getInstance().updatePlayerWins(loser);

                            sender.send(response);
                        } else if (this.gameState == GameState.DRAW) {
                            response.setStatus(ResponseStatus.DRAW);

                            sender.send(response);
                        } else {
                            // Notify human player to take the turn
                            response.setStatus(ResponseStatus.CONTINUE);
                            // Send computer player's selected row and computerColumn to player 1
                            response.setResult(new GameMove(computerRow, computerColumn));

                            sender.send(response);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                DatabaseConnection.getInstance().closeConnection();
            }
        }
    }
}
