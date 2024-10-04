package com.guilhermepereira.springchess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

	@Test
	@DisplayName("Testing board pieces position with FEN string initialization")
	public void shouldProperlyInitializeBoard() {
		Board board = new Board();
		board.initialize();

		PieceType[] pieceTypesOrder = new PieceType[] {
			PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
			PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK,
		};

		for (int i = 1; i <= 8; i++) {
			String column = Character.toString((char) ('a' + i - 1));

			Piece whitePiece = board.getSquare(column + "1").getPiece();
			assertTrue(whitePiece.isWhite());
			assertEquals(pieceTypesOrder[i - 1], whitePiece.getType());

			Piece whitePawn = board.getSquare(column + "2").getPiece();
			assertTrue(whitePawn.isWhite());
			assertTrue(whitePawn.isPawn());

			assertTrue(board.getSquare(column + "3").isEmpty());
			assertTrue(board.getSquare(column + "4").isEmpty());
			assertTrue(board.getSquare(column + "5").isEmpty());
			assertTrue(board.getSquare(column + "6").isEmpty());

			Piece blackPawn = board.getSquare(column + "7").getPiece();
			assertTrue(blackPawn.isBlack());
			assertTrue(blackPawn.isPawn());

			Piece blackPiece = board.getSquare(column + "8").getPiece();
			assertTrue(blackPiece.isBlack());
			assertEquals(pieceTypesOrder[i - 1], blackPiece.getType());
		}
	}
}