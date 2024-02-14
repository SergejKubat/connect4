module com.main.connect4client {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.main.connect4client;
    exports com.main.connect4client.controllers;
    opens com.main.connect4client to javafx.fxml;
    opens com.main.connect4client.controllers to javafx.fxml;
}