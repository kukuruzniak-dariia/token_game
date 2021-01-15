package com.game.token.controllers;

import com.game.token.entities.Game;
import com.game.token.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    @Resource
    private GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<Game> start() {
        Game game = gameService.start();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping("/move-token")
    public ResponseEntity<Game> moveToken(@PathParam("gameId") int gameId, @PathParam("spaces") int spaces) {
        return new ResponseEntity<>(gameService.moveToken(gameId, spaces), HttpStatus.OK);
    }

    @GetMapping("/roll-a-die")
    public ResponseEntity<String> rollDie() {
        return new ResponseEntity<>(String.valueOf(gameService.rollDie()), HttpStatus.OK);
    }
}
