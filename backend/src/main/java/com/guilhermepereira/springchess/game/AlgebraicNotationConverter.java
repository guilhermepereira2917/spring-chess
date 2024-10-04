package com.guilhermepereira.springchess.game;

import java.util.List;

public abstract class AlgebraicNotationConverter {

	public static Move convertAlgebraicMove(Board board, String algebraicMove) {
		if (isPawnMovement(algebraicMove)) {
			if (!isPawnCaptureMovement(algebraicMove)) {
				Square targetSquare = board.getSquare(algebraicMove.substring(0, 2));
				List<Piece> pawns = board.getCurrentSidePieces(PieceType.PAWN);
				for (Piece pawn : pawns) {
					if (pawn.getValidMovementSquares().contains(targetSquare)) {
						return new Move(pawn.getSquare(), targetSquare);
					}
				}
			} else {
				int pawnColumn = convertAlgebraicColumn(algebraicMove.charAt(0));
				Square targetSquare = board.getSquare(algebraicMove.substring(2, 4));
				List<Piece> pawns = board.getCurrentSidePieces(PieceType.PAWN);
				for (Piece pawn : pawns) {
					if (pawn.getColumn() == pawnColumn && pawn.getValidMovementSquares().contains(targetSquare)) {
						return new Move(pawn.getSquare(), targetSquare);
					}
				}
			}
		}

		return null;
	}

	private static boolean isPawnMovement(String algebraicNotation) {
		return Character.isLowerCase(algebraicNotation.charAt(0));
	}

	private static boolean isPawnCaptureMovement(String algebraicNotation) {
		return algebraicNotation.length() >= 4 && algebraicNotation.charAt(1) == 'x';
	}

	public static int[] convertAlgebraicCoordinate(String algebraicCoordinate) {
		return new int[]{convertAlgebraicRow(algebraicCoordinate), convertAlgebraicColumn(algebraicCoordinate)};
	}

	private static int convertAlgebraicRow(String coordinate) {
		return 8 - Character.getNumericValue(coordinate.charAt(1));
	}

	private static int convertAlgebraicColumn(String algebraicCoordinate) {
		return convertAlgebraicColumn(algebraicCoordinate.charAt(0));
	}

	private static int convertAlgebraicColumn(Character algebraicColumn) {
		return algebraicColumn - 'a';
	}
}
