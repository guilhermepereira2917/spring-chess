package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.moves.Move;
import com.guilhermepereira.springchess.game.moves.PromotionMove;

import java.util.List;

public abstract class AlgebraicNotationConverter {

	public static Move convertAlgebraicMove(Board board, String algebraicMove) {
		if (isPawnMovement(algebraicMove)) {
			if (!isCaptureMovement(algebraicMove)) {
				Square targetSquare = board.getSquare(algebraicMove.substring(0, 2));
				List<Piece> pawns = board.getAllCurrentSidePiecesOfType(PieceType.PAWN);
				for (Piece pawn : pawns) {
					if (pawn.canMakeMove(targetSquare)) {
						if (isPromotionMovement(algebraicMove)) {
							char pieceSymbol = algebraicMove.charAt(algebraicMove.length() - 1);
							return new PromotionMove(pawn.getSquare(), targetSquare, PieceType.getPieceTypeFromSymbol(pieceSymbol));
						} else {
							return new Move(pawn.getSquare(), targetSquare);
						}
					}
				}
			} else {
				int pawnColumn = convertAlgebraicColumn(algebraicMove.charAt(0));
				Square targetSquare = board.getSquare(algebraicMove.substring(2, 4));
				List<Piece> pawns = board.getAllCurrentSidePiecesOfType(PieceType.PAWN);
				for (Piece pawn : pawns) {
					if (pawn.getColumn() == pawnColumn && pawn.canMakeMove(targetSquare)) {
						if (isPromotionMovement(algebraicMove)) {
							char pieceSymbol = algebraicMove.charAt(algebraicMove.length() - 1);
							return new PromotionMove(pawn.getSquare(), targetSquare, PieceType.getPieceTypeFromSymbol(pieceSymbol));
						} else {
							return new Move(pawn.getSquare(), targetSquare);
						}
					}
				}
			}
		} else if (isPieceMovement(algebraicMove)) {
			algebraicMove = algebraicMove.replace("x", "");

			Integer disambiguationRow = null;
			if (hasDisambiguationRow(algebraicMove)) {
				disambiguationRow = convertAlgebraicRow(algebraicMove.charAt(1));
			}

			Integer disambiguationColumn = null;
			if (hasDisambiguationColumn(algebraicMove)) {
				disambiguationColumn = convertAlgebraicColumn(algebraicMove.charAt(1));
			}

			Square targetSquare;
			if (!hasDisambiguation(algebraicMove)) {
				targetSquare = board.getSquare(algebraicMove.substring(1, 3));
			} else {
				targetSquare = board.getSquare(algebraicMove.substring(2, 4));
			}

			PieceType pieceType = PieceType.getPieceTypeFromSymbol(algebraicMove.charAt(0));
			List<Piece> pieces = board.getAllCurrentSidePiecesOfType(pieceType);
			for (Piece piece : pieces) {
				if (disambiguationRow != null && piece.getRow() != disambiguationRow) {
					continue;
				}

				if (disambiguationColumn != null && piece.getColumn() != disambiguationColumn) {
					continue;
				}

				if (piece.canMakeMove(targetSquare)) {
					return new Move(piece.getSquare(), targetSquare);
				}
			}
		} else if (isCastlingMovement(algebraicMove)) {
			if (board.isWhiteTurn()) {
				if (isKingsideCastling(algebraicMove)) {
					return new Move(board.getSquare("e1"), board.getSquare("g1"));
				}

				return new Move(board.getSquare("e1"), board.getSquare("b1"));
			}

			if (isKingsideCastling(algebraicMove)) {
				return new Move(board.getSquare("e8"), board.getSquare("g8"));
			}

			return new Move(board.getSquare("e8"), board.getSquare("b8"));
		}

		return null;
	}

	private static boolean isPawnMovement(String algebraicNotation) {
		return Character.isLowerCase(algebraicNotation.charAt(0));
	}

	private static boolean isPieceMovement(String algebraicNotation) {
		return Character.isUpperCase(algebraicNotation.charAt(0));
	}

	private static boolean isCastlingMovement(String algebraicNotation) {
		return Character.isDigit(algebraicNotation.charAt(0));
	}

	private static boolean isKingsideCastling(String algebraicNotation) {
		return algebraicNotation.length() == 3;
	}

	private static boolean isCaptureMovement(String algebraicNotation) {
		return algebraicNotation.charAt(1) == 'x';
	}

	private static boolean isPromotionMovement(String algebraicNotation) {
		return algebraicNotation.charAt(algebraicNotation.length() - 2) == '=';
	}

	private static boolean hasDisambiguationRow(String algebraicNotation) {
		return hasDisambiguation(algebraicNotation) && Character.isDigit(algebraicNotation.charAt(1));
	}

	private static boolean hasDisambiguationColumn(String algebraicNotation) {
		return hasDisambiguation(algebraicNotation) && !Character.isDigit(algebraicNotation.charAt(1));
	}

	private static boolean hasDisambiguation(String algebraicNotation) {
		return algebraicNotation.length() >= 4;
	}

	public static int[] convertAlgebraicCoordinate(String algebraicCoordinate) {
		return new int[]{convertAlgebraicRow(algebraicCoordinate), convertAlgebraicColumn(algebraicCoordinate)};
	}

	private static int convertAlgebraicRow(String algebraicCoordinate) {
		return convertAlgebraicRow(algebraicCoordinate.charAt(1));
	}

	private static int convertAlgebraicRow(Character algebraicRow) {
		return 8 - Character.getNumericValue(algebraicRow);
	}

	private static int convertAlgebraicColumn(String algebraicCoordinate) {
		return convertAlgebraicColumn(algebraicCoordinate.charAt(0));
	}

	private static int convertAlgebraicColumn(Character algebraicColumn) {
		return algebraicColumn - 'a';
	}
}
