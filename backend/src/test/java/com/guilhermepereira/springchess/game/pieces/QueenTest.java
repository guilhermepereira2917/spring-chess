package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if queen can move normally")
	public void shouldMoveNormally() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Qg4"));

		assertTrue(board.getSquare("g4").getPiece().isQueen());
		assertTrue(board.getSquare("g4").getPiece().isWhite());
		assertTrue(board.getSquare("d1").isEmpty());
	}

	@Test
	@DisplayName("Testing if queen can't do invalid move")
	public void shouldNotDoInvalidMove() {
		assertFalse(board.playMove("Qg4"));

		assertTrue(board.getSquare("g4").isEmpty());
		assertTrue(board.getSquare("d1").getPiece().isQueen());
		assertTrue(board.getSquare("d1").getPiece().isWhite());
	}
}