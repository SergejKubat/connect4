module com.main.connect4client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires connect.shared;

    exports com.main.connect4client;
    exports com.main.connect4client.controllers.fxml;
    exports com.main.connect4client.controllers.gui;

    opens com.main.connect4client to javafx.fxml;

    opens com.main.connect4client.controllers.fxml to javafx.fxml;
}