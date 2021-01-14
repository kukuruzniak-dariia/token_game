package com.game.token.services;

import com.game.token.entities.Game;
import com.game.token.entities.Token;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultGameService implements GameService {

    @Override
    public Game start() {
        Token token = new Token(INITIAL_POSITION);
        return new Game(token);
    }

    @Override
    public void moveToken(Game game, int position) {
        Optional.ofNullable(game.getToken())
                .ifPresent(token -> token.setPosition(token.getPosition() + position));
    }
}
