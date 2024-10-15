package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CastlingMoveTest {

	@Test
	@DisplayName("Testing if castling move undo is working properly")
	public void shouldUndoMoveCorrectly() {
		Board board = new Board();
		board.initialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK2R w KQkq - 0 1");

		assertTrue(board.playMove("0-0"));
		assertTrue(board.undoLastMove());

		assertTrue(board.getSquare("e1").getPiece().isKing());
		assertTrue(board.getSquare("e1").getPiece().isWhite());
		assertTrue(board.getSquare("h1").getPiece().isRook());
		assertTrue(board.getSquare("h1").getPiece().isWhite());

		assertTrue(board.playMove("0-0"));
	}
}