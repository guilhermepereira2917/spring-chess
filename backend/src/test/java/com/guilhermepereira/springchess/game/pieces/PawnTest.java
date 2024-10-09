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

	@Test
	@DisplayName("Testing if pawn can't do invalid move")
	public void shouldNotDoInvalidMove() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertFalse(board.playMove("e3"));

		assertTrue(board.getSquare("e4").getPiece().isPawn());
		assertTrue(board.getSquare("e4").getPiece().isWhite());
		assertTrue(board.getSquare("e3").isEmpty());
	}

	@Test
	@DisplayName("Testing if pawn can do en passant")
	public void shouldDoEnPassant() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("f5"));
		assertTrue(board.playMove("exf6"));

		assertTrue(board.getSquare("f6").getPiece().isPawn());
		assertTrue(board.getSquare("f6").getPiece().isWhite());
		assertTrue(board.getSquare("f5").isEmpty());
		assertNull(board.getEnPassantSquare());
	}

	@Test
	@DisplayName("Testing if pawn can't do en passant when the other pawn has moved only one square")
	public void shouldNotDoEnPassant() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("d4"));
		assertTrue(board.playMove("e6"));
		assertTrue(board.playMove("f6"));
		assertFalse(board.playMove("exf7"));

		assertTrue(board.getSquare("e6").getPiece().isPawn());
		assertTrue(board.getSquare("e6").getPiece().isWhite());
		assertTrue(board.getSquare("f6").getPiece().isPawn());
		assertTrue(board.getSquare("f6").getPiece().isBlack());
		assertTrue(board.getSquare("f7").isEmpty());
	}

	@Test
	@DisplayName("Testing if pawn can promote to queen")
	public void shouldPromoteToQueen() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("d4"));
		assertTrue(board.playMove("e6"));
		assertTrue(board.playMove("d3"));
		assertTrue(board.playMove("exf7+"));
		assertTrue(board.playMove("Kd7"));
		assertTrue(board.playMove("fxg8=Q"));

		assertTrue(board.getSquare("g8").getPiece().isQueen());
		assertTrue(board.getSquare("g8").getPiece().isWhite());
	}

	@Test
	@DisplayName("Testing if pawn can promote to rook")
	public void shouldPromoteToRook() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("d4"));
		assertTrue(board.playMove("e6"));
		assertTrue(board.playMove("d3"));
		assertTrue(board.playMove("exf7+"));
		assertTrue(board.playMove("Kd7"));
		assertTrue(board.playMove("fxg8=R"));

		assertTrue(board.getSquare("g8").getPiece().isRook());
		assertTrue(board.getSquare("g8").getPiece().isWhite());
	}
}
