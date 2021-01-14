package com.game.token.services;

import com.game.token.entities.Game;

public interface GameService {
    int INITIAL_POSITION = 1;

    Game start();
}
