package com.guilhermepereira.springchess.game;

public class Square {

	private final int row;
	private final int column;
	private Piece piece;

	public Square(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public boolean hasEnemyPiece(PieceSide side) {
		return !isEmpty() && piece.getSide() != side;
	}

	public boolean isEmpty() {
		return piece == null;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
