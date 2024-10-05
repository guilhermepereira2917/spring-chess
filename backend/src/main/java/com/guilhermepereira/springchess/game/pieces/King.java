package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.List;
import java.util.stream.Stream;

public class King extends Piece {

	public King(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.KING, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		return Stream.concat(getDiagonallyValidMovementSquares(1).stream(), getHorizontallyAndVerticallyValidMovementSquares(1).stream()).toList();
	}
}
