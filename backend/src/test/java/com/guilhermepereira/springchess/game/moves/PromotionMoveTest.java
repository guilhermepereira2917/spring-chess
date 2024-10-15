package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PromotionMoveTest {

	@Test
	@DisplayName("Testing if promotion move undo is working properly")
	public void shouldUndoMoveCorrectly() {
		Board board = new Board();
		board.initialize();

		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("d4"));
		assertTrue(board.playMove("e6"));
		assertTrue(board.playMove("d3"));
		assertTrue(board.playMove("exf7+"));
		assertTrue(board.playMove("Kd7"));
		assertTrue(board.playMove("fxg8=Q"));
		assertTrue(board.undoLastMove());

		assertTrue(board.getSquare("f7").getPiece().isPawn());
		assertTrue(board.getSquare("f7").getPiece().isWhite());
		assertTrue(board.getSquare("g8").getPiece().isKnight());
		assertTrue(board.getSquare("g8").getPiece().isBlack());
	}
}