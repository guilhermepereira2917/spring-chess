package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.PieceSide;
import com.guilhermepereira.springchess.game.PieceType;

public class Queen extends Piece {

	public Queen(PieceSide side) {
		super(PieceType.QUEEN, side);
	}
}
