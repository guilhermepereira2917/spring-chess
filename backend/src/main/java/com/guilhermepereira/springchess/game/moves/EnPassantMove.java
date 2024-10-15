package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.Piece;
import com.guilhermepereira.springchess.game.Square;

public class EnPassantMove extends Move {

	private final Square pawnCaptureSquare;
	private Piece capturedPawn;

	public EnPassantMove(Square originalSquare, Square targetSquare, Square pawnCaptureSquare) {
		super(originalSquare, targetSquare, MoveType.EN_PASSANT);
		this.pawnCaptureSquare = pawnCaptureSquare;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);
		capturedPawn = pawnCaptureSquare.getPiece();
		board.capturePiece(capturedPawn);
	}

	@Override
	public void undo(Board board) {
		super.undo(board);
		board.placePiece(capturedPawn, pawnCaptureSquare);
	}
}
