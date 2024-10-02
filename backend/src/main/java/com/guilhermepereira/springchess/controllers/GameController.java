package com.guilhermepereira.springchess.controllers;


import com.guilhermepereira.springchess.game.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

	@GetMapping
	public ResponseEntity<Game> getGame() {
		Game game = new Game();
		game.initialize();

		return ResponseEntity.ok(game);
	}
}
