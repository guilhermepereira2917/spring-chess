package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.pieces.*;

public abstract class PieceFactory {

	public static Piece createPieceFromPieceType(Board board, Square square, char pieceSymbol) {
		PieceSide side = Character.isUpperCase(pieceSymbol) ? PieceSide.WHITE : PieceSide.BLACK;
		PieceType type = PieceType.getPieceTypeFromSymbol(Character.toUpperCase(pieceSymbol));

		return createPieceFromPieceType(board, square, side, type);
	}

	public static Piece createPieceFromPieceType(Board board, Square square, PieceSide side, PieceType type) {
		switch (type) {
			case PAWN -> {
				return new Pawn(board, square, side);
			}
			case KNIGHT -> {
				return new Knight(board, square, side);
			}
			case BISHOP -> {
				return new Bishop(board, square, side);
			}
			case ROOK -> {
				return new Rook(board, square, side);
			}
			case QUEEN -> {
				return new Queen(board, square, side);
			}
			case KING -> {
				return new King(board, square, side);
			}
		}

		return null;
	}
}
