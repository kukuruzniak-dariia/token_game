package com.game.token.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game {
    private Token token;
    private Status status;

    public Game(Token token) {
        this.token = token;
        status = Status.NEW;
    }
}
