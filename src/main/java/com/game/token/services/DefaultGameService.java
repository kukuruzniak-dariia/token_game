package com.game.token.services;

import com.game.token.entities.Game;
import com.game.token.entities.Token;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {

    @Override
    public Game start() {
        Token token = new Token(INITIAL_POSITION);

        return new Game(true, token);
    }
}
