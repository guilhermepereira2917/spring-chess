package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.Square;

public class Move {
	private final Square originalSquare;
	private final Square targetSquare;

	public Move(Square originalSquare, Square targetSquare) {
		this.originalSquare = originalSquare;
		this.targetSquare = targetSquare;
	}

	public void execute(Board board) {
		board.movePiece(originalSquare.getPiece(), targetSquare);
	}

	public Square getOriginalSquare() {
		return originalSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}
}
