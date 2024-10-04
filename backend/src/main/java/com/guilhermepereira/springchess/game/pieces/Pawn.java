package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Pawn extends Piece {

	public Pawn(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.PAWN, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		return Stream.concat(getNormalMovementSquares().stream(), getCaptureMovementSquares().stream()).toList();
	}

	private List<Square> getNormalMovementSquares() {
		List<Square> normalMovementSquares = new ArrayList<>();

		int[] direction = getDirection();

		int row = square.getRow();
		int column = square.getColumn();

		int numberOfSquares = hasMoved ? 1 : 2;

		for (int i = 0; i < numberOfSquares; i++) {
			row += direction[0];
			column += direction[1];

			Square candidateSquare = board.getSquare(row, column);
			if (candidateSquare.isEmpty()) {
				normalMovementSquares.add(candidateSquare);
			}
		}

		return normalMovementSquares;
	}

	private List<Square> getCaptureMovementSquares() {
		List<Square> captureMovementSquares = new ArrayList<>();

		int[] direction = getDirection();

		Square leftSquare = board.getSquare(square.getRow() + direction[0], square.getColumn() - 1);
		Square rightSquare = board.getSquare(square.getRow() + direction[0], square.getColumn() + 1);

		for (Square square : Stream.of(leftSquare, rightSquare).filter(Objects::nonNull).toList()) {
			if (square != null && square.hasEnemyPiece(side)) {
				captureMovementSquares.add(square);
			}
		}

		return captureMovementSquares;
	}

	private int[] getDirection() {
		return isWhite() ? new int[]{-1, 0} : new int[]{1, 0};
	}
}
