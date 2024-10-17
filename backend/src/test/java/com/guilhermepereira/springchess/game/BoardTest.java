package com.guilhermepereira.springchess.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

	private final Board board = new Board();

	@BeforeEach
	public void initializeBoard() {
		board.initialize();
	}

	@Test
	@DisplayName("Testing board pieces position with FEN string initialization")
	public void shouldProperlyInitializeBoard() {
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

	@Test
	@DisplayName("Testing algebraic notation move row disambiguation")
	public void shouldProperlyDisambiguateRow() {
		assertTrue(board.playMove("Nf3"));
		assertTrue(board.playMove("Nc6"));
		assertTrue(board.playMove("Nc3"));
		assertTrue(board.playMove("Nb8"));
		assertTrue(board.playMove("Ng5"));
		assertTrue(board.playMove("Nc6"));
		assertTrue(board.playMove("Ne6"));
		assertTrue(board.playMove("Nb8"));
		assertTrue(board.playMove("Nc5"));
		assertTrue(board.playMove("Nc6"));
		assertTrue(board.playMove("N3e4"));

		assertTrue(board.getSquare("e4").getPiece().isWhite());
		assertTrue(board.getSquare("e4").getPiece().isKnight());
		assertTrue(board.getSquare("c5").getPiece().isWhite());
		assertTrue(board.getSquare("c5").getPiece().isKnight());
	}

	@Test
	@DisplayName("Testing algebraic notation move column disambiguation")
	public void shouldProperlyDisambiguateColumn() {
		assertTrue(board.playMove("Nh3"));
		assertTrue(board.playMove("Nc6"));
		assertTrue(board.playMove("Nc3"));
		assertTrue(board.playMove("Nb8"));
		assertTrue(board.playMove("Nf4"));
		assertTrue(board.playMove("Nc6"));
		assertTrue(board.playMove("Ncd5"));

		assertTrue(board.getSquare("d5").getPiece().isWhite());
		assertTrue(board.getSquare("d5").getPiece().isKnight());
		assertTrue(board.getSquare("f4").getPiece().isWhite());
		assertTrue(board.getSquare("f4").getPiece().isKnight());
	}

	@Test
	@DisplayName("Testing if can't make move that would lead king being checked")
	public void shouldNotDoMoveThatWouldCheckKing() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Nc3"));
		assertTrue(board.playMove("Qh4"));
		assertFalse(board.playMove("f3"));
		assertFalse(board.playMove("f4"));

		assertTrue(board.getSquare("f2").getPiece().isWhite());
		assertTrue(board.getSquare("f2").getPiece().isPawn());
	}

	@Test
	@DisplayName("Testing if checkmate is finishing the game")
	public void shouldFinishTheGameOnCheckmate() {
		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("g5"));
		assertTrue(board.playMove("Nc3"));
		assertTrue(board.playMove("f5"));
		assertTrue(board.playMove("Qh5#"));

		assertEquals(GameResultEnum.WHITE_WINS, board.getGameResult());
	}
}