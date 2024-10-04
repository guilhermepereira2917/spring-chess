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
	protected boolean hasMoved = false;

	public Piece(Board board, Square square, PieceType type, PieceSide side) {
		this.board = board;
		this.square = square;
		this.type = type;
		this.side = side;
	}

	protected abstract List<Square> getValidMovementSquares();

	public boolean isPawn() {
		return type == PieceType.PAWN;
	}

	public boolean isKnight() {
		return type == PieceType.KNIGHT;
	}

	public boolean isBishop() {
		return type == PieceType.BISHOP;
	}

	public boolean isRook() {
		return type == PieceType.ROOK;
	}

	public boolean isQueen() {
		return type == PieceType.QUEEN;
	}

	public boolean isKing() {
		return type == PieceType.KING;
	}

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

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
