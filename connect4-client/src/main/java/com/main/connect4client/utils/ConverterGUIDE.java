package com.main.connect4client.utils;

import com.main.connect4client.controllers.fxml.MainController;
import com.main.connect4client.controllers.fxml.SignInController;
import com.main.connect4shared.domain.Player;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConverterGUIDE {
    public static void convertGUIDK(SignInController controller, Player player) {
        for (Field controllerField : controller.getClass().getDeclaredFields()) {
            for (Field playerField : player.getClass().getDeclaredFields()) {
                playerField.setAccessible(true);

                if (playerField.getName().equals(controllerField.getName())) {
                    if (controllerField.getType().getName().equals("javafx.scene.control.TextField")
                            && playerField.getType().getName().equals("java.lang.String")) {
                        try {
                            playerField.set(player, ((javafx.scene.control.TextField) controllerField.get(controller)).getText());
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    if (controllerField.getType().getName().equals("javafx.scene.control.PasswordField")
                            && playerField.getType().getName().equals("java.lang.String")) {
                        try {
                            playerField.set(player, ((javafx.scene.control.TextField) controllerField.get(controller)).getText());
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void convertDKUGUI(Player player, MainController controller) {
        for (Field controllerField : controller.getClass().getDeclaredFields()) {
            for (Field playerField : player.getClass().getDeclaredFields()) {
                playerField.setAccessible(true);

                if (playerField.getName().equals(controllerField.getName())) {
                    if (controllerField.getType().getName().equals("javafx.scene.control.Label")
                            && playerField.getType().getName().equals("java.lang.String")) {
                        try {
                            ((javafx.scene.control.Label) controllerField.get(controller)).setText((String) playerField.get(player));
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    if (controllerField.getType().getName().equals("javafx.scene.control.Label")
                            && playerField.getType().getName().equals("int")) {
                        try {
                            int number = (int) playerField.get(player);
                            ((javafx.scene.control.Label) controllerField.get(controller)).setText(String.valueOf(number));
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    if (controllerField.getType().getName().equals("javafx.scene.control.Label")
                            && playerField.getType().getName().equals("java.util.Date")) {
                        try {
                            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                            ((javafx.scene.control.Label) controllerField.get(controller)).setText(dateFormat.format(playerField.get(player)));
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
        }
    }
}
