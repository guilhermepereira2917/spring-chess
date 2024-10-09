package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

	private final Board board = new Board();

	@Test
	@DisplayName("Testing if king can move normally")
	public void shouldMoveNormally() {
		board.initialize();

		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Ke2"));

		assertTrue(board.getSquare("e2").getPiece().isKing());
		assertTrue(board.getSquare("e2").getPiece().isWhite());
		assertTrue(board.getSquare("e1").isEmpty());
	}

	@Test
	@DisplayName("Testing if king can't do invalid move'")
	public void shouldNotDoInvalidMove() {
		board.initialize();

		assertTrue(board.playMove("e4"));
		assertTrue(board.playMove("e5"));
		assertFalse(board.playMove("Ke3"));

		assertTrue(board.getSquare("e3").isEmpty());
		assertTrue(board.getSquare("e1").getPiece().isKing());
		assertTrue(board.getSquare("e1").getPiece().isWhite());
	}

	@Test
	@DisplayName("Testing if white can castle kingside")
	public void shouldCastleWhiteKingside() {
		board.initialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK2R w KQkq - 0 1");
		assertTrue(board.playMove("0-0"));

		assertTrue(board.getSquare("g1").getPiece().isKing());
		assertTrue(board.getSquare("g1").getPiece().isWhite());
		assertTrue(board.getSquare("f1").getPiece().isRook());
		assertTrue(board.getSquare("f1").getPiece().isWhite());
		assertTrue(board.getSquare("e1").isEmpty());
		assertTrue(board.getSquare("h1").isEmpty());
	}

	@Test
	@DisplayName("Testing if white can castle queenside")
	public void shouldCastleWhiteQueenside() {
		board.initialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R3KBNR w KQkq - 0 1");
		assertTrue(board.playMove("0-0-0"));

		assertTrue(board.getSquare("b1").getPiece().isKing());
		assertTrue(board.getSquare("b1").getPiece().isWhite());
		assertTrue(board.getSquare("c1").getPiece().isRook());
		assertTrue(board.getSquare("c1").getPiece().isWhite());
		assertTrue(board.getSquare("e1").isEmpty());
		assertTrue(board.getSquare("a1").isEmpty());
	}

	@Test
	@DisplayName("Testing if black can castle kingside")
	public void shouldCastleBlackKingside() {
		board.initialize("rnbqk2r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1");
		assertTrue(board.playMove("0-0"));

		assertTrue(board.getSquare("g8").getPiece().isKing());
		assertTrue(board.getSquare("g8").getPiece().isBlack());
		assertTrue(board.getSquare("f8").getPiece().isRook());
		assertTrue(board.getSquare("f8").getPiece().isBlack());
		assertTrue(board.getSquare("e8").isEmpty());
		assertTrue(board.getSquare("h8").isEmpty());
	}

	@Test
	@DisplayName("Testing if black can castle queenside")
	public void shouldCastleBlackQueenside() {
		board.initialize("r3kbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1");
		assertTrue(board.playMove("0-0-0"));

		assertTrue(board.getSquare("b8").getPiece().isKing());
		assertTrue(board.getSquare("b8").getPiece().isBlack());
		assertTrue(board.getSquare("c8").getPiece().isRook());
		assertTrue(board.getSquare("c8").getPiece().isBlack());
		assertTrue(board.getSquare("e8").isEmpty());
		assertTrue(board.getSquare("a8").isEmpty());
	}

	@Test
	@DisplayName("Testing if can't castle if exists pieces in between")
	public void shouldNotCastleWithPiecesInBetween() {
		board.initialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		assertFalse(board.playMove("0-0"));

		assertTrue(board.getSquare("e1").getPiece().isKing());
		assertTrue(board.getSquare("e1").getPiece().isWhite());
		assertTrue(board.getSquare("h1").getPiece().isRook());
		assertTrue(board.getSquare("h1").getPiece().isWhite());
	}

	@Test
	@DisplayName("Testing if can't castle if king already moved")
	public void shouldNotCastleIfKingMoved() {
		board.initialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK2R w KQkq - 0 1");
		assertTrue(board.playMove("Kf1"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Ke1"));
		assertTrue(board.playMove("e4"));
		assertFalse(board.playMove("0-0"));

		assertTrue(board.getSquare("e1").getPiece().isKing());
		assertTrue(board.getSquare("e1").getPiece().isWhite());
		assertTrue(board.getSquare("h1").getPiece().isRook());
		assertTrue(board.getSquare("h1").getPiece().isWhite());
	}

	@Test
	@DisplayName("Testing if can't castle if rook already moved")
	public void shouldNotCastleIfRookMoved() {
		board.initialize("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQK2R w KQkq - 0 1");
		assertTrue(board.playMove("Rg1"));
		assertTrue(board.playMove("e5"));
		assertTrue(board.playMove("Rh1"));
		assertTrue(board.playMove("e4"));
		assertFalse(board.playMove("0-0"));

		assertTrue(board.getSquare("e1").getPiece().isKing());
		assertTrue(board.getSquare("e1").getPiece().isWhite());
		assertTrue(board.getSquare("h1").getPiece().isRook());
		assertTrue(board.getSquare("h1").getPiece().isWhite());
	}
}
