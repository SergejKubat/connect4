package com.main.connect4client.utils;

import com.main.connect4shared.domain.Player;

public class Session {
    private static Session instance;

    private Player player;

    private Session() {
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }

        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
