package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.PieceSide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

	@Test
	@DisplayName("Testing if normal move undo is working properly")
	public void shouldUndoMoveCorrectly() {
		Board board = new Board();
		board.initialize();

		assertTrue(board.playMove("Nf3"));
		assertTrue(board.undoLastMove());

		assertEquals(PieceSide.WHITE, board.getTurnSide());
		assertTrue(board.getSquare("g1").getPiece().isWhite());
		assertTrue(board.getSquare("g1").getPiece().isKnight());
	}
}