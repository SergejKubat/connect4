package com.main.connect4client.controllers;

import com.main.connect4client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    private AnchorPane signInContainer;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    public void singIn() throws IOException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        Stage stage = (Stage) signInContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Main");
    }

    public void openSignUpPage() throws IOException {
        Stage stage = (Stage) signInContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-up-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Sign Up");
    }
}
