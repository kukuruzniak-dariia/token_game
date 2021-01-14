package com.game.token.services;

import com.game.token.entities.Game;

import com.game.token.entities.Status;
import com.game.token.entities.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GameServiceTest {
    private static final int FIRST_SQUARE = 1;
    private static final int LAST_SQUARE = 100;
    @Autowired
    private GameService gameService;

    @Test
    public void tokenShouldBeOnSquareOneWhenGameIsStarted() {
        Game game = gameService.start();

        assertNotNull(game.getToken());
        assertEquals(Status.IN_PROGRESS, game.getStatus());
        assertEquals(FIRST_SQUARE, game.getToken().getPosition());
    }

    @Test
    public void tokenShouldBeOnSquareFourWhenWasMovedOnThreeSpaces() {
        Game game = new Game(new Token(FIRST_SQUARE));

        gameService.moveToken(game, 3);

        assertEquals(4, game.getToken().getPosition());
    }

    @Test
    public void tokenShouldBeOnSquareEightWhenWasMovedOnThreeAndFourSpaces() {
        Game game = new Game(new Token(FIRST_SQUARE));

        gameService.moveToken(game, 3);
        gameService.moveToken(game, 4);

        assertEquals(8, game.getToken().getPosition());
    }

    @Test
    public void playerShouldWinTheGameIfTokenOnLastSquare() {
        Game game = new Game(new Token(97));

        gameService.moveToken(game, 3);

        assertEquals(LAST_SQUARE, game.getToken().getPosition());
        assertEquals(Status.COMPLETED, game.getStatus());
    }

    @Test
    public void playerShouldNotWinTheGameIfTokenPositionGreaterThanAllowed() {
        int spaces = 4;
        int position = LAST_SQUARE - spaces + 1;
        Game game = new Game(new Token(position));

        gameService.moveToken(game, spaces);

        assertEquals(position, game.getToken().getPosition());
        assertNotEquals(Status.COMPLETED, game.getStatus());
    }

    @Test
    public void shouldReturnNumberBetweenOneAndSixWhenRollADie() {
        int min = 1;
        int max = 6;

        IntStream.range(0,100).forEach(value -> {
            int number = gameService.rollDie();
            assertTrue(number >= min);
            assertTrue(number <= max);
        });
    }
}