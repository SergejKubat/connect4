package com.main.connect4server.client;

import com.main.connect4shared.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class ClientSession {
    private static ClientSession instance;

    private final List<Player> players;

    private ClientSession() {
        players = new ArrayList<>();
    }

    public static synchronized ClientSession getInstance() {
        if (instance == null) {
            instance = new ClientSession();
        }
        return instance;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
