package com.game.token.controllers;

import com.game.token.entities.Game;
import com.game.token.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<Game> start() {
        Game game = gameService.start();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
