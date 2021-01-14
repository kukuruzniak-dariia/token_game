package com.game.token.controllers;

import com.game.token.entities.Status;
import com.game.token.services.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Status.IN_PROGRESS.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token.position").value(1))
                .andExpect(status().isOk())
                .andDo(print());
    }
}