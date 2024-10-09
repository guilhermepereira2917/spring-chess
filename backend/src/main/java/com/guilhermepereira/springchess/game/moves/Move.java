package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Square;

public class Move {
	private final Square originalSquare;
	private final Square targetSquare;
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
}
