package com.game.token.services;

import com.game.token.entities.Game;
import com.game.token.entities.Status;
import com.game.token.entities.Token;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService implements GameService {

    @Override
    public Game start() {
        Token token = new Token(INITIAL_POSITION);
        return new Game(token);
    }

    @Override
    public void moveToken(final Game game, final int position) {
        Token token = game.getToken();
        token.setPosition(token.getPosition() + position);
        updateGameStatus(game);
    }

    private void updateGameStatus(final Game game) {
        if (game.getToken().getPosition() == LAST_POSITION) {
            game.setStatus(Status.COMPLETED);
        }
    }
}
