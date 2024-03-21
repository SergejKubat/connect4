package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.client.ClientController;
import com.main.connect4client.controllers.fxml.SignUpController;
import com.main.connect4client.utils.Message;
import com.main.connect4client.utils.Session;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.utils.Validator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpGUIController {
    private final SignUpController signUpController;

    public SignUpGUIController(SignUpController signUpController) {
        this.signUpController = signUpController;
        this.signUpController.signInPageLabel.setOnMouseClicked(event -> openSignInPage());
        this.signUpController.signUpBtn.setOnAction(event -> singUp());
    }

    public void singUp() {
        String username = this.signUpController.usernameInput.getText();
        String email = this.signUpController.emailInput.getText();
        String password = this.signUpController.passwordInput.getText();
        String repeatedPassword = this.signUpController.repeatPasswordInput.getText();

        boolean validationResult = validateInputs(username, email, password, repeatedPassword);

        if (!validationResult) {
            return;
        }

        try {
            Player player = ClientController.getInstance().signUp(username, email, password);

            Session.getInstance().setPlayer(player);

            openMainPage();
        } catch (Exception ex) {
            ex.printStackTrace();
            Message.showMessage("Username already exists.", Alert.AlertType.ERROR);
        }
    }

    private boolean validateInputs(String username, String email, String password, String repeatedPassword) {
        clearInputErrors();

        boolean isValid = true;

        if (!Validator.validateUsername(username)) {
            this.signUpController.usernameInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validateEmail(email)) {
            this.signUpController.emailInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validatePassword(password)) {
            this.signUpController.passwordInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validatePassword(repeatedPassword)) {
            this.signUpController.repeatPasswordInputError.setVisible(true);
            isValid = false;
        } else {
            if (!password.equals(repeatedPassword)) {
                this.signUpController.repeatPasswordInputError.setVisible(true);
                this.signUpController.repeatPasswordInputError.setText("Passwords doesn't match.");
                isValid = false;
            }
        }

        return isValid;
    }

    private void clearInputErrors() {
        this.signUpController.usernameInputError.setVisible(false);
        this.signUpController.emailInputError.setVisible(false);
        this.signUpController.passwordInputError.setVisible(false);
        this.signUpController.repeatPasswordInputError.setVisible(false);
    }

    public void openMainPage() {
        Stage stage = (Stage) this.signUpController.signUpContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Main");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void openSignInPage() {
        Stage stage = (Stage) this.signUpController.signUpContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Sign In");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
