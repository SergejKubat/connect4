module com.main.connect4server {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.main.connect4server to javafx.fxml;
    exports com.main.connect4server;
    exports com.main.connect4server.controllers.server;
    opens com.main.connect4server.controllers.server to javafx.fxml;
    exports com.main.connect4server.controllers.fxml;
    opens com.main.connect4server.controllers.fxml to javafx.fxml;
}