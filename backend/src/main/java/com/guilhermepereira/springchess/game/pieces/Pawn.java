package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.PieceSide;
import com.guilhermepereira.springchess.game.PieceType;

public class Pawn extends Piece {

	public Pawn(PieceSide side) {
		super(PieceType.PAWN, side);
	}
}
