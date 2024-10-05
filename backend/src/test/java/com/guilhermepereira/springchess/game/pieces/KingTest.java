package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if king can move normally")
	public void shouldMoveNormally() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Ke2"));

		assertTrue(board.getSquare("e2").getPiece().isKing());
		assertTrue(board.getSquare("e2").getPiece().isWhite());
		assertTrue(board.getSquare("e1").isEmpty());
	}

	@Test
	@DisplayName("Testing if king can't do invalid move'")
	public void shouldNotDoInvalidMove() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertFalse(board.playMove("Ke3"));

		assertTrue(board.getSquare("e3").isEmpty());
		assertTrue(board.getSquare("e1").getPiece().isKing());
		assertTrue(board.getSquare("e1").getPiece().isWhite());
	}
}