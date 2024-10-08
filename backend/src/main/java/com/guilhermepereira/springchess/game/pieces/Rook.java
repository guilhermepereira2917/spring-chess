package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;
import com.guilhermepereira.springchess.game.moves.Move;

import java.util.List;

public class Rook extends Piece {

	public Rook(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.ROOK, side);
	}

	@Override
	protected List<? extends Move> getValidMoves() {
		return getHorizontallyAndVerticallyValidMovementSquares();
	}
}
