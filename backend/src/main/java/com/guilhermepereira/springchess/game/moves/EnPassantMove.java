package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Square;

public class EnPassantMove extends Move {

	private final Square pawnCaptureSquare;

	public EnPassantMove(Square originalSquare, Square targetSquare, Square pawnCaptureSquare) {
		super(originalSquare, targetSquare, MoveType.EN_PASSANT);
		this.pawnCaptureSquare = pawnCaptureSquare;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);
		board.capturePiece(pawnCaptureSquare.getPiece());
	}
}
