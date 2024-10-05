package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.List;

public class Rook extends Piece {

	public Rook(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.ROOK, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		return getHorizontallyAndVerticallyValidMovementSquares();
	}
}
