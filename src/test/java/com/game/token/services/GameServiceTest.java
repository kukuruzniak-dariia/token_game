package com.game.token.services;

import com.game.token.entities.Game;
import com.game.token.entities.Status;
import com.game.token.entities.Token;
import com.game.token.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GameServiceTest {
    private static final int FIRST_SQUARE = 1;
    private static final int LAST_SQUARE = 100;
    @Mock
    private GameRepository gameRepository;
    private DefaultGameService gameService;

    @BeforeEach
    public void setUp() {
        gameService = new DefaultGameService();
        gameService.setGameRepository(gameRepository);
    }

    @Test
    public void tokenShouldBeOnSquareOneWhenGameIsStarted() {
        when(gameRepository.save(any(Game.class)))
                .thenReturn(new Game(1L, new Token(FIRST_SQUARE), Status.IN_PROGRESS));
        Game game = gameService.start();

        assertNotNull(game.getToken());
        assertEquals(Status.IN_PROGRESS, game.getStatus());
        assertEquals(FIRST_SQUARE, game.getToken().getPosition());
    }

    @Test
    public void tokenShouldBeOnSquareFourWhenWasMovedOnThreeSpaces() {
        Game game = new Game(1L, new Token(FIRST_SQUARE), Status.IN_PROGRESS);
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));

        gameService.moveToken(game.getId(), 3);

        assertEquals(4, game.getToken().getPosition());
    }

    @Test
    public void tokenShouldBeOnSquareEightWhenWasMovedOnThreeAndFourSpaces() {
        Game game = new Game(1L, new Token(FIRST_SQUARE), Status.IN_PROGRESS);
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));

        gameService.moveToken(game.getId(), 3);
        gameService.moveToken(game.getId(), 4);

        assertEquals(8, game.getToken().getPosition());
    }

    @Test
    public void playerShouldWinTheGameIfTokenOnLastSquare() {
        Game game = new Game(1L, new Token(97), Status.IN_PROGRESS);
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));


        gameService.moveToken(game.getId(), 3);

        assertEquals(LAST_SQUARE, game.getToken().getPosition());
        assertEquals(Status.COMPLETED, game.getStatus());
    }

    @Test
    public void playerShouldNotWinTheGameIfTokenPositionGreaterThanAllowed() {
        int spaces = 4;
        int position = LAST_SQUARE - spaces + 1;
        Game game = new Game(1L, new Token(position), Status.IN_PROGRESS);
        when(gameRepository.findById(game.getId())).thenReturn(Optional.of(game));


        gameService.moveToken(game.getId(), spaces);

        assertEquals(position, game.getToken().getPosition());
        assertNotEquals(Status.COMPLETED, game.getStatus());
    }

    @Test
    public void shouldReturnNumberBetweenOneAndSixWhenRollADie() {
        int min = 1;
        int max = 6;

        IntStream.range(0, 100).forEach(value -> {
            int number = gameService.rollDie();
            assertTrue(number >= min);
            assertTrue(number <= max);
        });
    }
}