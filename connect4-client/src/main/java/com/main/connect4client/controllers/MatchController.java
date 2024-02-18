package com.main.connect4client.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class MatchController {
    private static final double PLACEHOLDER_SIZE = 60;

    private static final int COLUMNS = 7;

    private static final int ROWS = 6;

    @FXML
    public GridPane matchContainer;

    @FXML
    public Pane boardPane;

    @FXML
    public void initialize() {
        Shape gridShape = generateGrid();

        matchContainer.add(gridShape, 0, 0);
        matchContainer.getChildren().addAll(generateColumns());
    }

    private Shape generateGrid() {
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
            Rectangle rect = new Rectangle(PLACEHOLDER_SIZE, (ROWS + 1) * PLACEHOLDER_SIZE);

            rect.setTranslateX(x * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(122, 136, 171, 0.2)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            list.add(rect);
        }

        return list;
    }
}
