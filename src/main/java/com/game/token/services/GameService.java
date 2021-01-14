package com.game.token.services;

import com.game.token.entities.Game;

public interface GameService {
    int INITIAL_POSITION = 1;
    int LAST_POSITION = 100;

    Game start();

    void moveToken(Game game, int i);

    int rollDie();
}
