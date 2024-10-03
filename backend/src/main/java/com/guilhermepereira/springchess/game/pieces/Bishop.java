package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.List;

public class Bishop extends Piece {

	public Bishop(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.BISHOP, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		return List.of();
	}
}
