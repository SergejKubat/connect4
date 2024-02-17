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

public class SignUpController {
    @FXML
    private AnchorPane signUpContainer;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private PasswordField repeatPasswordInput;

    public void singUp() throws IOException {
        String username = usernameInput.getText();
        String email = emailInput.getText();
        String password = passwordInput.getText();
        String repeatedPassword = repeatPasswordInput.getText();

        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Repeated password: " + repeatedPassword);

        Stage stage = (Stage) signUpContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Main");
    }

    @FXML
    public void openSignInPage() throws IOException {
        Stage stage = (Stage) signUpContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Sign In");
    }
}
