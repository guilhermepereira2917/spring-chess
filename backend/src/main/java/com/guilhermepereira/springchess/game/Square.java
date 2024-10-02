package com.guilhermepereira.springchess.game;

public class Square {

	private final int row;
	private final int column;
	private Piece piece;

	public Square(int row, int column) {
		this.row = row;
		this.column = column;
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
