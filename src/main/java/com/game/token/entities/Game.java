package com.game.token.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {
    private boolean inProgress;
    private Token token;
}
