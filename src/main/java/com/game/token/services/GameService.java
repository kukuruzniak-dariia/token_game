package com.game.token.services;

import com.game.token.entities.Game;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    int INITIAL_TOKEN_POSITION = 1;
    int LAST_TOKEN_POSITION = 100;
    int MAX_ROLE_A_DIE_RESULT = 6;

    Game start();

    void moveToken(Game game, int i);

    int rollDie();
}
