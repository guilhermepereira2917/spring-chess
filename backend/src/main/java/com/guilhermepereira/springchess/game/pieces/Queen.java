package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.List;

public class Queen extends Piece {

	public Queen(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.QUEEN, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		return List.of();
	}
}
