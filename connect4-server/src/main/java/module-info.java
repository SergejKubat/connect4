module com.main.connect4server {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.main.connect4server to javafx.fxml;
    exports com.main.connect4server;
    exports com.main.connect4server.controllers;
    opens com.main.connect4server.controllers to javafx.fxml;
}