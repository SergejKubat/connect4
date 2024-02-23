package com.main.connect4server.client;

import com.main.connect4server.models.GenericEntity;
import com.main.connect4server.models.Player;

import java.util.ArrayList;

public class ClientSession {
    private static ClientSession instance;

    private ArrayList<Player> players;

    private ClientSession() {
        players = new ArrayList<>();
    }

    public static synchronized ClientSession getInstance() {
        if (instance == null) {
            instance = new ClientSession();
        }
        return instance;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getIndexOfPlayer(Player player){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).getId().equals(player.getId()))
                return i;
        }

        return -1;
    }

    public void addPlayer(GenericEntity object) {
        if (object instanceof Player) {
            if (!players.contains(object)) {
                players.add((Player) object);
            }
        }
    }

    public void removePlayer(Object object) {
        try {
            if (object instanceof Player) {
                for (Player player : players) {
                    players.remove(object);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void logoutAll() {
        players = new ArrayList<>();
    }
}
