package com.guilhermepereira.springchess.game;

public abstract class Piece {

	protected final PieceType type;
	protected final PieceSide side;

	protected Piece(PieceType type, PieceSide side) {
		this.type = type;
		this.side = side;
	}

	public PieceType getType() {
		return type;
	}

	public PieceSide getSide() {
		return side;
	}
}
