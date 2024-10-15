package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnPassantMoveTest {

	@Test
	@DisplayName("Testing if en passant move undo is working properly")
	public void shouldUndoMoveCorrectly() {
		Board board = new Board();
		board.initialize();

		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("d5"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("f5"));
		assertTrue(board.playMove("exf6"));
		assertTrue(board.undoLastMove());

		assertTrue(board.getSquare("e5").getPiece().isWhite());
		assertTrue(board.getSquare("e5").getPiece().isPawn());
		assertTrue(board.getSquare("f5").getPiece().isBlack());
		assertTrue(board.getSquare("f5").getPiece().isPawn());
		assertTrue(board.getSquare("f6").isEmpty());
	}
}