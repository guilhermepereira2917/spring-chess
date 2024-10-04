package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if pawn can move one square forward")
	public void shouldMoveOneSquare() {
		assertTrue(board.playMove("e3"));

		assertTrue(board.getSquare("e3").getPiece().isPawn());
		assertTrue(board.getSquare("e3").getPiece().isWhite());
		assertTrue(board.getSquare("e2").isEmpty());
	}

	@Test
	@DisplayName("Testing if pawn can move two squares forward when it hasn't moved yet")
	public void shouldMoveTwoSquares() {
		assertTrue(board.playMove("e4"));

		assertTrue(board.getSquare("e4").getPiece().isPawn());
		assertTrue(board.getSquare("e4").getPiece().isWhite());
		assertTrue(board.getSquare("e2").isEmpty());
	}

	@Test
	@DisplayName("Testing if pawn can't move when blocked")
	public void shouldNotMoveForwardWhenBlocked() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertFalse(board.playMove("e5"));

		assertTrue(board.getSquare("e4").getPiece().isPawn());
		assertTrue(board.getSquare("e4").getPiece().isWhite());
		assertTrue(board.getSquare("e2").isEmpty());
		assertTrue(board.getSquare("e5").getPiece().isPawn());
		assertTrue(board.getSquare("e5").getPiece().isBlack());
	}

	@Test
	@DisplayName("Testing if pawn can't move two squares forward when it already has moved")
	public void shouldNotMoveTwoSquares() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertFalse(board.playMove("e6"));
		assertTrue(board.playMove("e5"));

		assertTrue(board.getSquare("e5").getPiece().isPawn());
		assertTrue(board.getSquare("e5").getPiece().isWhite());
		assertTrue(board.getSquare("e2").isEmpty());
	}

	@Test
	@DisplayName("Testing if pawn can capture a piece diagonally")
	public void shouldCapturePieceDiagonally() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("exd5"));

		assertTrue(board.getSquare("d5").getPiece().isPawn());
		assertTrue(board.getSquare("d5").getPiece().isWhite());
		assertTrue(board.getSquare("e2").isEmpty());
		assertTrue(board.getSquare("d7").isEmpty());
	}
}