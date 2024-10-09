package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Square;

public class Move {
	protected final Square originalSquare;
	protected final Square targetSquare;
	private final MoveType type;

	public Move(Square originalSquare, Square targetSquare) {
		this(originalSquare, targetSquare, MoveType.NORMAL);
	}

	protected Move(Square originalSquare, Square targetSquare, MoveType type) {
		this.originalSquare = originalSquare;
		this.targetSquare = targetSquare;
		this.type = type;
	}

	public void execute(Board board) {
		board.movePiece(originalSquare.getPiece(), targetSquare);
	}

	public boolean isNormalMove() {
		return type == MoveType.NORMAL;
	}

	public boolean isCastlingMove() {
		return type == MoveType.CASTLING;
	}

	public boolean isPawnDoubleSquareMove() {
		return type == MoveType.PAWN_DOUBLE_SQUARE;
	}

	public boolean isEnPassantMove() {
		return type == MoveType.EN_PASSANT;
	}

	public Square getOriginalSquare() {
		return originalSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}

	public MoveType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Move move = (Move) o;
		return originalSquare.equals(move.originalSquare) && targetSquare.equals(move.targetSquare) && type == move.type;
	}

	@Override
	public int hashCode() {
		int result = originalSquare.hashCode();
		result = 31 * result + targetSquare.hashCode();
		result = 31 * result + type.hashCode();
		return result;
	}
}
