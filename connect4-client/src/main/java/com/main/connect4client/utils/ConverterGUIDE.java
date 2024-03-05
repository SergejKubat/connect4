package com.main.connect4client.utils;

import com.main.connect4client.controllers.fxml.SignInController;
import com.main.connect4shared.domain.Player;

import java.lang.reflect.Field;

public class ConverterGUIDE {
    public static void converterGUID(SignInController controller, Player player) {
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
}
