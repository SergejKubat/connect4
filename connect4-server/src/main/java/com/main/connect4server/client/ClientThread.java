package com.main.connect4server.client;

import com.main.connect4server.controllers.server.ServerController;
import com.main.connect4server.database.DatabaseConnection;
import com.main.connect4server.models.*;
import com.main.connect4server.models.enums.GameState;
import com.main.connect4server.models.enums.RequestOperation;
import com.main.connect4server.models.enums.ResponseStatus;
import com.main.connect4server.server.Server;

import java.net.Socket;
import java.util.List;
import java.util.Random;

public class ClientThread extends Thread {
    private Socket socket;

    private final Sender sender;

    private final Receiver receiver;

    ComputerPlayer computerPlayer;

    GameState gameState;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void handleRequest() throws Exception {
        while (!isInterrupted()) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();

                try {
                    RequestOperation requestOperation = request.getOperation();

                    switch (requestOperation) {
                        case SIGN_UP -> {
                            try {
                                GenericEntity object = ServerController.getInstance().signUp(request.getData());

                                ClientSession.getInstance().getPlayers().add((Player) object);

                                response.setResult(object);
                                response.setStatus(ResponseStatus.SUCCESS);
                            } catch (Exception ex) {
                                response.setStatus(ResponseStatus.ERROR);
                                response.setException(ex);
                            }

                            this.computerPlayer = new ComputerPlayer();

                            sender.send(response);
                        }
                        case SIGN_IN -> {
                            try {
                                GenericEntity object = ServerController.getInstance().signIn(request.getData());

                                ClientSession.getInstance().getPlayers().add((Player) object);

                                response.setResult(object);
                                response.setStatus(ResponseStatus.SUCCESS);
                            } catch (Exception ex) {
                                response.setStatus(ResponseStatus.ERROR);
                                response.setException(ex);
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
                                response.setStatus(ResponseStatus.ERROR);
                                response.setException(ex);
                            }

                            sender.send(response);
                        }
                        case GET_AVAILABLE_ROW -> {
                            ClickedColumn c1 = (ClickedColumn) request.getData();

                            int firstEmptyRow = computerPlayer.getFirstEmptyRow(c1.getColumn());

                            response.setResult(firstEmptyRow);

                            sender.send(response);
                        }
                        case SEND_MOVE -> {
                            GameMove move = (GameMove) request.getData();

                            int column = move.getCol();

                            gameState = computerPlayer.updateBoard(column, 'X');

                            // Check if human player wins
                            if (gameState == GameState.XWin) {
                                Player winner = ClientSession.getInstance().getPlayers().get(0);

                                int currentWins = winner.getWins();

                                winner.setWins(++currentWins);

                                ServerController.getInstance().updatePlayerWins(winner);

                                response.setStatus(ResponseStatus.PLAYER_1_WON);

                                sender.send(response);

                                break;
                            } else if (gameState == GameState.DRAW) {
                                response.setResult(ResponseStatus.DRAW);

                                sender.send(response);

                                break;
                            }

                            Random rand = new Random();

                            int computerColumn;
                            int computerRow;

                            do {
                                computerColumn = Math.abs(rand.nextInt() % Server.COLS);
                                computerRow = computerPlayer.getFirstEmptyRow(computerColumn);
                            } while (computerRow == -1);

                            // Generate a move from computer player
                            gameState = computerPlayer.updateBoard(computerColumn, 'O');

                            // Check if computer player wins
                            if (gameState == GameState.OWin) {
                                response.setStatus(ResponseStatus.PLAYER_2_WON);
                                response.setResult(new GameMove(computerRow, computerColumn));

                                Player loser = ClientSession.getInstance().getPlayers().get(0);

                                int currentDefeats = loser.getDefeats();

                                loser.setDefeats(++currentDefeats);

                                ServerController.getInstance().updatePlayerWins(loser);

                                sender.send(response);
                            } else if (gameState == GameState.DRAW) {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                DatabaseConnection.getInstance().closeConnection();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
