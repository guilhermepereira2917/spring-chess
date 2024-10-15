package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.*;

public class PromotionMove extends Move {

	private final PieceType piecePromotionType;
	private Piece promotedPawn;

	public PromotionMove(Square originalSquare, Square targetSquare, PieceType piecePromotionType) {
		super(originalSquare, targetSquare, MoveType.PROMOTION);
		this.piecePromotionType = piecePromotionType;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);

		promotedPawn = targetSquare.getPiece();
		board.removePiece(promotedPawn);

		Piece newPiece = PieceFactory.createPieceFromPieceType(board, targetSquare, promotedPawn.getSide(), piecePromotionType);
		board.addPiece(newPiece);

		targetSquare.setPiece(newPiece);
		promotedPawn.setSquare(null);
	}

	@Override
	public void undo(Board board) {
		Piece promotedPiece = targetSquare.getPiece();

		super.undo(board);

		board.removePiece(promotedPiece);
		promotedPiece.setSquare(null);

		board.placePiece(promotedPawn, originalSquare);
	}

	@Override
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		if (this == o) return true;
		if (getClass() != o.getClass()) return false;

		PromotionMove that = (PromotionMove) o;
		return piecePromotionType == that.piecePromotionType;
	}

	@Override
	public int hashCode() {
		return 31 * super.hashCode() + piecePromotionType.hashCode();
	}
}
