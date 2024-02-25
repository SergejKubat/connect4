package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.fxml.SignInController;
import com.main.connect4client.utils.Validator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInGUIController {
    private final SignInController signInController;

    public SignInGUIController(SignInController signInController) {
        this.signInController = signInController;
        this.signInController.signUpPageLabel.setOnMouseClicked(event -> openSignUpPage());
        this.signInController.signInBtn.setOnAction(event -> singIn());
    }

    public void singIn() {
//        String username = this.signInController.usernameInput.getText();
//        String password = this.signInController.passwordInput.getText();
//
//        boolean validationResult = validateInputs(username, password);
//
//        if (!validationResult) {
//            return;
//        }
//
//        // send credentials to remote server
//        boolean success = false;
//
//        if (!success) {
//            Message.showMessage("Server error.", Alert.AlertType.ERROR);
//            return;
//        }

        Stage stage = (Stage) this.signInController.signInContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateInputs(String username, String password) {
        clearInputErrors();

        boolean isValid = true;

        if (!Validator.validateUsername(username)) {
            this.signInController.usernameInputError.setVisible(true);
            isValid = false;
        }

        if (!Validator.validatePassword(password)) {
            this.signInController.passwordInputError.setVisible(true);
            isValid = false;
        }

        return isValid;
    }

    private void clearInputErrors() {
        this.signInController.usernameInputError.setVisible(false);
        this.signInController.passwordInputError.setVisible(false);
    }

    public void openSignUpPage() {
        Stage stage = (Stage) this.signInController.signInContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-up-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Sign Up");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
