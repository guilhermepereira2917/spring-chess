package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Square;

public class PawnDoubleSquareMove extends Move {
	private final Square enPassantSquare;

	public PawnDoubleSquareMove(Square originalSquare, Square targetSquare, Square enPassantSquare) {
		super(originalSquare, targetSquare, MoveType.PAWN_DOUBLE_SQUARE);
		this.enPassantSquare = enPassantSquare;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);
		board.setEnPassantSquare(enPassantSquare);
	}
}
