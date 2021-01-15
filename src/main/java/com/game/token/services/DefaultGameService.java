package com.game.token.services;

import com.game.token.entities.Game;
import com.game.token.entities.Status;
import com.game.token.entities.Token;
import com.game.token.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service(value = "gameService")
public class DefaultGameService implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game start() {
        Game game = new Game();
        game.setToken(new Token(INITIAL_TOKEN_POSITION));
        game.setStatus(Status.IN_PROGRESS);

        return gameRepository.save(game);
    }

    @Override
    public Game moveToken(final long gameId, final int spaces) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException(String.format("Can not fetch game by id = [%s]", gameId)));
        Token token = game.getToken();
        if (checkIfTokenCanNotBeMoved(token, spaces)) {
            return game;
        }

        token.setPosition(token.getPosition() + spaces);
        updateGameStatus(game);
        return gameRepository.save(game);
    }

    @Override
    public int rollDie() {
        return new Random().nextInt(MAX_ROLE_A_DIE_RESULT) + 1;
    }

    private boolean checkIfTokenCanNotBeMoved(Token token, int spaces) {
        return token.getPosition() + spaces > LAST_TOKEN_POSITION;
    }

    private void updateGameStatus(final Game game) {
        if (game.getToken().getPosition() == LAST_TOKEN_POSITION) {
            game.setStatus(Status.COMPLETED);
        }
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
