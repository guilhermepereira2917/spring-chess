package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if knight can move normally in a L shape")
	public void shouldMoveNormally() {
		assertTrue(board.playMove("Nf3"));

		assertTrue(board.getSquare("f3").getPiece().isWhite());
		assertTrue(board.getSquare("f3").getPiece().isKnight());
		assertTrue(board.getSquare("g1").isEmpty());
	}

	@Test
	@DisplayName("Testing if knight can capture enemy pieces")
	public void shouldCaptureEnemyPiece() {
		assertTrue(board.playMove("Nf3"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Nxe5"));

		assertTrue(board.getSquare("e5").getPiece().isWhite());
		assertTrue(board.getSquare("e5").getPiece().isKnight());
		assertTrue(board.getSquare("g1").isEmpty());
	}

	@Test
	@DisplayName("Testing if knight can't do invalid move")
	public void shouldNotDoInvalidMove() {
		assertFalse(board.playMove("Ng3"));

		assertTrue(board.getSquare("g1").getPiece().isWhite());
		assertTrue(board.getSquare("g1").getPiece().isKnight());
		assertTrue(board.getSquare("g3").isEmpty());
	}

	@Test
	@DisplayName("Testing if knight can't capture ally pieces")
	public void shouldCaptureAllPieces() {
		assertFalse(board.playMove("Ne2"));

		assertTrue(board.getSquare("g1").getPiece().isWhite());
		assertTrue(board.getSquare("g1").getPiece().isKnight());
		assertFalse(board.getSquare("e2").isEmpty());
	}
}