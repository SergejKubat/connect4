package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.SignInGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignInController {
    public SignInGUIController signInGUIController;

    @FXML
    public AnchorPane signInContainer;

    @FXML
    public TextField username;

    @FXML
    public PasswordField password;

    @FXML
    public Label usernameInputError;

    @FXML
    public Label passwordInputError;

    @FXML
    public Label signUpPageLabel;

    @FXML
    public Button signInBtn;

    public void initialize() throws IllegalArgumentException {
        this.signInGUIController = new SignInGUIController(this);
    }
}
