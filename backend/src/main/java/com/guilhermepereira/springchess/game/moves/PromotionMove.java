package com.guilhermepereira.springchess.game.moves;

import com.guilhermepereira.springchess.game.*;

public class PromotionMove extends Move {

	private final PieceType piecePromotionType;

	public PromotionMove(Square originalSquare, Square targetSquare, PieceType piecePromotionType) {
		super(originalSquare, targetSquare, MoveType.PROMOTION);
		this.piecePromotionType = piecePromotionType;
	}

	@Override
	public void execute(Board board) {
		super.execute(board);

		Piece promotionPawn = getTargetSquare().getPiece();
		board.removePiece(getTargetSquare().getPiece());

		Piece newPiece = PieceFactory.createPieceFromPieceType(board, targetSquare, promotionPawn.getSide(), piecePromotionType);
		board.addPiece(newPiece);

		targetSquare.setPiece(newPiece);
		promotionPawn.setSquare(null);
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
