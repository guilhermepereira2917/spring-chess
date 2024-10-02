package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.PieceSide;
import com.guilhermepereira.springchess.game.PieceType;

public class Knight extends Piece {

	public Knight(PieceSide side) {
		super(PieceType.KNIGHT, side);
	}
}
