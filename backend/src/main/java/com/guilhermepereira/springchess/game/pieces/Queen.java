package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;

import java.util.List;
import java.util.stream.Stream;

public class Queen extends Piece {

	public Queen(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.QUEEN, side);
	}

	@Override
	protected List<Square> getValidMovementSquares() {
		return Stream.concat(getDiagonallyValidMovementSquares().stream(), getHorizontallyAndVerticallyValidMovementSquares().stream()).toList();
	}
}
