package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RookTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if rook can move normally")
	public void shouldMoveNormally() {
		assertTrue(board.playMove("a4"));
		assertTrue(board.playMove("a5"));
		assertTrue(board.playMove("Ra3"));

		assertTrue(board.getSquare("a3").getPiece().isRook());
		assertTrue(board.getSquare("a3").getPiece().isWhite());
		assertTrue(board.getSquare("a1").isEmpty());
	}

	@Test
	@DisplayName("Testing if rook can't do invalid move")
	public void shouldNotDoInvalidMove() {
		assertTrue(board.playMove("a4"));
		assertTrue(board.playMove("e5"));
		assertFalse(board.playMove("Ra5"));

		assertTrue(board.getSquare("a5").isEmpty());
		assertTrue(board.getSquare("a1").getPiece().isRook());
		assertTrue(board.getSquare("a1").getPiece().isWhite());
	}
}