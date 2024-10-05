package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if bishop can move normally")
	public void shouldMoveNormally() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Bc4"));

		assertTrue(board.getSquare("c4").getPiece().isBishop());
		assertTrue(board.getSquare("c4").getPiece().isWhite());
		assertTrue(board.getSquare("f1").isEmpty());
	}

	@Test
	@DisplayName("Testing if bishop can't do invalid move")
	public void shouldNotDoInvalidMove() {
		assertFalse(board.playMove("Bc4"));

		assertTrue(board.getSquare("f1").getPiece().isBishop());
		assertTrue(board.getSquare("f1").getPiece().isWhite());
		assertTrue(board.getSquare("c4").isEmpty());
	}
}