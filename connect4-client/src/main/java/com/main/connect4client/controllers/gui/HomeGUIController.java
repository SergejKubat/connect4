package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.fxml.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeGUIController {
    private final HomeController homeController;

    public HomeGUIController(HomeController homeController) {
        this.homeController = homeController;
        this.homeController.openSignInBtn.setOnAction(event -> openSignInPage());
        this.homeController.openSignUpBtn.setOnAction(event -> openSignUpPage());
    }

    public void openSignInPage() {
        Stage stage = (Stage) this.homeController.homeContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-in-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Sign In");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void openSignUpPage() {
        Stage stage = (Stage) this.homeController.homeContainer.getScene().getWindow();

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
