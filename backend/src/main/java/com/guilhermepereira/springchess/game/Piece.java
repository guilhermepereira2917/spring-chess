package com.guilhermepereira.springchess.game;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class Piece {

	@JsonIgnore
	protected final Board board;
	@JsonIgnore
	protected Square square;

	protected final PieceType type;
	protected final PieceSide side;

	public Piece(Board board, Square square, PieceType type, PieceSide side) {
		this.board = board;
		this.square = square;
		this.type = type;
		this.side = side;
	}

	protected abstract List<Square> getValidMovementSquares();

	public boolean isBlack() {
		return this.getSide() == PieceSide.BLACK;
	}

	public boolean isWhite() {
		return this.getSide() == PieceSide.WHITE;
	}

	public int getRow() {
		return square.getRow();
	}

	public int getColumn() {
		return square.getColumn();
	}

	public Board getBoard() {
		return board;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public PieceType getType() {
		return type;
	}

	public PieceSide getSide() {
		return side;
	}
}
