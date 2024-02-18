package com.main.connect4client.utils;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Message {
    public static void showMessage(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        String title = getTitleFromAlertType(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setResizable(false);

        alert.showAndWait();
    }

    private static String getTitleFromAlertType(Alert.AlertType type) {
        switch (type) {
            case CONFIRMATION -> {
                return "Confirmation";
            }
            case INFORMATION -> {
                return "Information";
            }
            case WARNING -> {
                return "Warning";
            }
            case ERROR -> {
                return "Error";
            }
            default -> {
                return "None";
            }
        }
    }
}
