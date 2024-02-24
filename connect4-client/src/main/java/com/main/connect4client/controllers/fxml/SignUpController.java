package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.SignUpGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignUpController {
    public SignUpGUIController signUpGUIController;

    @FXML
    public AnchorPane signUpContainer;

    @FXML
    public TextField usernameInput;

    @FXML
    public TextField emailInput;

    @FXML
    public PasswordField passwordInput;

    @FXML
    public PasswordField repeatPasswordInput;

    @FXML
    public Label usernameInputError;

    @FXML
    public Label emailInputError;

    @FXML
    public Label passwordInputError;

    @FXML
    public Label repeatPasswordInputError;

    @FXML
    public Label signInPageLabel;

    @FXML
    public Button signUpBtn;

    public void initialize() throws IllegalArgumentException {
        this.signUpGUIController = new SignUpGUIController(this);
    }
}
