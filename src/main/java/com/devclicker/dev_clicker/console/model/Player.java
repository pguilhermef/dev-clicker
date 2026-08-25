package com.devclicker.dev_clicker.console.model;

public class Player {
    private String name;
    private Boolean wantsContinueGame;

    public Player(String name) {
        this.name = name;
        this.wantsContinueGame = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWantsContinueGame() {
        return wantsContinueGame;
    }

    public void setWantsContinueGame(Boolean continueGame) {
        this.wantsContinueGame = continueGame;
    }
}
