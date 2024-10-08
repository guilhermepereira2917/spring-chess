package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;
import com.guilhermepereira.springchess.game.moves.Move;

import java.util.List;

public class Bishop extends Piece {

	public Bishop(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.BISHOP, side);
	}

	@Override
	protected List<? extends Move> getValidMoves() {
		return getDiagonallyValidMovementSquares();
	}
}
