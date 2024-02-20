package com.main.connect4client.controllers;

import com.main.connect4client.controllers.client.ClientController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchController {
    private static final double PLACEHOLDER_SIZE = 60;

    private static final int COLUMNS = 7;

    private static final int ROWS = 6;
    @FXML
    public GridPane boardContainer;
    @FXML
    public Label statusLabel;
    @FXML
    public Circle statusCircle;
    private boolean playerOneTurn = false;
    private final boolean continueToPlay = true;
    private boolean waiting = true;
    private final boolean playerOneMove = false;
    private int rowSelected;
    private int columnSelected;
    private final Token[][] board = new Token[COLUMNS][ROWS];

    @FXML
    public void initialize() {
        Shape board = generateBoard();
        List<Rectangle> columns = generateColumns();

        boardContainer.add(board, 0, 0);
        boardContainer.getChildren().addAll(columns);
    }

    private Shape generateBoard() {
        Shape shape = new Rectangle((COLUMNS + 1) * PLACEHOLDER_SIZE, (ROWS + 1) * PLACEHOLDER_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(PLACEHOLDER_SIZE / 2);

                circle.setCenterX(PLACEHOLDER_SIZE / 2);
                circle.setCenterY(PLACEHOLDER_SIZE / 2);
                circle.setTranslateX(x * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
                circle.setTranslateY(y * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }

        Light.Distant light = new Light.Distant();

        light.setAzimuth(50.0);
        light.setElevation(25.0);

        Lighting lighting = new Lighting();

        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.rgb(14, 28, 41));
        shape.setEffect(lighting);

        return shape;
    }

    private List<Rectangle> generateColumns() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rectangle = new Rectangle(PLACEHOLDER_SIZE, (ROWS + 1) * PLACEHOLDER_SIZE);

            rectangle.setTranslateX(x * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
            rectangle.setFill(Color.TRANSPARENT);

            rectangle.setOnMouseEntered(e -> {
                rectangle.setCursor(Cursor.HAND);
                rectangle.setFill(Color.rgb(122, 136, 171, 0.2));
            });

            rectangle.setOnMouseExited(e -> rectangle.setFill(Color.TRANSPARENT));

            final int column = x;

            rectangle.setOnMouseClicked((MouseEvent event) -> {
                try {
                    int row = ClientController.getInstance().getAvailableRow(column);
                    if (row != -1) {
                        if (playerOneTurn) {
                            placeToken(new Token(playerOneMove), column, row);
                            playerOneTurn = false;
                            rowSelected = row;
                            columnSelected = column;
                            waiting = false;
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });

            list.add(rectangle);
        }

        return list;
    }

    private void placeToken(Token token, int column, int row) {
        board[column][row] = token;

        boardContainer.getChildren().add(token);

        token.setTranslateX(column * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), token);

        animation.setToY(row * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);

        animation.play();
    }

    private Optional<Token> getToken(int column, int row) {
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS) {
            return Optional.empty();
        }

        return Optional.ofNullable(board[column][row]);
    }

    private static class Token extends Circle {

        public Token(boolean isRed) {
            super(PLACEHOLDER_SIZE / 2, isRed ? Color.RED : Color.YELLOW);

            setCenterX(PLACEHOLDER_SIZE / 2);
            setCenterY(PLACEHOLDER_SIZE / 2);
        }
    }
}
