package com.guilhermepereira.springchess.controllers;

import com.guilhermepereira.springchess.dtos.MoveDto;
import com.guilhermepereira.springchess.game.Game;
import com.guilhermepereira.springchess.game.moves.Move;
import com.guilhermepereira.springchess.game.Square;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
public class GameController {

	private final Game game = new Game();

	public GameController() {
		game.initialize();
	}

	@GetMapping
	public ResponseEntity<Game> getGame() {
		return ResponseEntity.ok(game);
	}

	@PostMapping
	public ResponseEntity<Game> playMove(@RequestBody MoveDto moveDto) {
		Square originalSquare = game.getBoard().getSquare(moveDto.originalRow(), moveDto.originalColumn());
		Square targetSquare = game.getBoard().getSquare(moveDto.targetRow(), moveDto.targetColumn());

		Move move = new Move(originalSquare, targetSquare);

		if (!game.playMove(move)) {
			return ResponseEntity.badRequest().body(game);
		}

		return ResponseEntity.ok(game);
	}
}
