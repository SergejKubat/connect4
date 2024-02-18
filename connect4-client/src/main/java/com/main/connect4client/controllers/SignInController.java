package com.main.connect4client.controllers;

import com.main.connect4client.Main;
import com.main.connect4client.utils.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    @FXML
    private Label usernameInputError;

    @FXML
    private Label passwordInputError;

    public void singIn() throws IOException {
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        /*boolean validationResult = validateInputs(username, password);

        if (!validationResult) {
            return;
        }*/

        // send credentials to remote server
        /*boolean success = false;

        if (!success) {
            Message.showMessage("Server error.", Alert.AlertType.ERROR);
            return;
        }*/

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

    public boolean validateInputs(String username, String password) {
        clearInputErrors();

        boolean isValid = true;

        if (!Validator.validateUsername(username)) {
            usernameInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validatePassword(password)) {
            passwordInputError.setVisible(true);
            isValid = false;
        }

        return isValid;
    }

    public void clearInputErrors() {
        usernameInputError.setVisible(false);
        passwordInputError.setVisible(false);
    }
}
