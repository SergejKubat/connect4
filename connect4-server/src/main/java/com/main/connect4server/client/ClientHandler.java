package com.main.connect4server.client;

import com.main.connect4server.controllers.server.ServerController;
import com.main.connect4server.database.DatabaseConnection;
import com.main.connect4server.models.enums.GameState;
import com.main.connect4server.utils.ComputerPlayer;
import com.main.connect4shared.domain.ClickedColumn;
import com.main.connect4shared.domain.GameMove;
import com.main.connect4shared.domain.GenericEntity;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.request.Request;
import com.main.connect4shared.request.RequestOperation;
import com.main.connect4shared.response.Response;
import com.main.connect4shared.response.ResponseStatus;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class ClientHandler implements Runnable {
    private final Socket socket;

    ComputerPlayer computerPlayer;

    GameState gameState;

    private ObjectInputStream in;

    private ObjectOutputStream out;

    private boolean running;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.computerPlayer = new ComputerPlayer();
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Request request = (Request) read();

                assert request != null;

                RequestOperation requestOperation = request.getOperation();

                Response response = new Response();

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

                        send(response);
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

                        send(response);
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

                        send(response);
                    }
                    case GET_AVAILABLE_ROW -> {
                        ClickedColumn clickedColumn = (ClickedColumn) request.getData();

                        int firstEmptyRow = this.computerPlayer.getFirstEmptyRow(clickedColumn.getColumn());

                        response.setResult(firstEmptyRow);

                        send(response);
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

                            send(response);

                            break;
                        } else if (this.gameState == GameState.DRAW) {
                            response.setResult(ResponseStatus.DRAW);

                            send(response);

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

                            int currentWins = loser.getWins();

                            loser.setWins(--currentWins);

                            ServerController.getInstance().updatePlayerWins(loser);

                            send(response);
                        } else if (this.gameState == GameState.DRAW) {
                            response.setStatus(ResponseStatus.DRAW);

                            send(response);
                        } else {
                            // Notify human player to take the turn
                            response.setStatus(ResponseStatus.CONTINUE);
                            // Send computer player's selected row and computerColumn to player 1
                            response.setResult(new GameMove(computerRow, computerColumn));

                            send(response);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

                try {
                    DatabaseConnection.getInstance().closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                shutdown();
            }
        }
    }

    private void send(Object object) {
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object read() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void shutdown() {
        try {
            running = false;

            in.close();
            out.close();

            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
