package com.game.token.services;

import com.game.token.entities.Game;
import com.game.token.entities.Status;
import com.game.token.entities.Token;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DefaultGameService implements GameService {

    @Override
    public Game start() {
        Game game = new Game(new Token(INITIAL_TOKEN_POSITION));
        game.setStatus(Status.IN_PROGRESS);
        return game;
    }

    @Override
    public void moveToken(final Game game, final int spaces) {
        Token token = game.getToken();
        if (checkIfTokenCanBeMoved(token, spaces)) {
            token.setPosition(token.getPosition() + spaces);
            updateGameStatus(game);
        }
    }

    @Override
    public int rollDie() {
        return new Random().nextInt(MAX_ROLE_A_DIE_RESULT) + 1;
    }

    private boolean checkIfTokenCanBeMoved(Token token, int spaces) {
        return token.getPosition() + spaces <= LAST_TOKEN_POSITION;
    }

    private void updateGameStatus(final Game game) {
        if (game.getToken().getPosition() == LAST_TOKEN_POSITION) {
            game.setStatus(Status.COMPLETED);
        }
    }
}
