package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	public Pawn(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.PAWN, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		List<Square> validMovementSquares = new ArrayList<>();

		int[] direction = isWhite() ? new int[] {-1, 0} : new int[] {1, 0};

		int row = square.getRow();
		int column = square.getColumn();

		for (int i = 0; i < 2; i++) {
			row += direction[0];
			column += direction[1];

			Square candidateSquare = board.getSquare(row, column);
			if (candidateSquare.isEmpty()) {
				validMovementSquares.add(candidateSquare);
			}
		}

		return validMovementSquares;
	}
}
