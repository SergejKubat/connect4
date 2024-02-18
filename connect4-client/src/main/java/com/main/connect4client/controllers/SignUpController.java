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

    @FXML
    private Label usernameInputError;

    @FXML
    private Label emailInputError;

    @FXML
    private Label passwordInputError;

    @FXML
    private Label repeatPasswordInputError;

    public void singUp() throws IOException {
        String username = usernameInput.getText();
        String email = emailInput.getText();
        String password = passwordInput.getText();
        String repeatedPassword = repeatPasswordInput.getText();

        boolean validationResult = validateInputs(username, email, password, repeatedPassword);

        if (!validationResult) {
            return;
        }

        // send credentials to remote server
        /*boolean success = false;

        if (!success) {
            Message.showMessage("Server error.", Alert.AlertType.ERROR);
            return;
        }*/

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

    public boolean validateInputs(String username, String email, String password, String repeatedPassword) {
        clearInputErrors();

        boolean isValid = true;

        if (!Validator.validateUsername(username)) {
            usernameInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validateEmail(email)) {
            emailInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validatePassword(password)) {
            passwordInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validatePassword(repeatedPassword)) {
            repeatPasswordInputError.setVisible(true);
            isValid = false;
        } else {
            if (!password.equals(repeatedPassword)) {
                repeatPasswordInputError.setVisible(true);
                repeatPasswordInputError.setText("Passwords doesn't match.");
                isValid = false;
            }
        }

        return isValid;
    }

    public void clearInputErrors() {
        usernameInputError.setVisible(false);
        emailInputError.setVisible(false);
        passwordInputError.setVisible(false);
        repeatPasswordInputError.setVisible(false);
    }
}
