package com.game.token.entities;

import lombok.Data;

@Data
public class Game {
    private Token token;
    private Status status;

    public Game(Token token) {
        this.token = token;
    }
}
