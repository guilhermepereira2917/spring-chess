package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.PieceSide;
import com.guilhermepereira.springchess.game.PieceType;

public class Rook extends Piece {

	public Rook(PieceSide side) {
		super(PieceType.ROOK, side);
	}
}
