package com.main.connect4client.controllers;

import com.main.connect4client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private AnchorPane homeContainer;

    @FXML
    public void openSignInPage() throws IOException {
        Stage stage = (Stage) homeContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Sign In");
    }

    public void openSignUpPage() throws IOException {
        Stage stage = (Stage) homeContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-up-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Sign Up");
    }
}