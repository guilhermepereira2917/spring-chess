package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.PieceSide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnDoubleSquareMoveTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing if pawn double square move undo is working properly")
	public void shouldUndoMoveCorrectly() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.undoLastMove());

		assertEquals(PieceSide.WHITE, board.getTurnSide());
		assertTrue(board.getSquare("e2").getPiece().isWhite());
		assertTrue(board.getSquare("e2").getPiece().isPawn());
		assertNull(board.getEnPassantSquare());

		assertTrue(board.playMove("e4"));
		assertTrue(board.getSquare("e4").getPiece().isWhite());
		assertTrue(board.getSquare("e4").getPiece().isPawn());
	}

	@Test
	@DisplayName("Testing if pawn double square move undo is working properly on the second turn")
	public void shouldUndoMoveCorrectlyOnSecondTurn() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.undoLastMove());

		assertEquals(PieceSide.BLACK, board.getTurnSide());
		assertTrue(board.getSquare("e7").getPiece().isBlack());
		assertTrue(board.getSquare("e7").getPiece().isPawn());
		assertEquals(board.getSquare("e3"), board.getEnPassantSquare());
	}
}