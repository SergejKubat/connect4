package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.client.ClientController;
import com.main.connect4client.controllers.fxml.SignInController;
import com.main.connect4client.utils.ConverterGUIDE;
import com.main.connect4client.utils.Message;
import com.main.connect4client.utils.Session;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.utils.Validator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        Player player = new Player();

        ConverterGUIDE.converterGUID(this.signInController, player);

        String username = player.getUsername();
        String password = player.getPassword();

        boolean validationResult = validateInputs(username, password);

        if (!validationResult) {
            return;
        }

        try {
            player = ClientController.getInstance().signIn(username, password);

            Session.getInstance().setPlayer(player);

            openMainPage();
        } catch (Exception ex) {
            ex.printStackTrace();
            Message.showMessage(ex.getMessage(), Alert.AlertType.ERROR);
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

    public void openMainPage() {
        Stage stage = (Stage) this.signInController.signInContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Main");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
