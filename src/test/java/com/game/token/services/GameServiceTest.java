package com.game.token.services;

import com.game.token.entities.Game;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameServiceTest {
    @Autowired
    private GameService gameService;

    @Test
    public void tokenShouldBeOnSquareOneWhenGameIsStarted() {
        Game game = gameService.start();

        assertNotNull(game.getToken());
        assertEquals(1, game.getToken().getPosition());
    }
}