package com.game.token.controllers;

import com.game.token.entities.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/game")
public class GameController {

@GetMapping("/start")
    public ResponseEntity<Game> start() {
        return null;
    }
}
