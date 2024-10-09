package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Square;

public class CastlingMove extends Move {

	private final Square rookInitialSquare;
	private final Square rookTargetSquare;

	public CastlingMove(Square originalSquare, Square targetSquare, Square rookInitialSquare, Square rookTargetSquare) {
		super(originalSquare, targetSquare, MoveType.CASTLING);
		this.rookInitialSquare = rookInitialSquare;
		this.rookTargetSquare = rookTargetSquare;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);
		board.movePiece(rookInitialSquare.getPiece(), rookTargetSquare);
	}
}
