package com.game.token.controllers;

import com.game.token.entities.Game;
import com.game.token.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

    @PutMapping("/move-token")
    public ResponseEntity<Game> moveToken(@RequestBody Game game,  @PathParam("spaces") int spaces) {
        return new ResponseEntity<>(gameService.moveToken(game, spaces), HttpStatus.OK);
    }
}
