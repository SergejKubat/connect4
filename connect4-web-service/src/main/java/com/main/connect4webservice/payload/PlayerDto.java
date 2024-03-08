package com.main.connect4webservice.payload;

import java.util.Date;

public class PlayerDto {
    private Long id;

    private String username;

    private int wins;

    private Date registeredAt;

    public PlayerDto() {
    }

    public PlayerDto(Long id, String username, int wins, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.wins = wins;
        this.registeredAt = registeredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
}
