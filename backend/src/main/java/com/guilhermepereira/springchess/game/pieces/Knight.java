package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	public Knight(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.KNIGHT, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		List<Square> validMovementSquares = new ArrayList<>();

		int[][] directions = new int[][] {
			{2, 1},
			{1, 2},
			{2, -1},
			{1, -2},
			{-2, 1},
			{-1, 2},
			{-2, -1},
			{-1, -2},
		};

		for (int[] direction : directions) {
			Square candidateSquare = board.getSquare(this.square.getRow() + direction[0], this.square.getColumn() + direction[1]);
			if (candidateSquare != null && !candidateSquare.hasAllyPiece(side)) {
				validMovementSquares.add(candidateSquare);
			}
		}

		return validMovementSquares;
	}
}
