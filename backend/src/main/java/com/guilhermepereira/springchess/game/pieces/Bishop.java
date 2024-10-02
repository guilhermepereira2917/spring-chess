package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.PieceSide;
import com.guilhermepereira.springchess.game.PieceType;

public class Bishop extends Piece {

	public Bishop(PieceSide side) {
		super(PieceType.BISHOP, side);
	}
}
