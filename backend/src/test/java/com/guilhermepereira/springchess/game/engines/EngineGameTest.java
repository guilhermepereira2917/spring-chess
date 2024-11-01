package com.guilhermepereira.springchess.game.engines;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.PieceSide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EngineGameTest {

	@Test
	@DisplayName("Testing if suggested move is being played by engine")
	public void shouldPlaySuggestedMove() {
		EngineGame engineGame = new EngineGame(new StockfishEngine());
		engineGame.initialize();

		Board board = engineGame.getBoard();

		assertTrue(engineGame.playMove("e4"));
		assertEquals(PieceSide.WHITE, board.getTurnSide());

		engineGame.finish();
	}
}