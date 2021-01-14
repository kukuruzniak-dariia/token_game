package com.game.token.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.token.entities.Game;
import com.game.token.entities.Status;
import com.game.token.entities.Token;
import com.game.token.services.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GameController.class)
class GameControllerTest {
    @Mock
    private GameService gameService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldStartGame() throws Exception {
        mockMvc.perform(get("/game/start")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(Status.IN_PROGRESS.toString()))
                .andExpect(jsonPath("$.token.position").value(1))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldMoveTokenOnThreeSpaces() throws Exception {
        Game game = new Game(new Token(GameService.INITIAL_TOKEN_POSITION));
        game.setStatus(Status.IN_PROGRESS);
        String json = new ObjectMapper().writeValueAsString(game);
        int spaces = 3;
        int expectedPosition = game.getToken().getPosition() + spaces;


        mockMvc.perform(put("/game/move-token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("spaces", String.valueOf(spaces))
                .content(json))
                .andExpect(jsonPath("$.status").value(Status.IN_PROGRESS.toString()))
                .andExpect(jsonPath("$.token.position").value(expectedPosition))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRollADie() throws Exception {
        mockMvc.perform(get("/game/roll-a-die")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(status().isOk())
                .andDo(print());
    }
}