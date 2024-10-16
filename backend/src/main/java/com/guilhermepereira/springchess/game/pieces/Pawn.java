package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;
import com.guilhermepereira.springchess.game.moves.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Pawn extends Piece {

	public Pawn(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.PAWN, side);
	}

	@Override
	protected List<? extends Move> getValidMoves() {
		return Stream.concat(getNormalMoves().stream(), getCaptureMoves().stream()).toList();
	}

	private List<Move> getNormalMoves() {
		List<Move> moves = new ArrayList<>();

		int[] direction = getDirection();

		int row = square.getRow();
		int column = square.getColumn();

		int numberOfSquares = hasMoved ? 1 : 2;

		for (int i = 0; i < numberOfSquares; i++) {
			row += direction[0];
			column += direction[1];

			Square candidateSquare = board.getSquare(row, column);
			if (!candidateSquare.isEmpty()) {
				break;
			}

			if (isPromotionMove(row)) {
				moves.addAll(createPromotionMoves(candidateSquare));
			} else if (i == 0) {
				moves.add(createMove(candidateSquare));
			} else {
				moves.add(createDoubleSquareMove(candidateSquare, board.getSquare(row - direction[0], column)));
			}
		}

		return moves;
	}

	private List<Move> getCaptureMoves() {
		List<Move> captureMoves = new ArrayList<>();

		int[] direction = getDirection();

		Square leftSquare = board.getSquare(square.getRow() + direction[0], square.getColumn() - 1);
		Square rightSquare = board.getSquare(square.getRow() + direction[0], square.getColumn() + 1);

		for (Square square : Stream.of(leftSquare, rightSquare).filter(Objects::nonNull).toList()) {
			if (square != null) {
				if (square.hasEnemyPiece(side)) {
					if (isPromotionMove(square.getRow())) {
						captureMoves.addAll(createPromotionMoves(square));
					} else {
						captureMoves.add(createMove(square));
					}
				} else if (square.equals(board.getEnPassantSquare())) {
					captureMoves.add(createEnPassantMove(square, board.getSquare(square.getRow() - direction[0], square.getColumn())));
				}
			}
		}

		return captureMoves;
	}

	@Override
	public Move getMove(Move move) {
		if (move.getType() != MoveType.PROMOTION) {
			return super.getMove(move);
		}

		PromotionMove promotionMove = (PromotionMove) move;
		return getValidMoves().stream().filter(m -> m.equals(promotionMove)).findFirst().orElse(null);
	}

	private boolean isPromotionMove(int row) {
		return row == 0 || row == board.getSquares().length - 1;
	}

	private List<PromotionMove> createPromotionMoves(Square targetSquare) {
		List<PromotionMove> promotionMoves = new ArrayList<>();

		for (PieceType pieceType : List.of(PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT)) {
			promotionMoves.add(new PromotionMove(square, targetSquare, pieceType));
		}

		return promotionMoves;
	}

	private PawnDoubleSquareMove createDoubleSquareMove(Square targetSquare, Square enPassantSquare) {
		return new PawnDoubleSquareMove(square, targetSquare, enPassantSquare);
	}

	private EnPassantMove createEnPassantMove(Square targetSquare, Square enemyPawnSquare) {
		return new EnPassantMove(square, targetSquare, enemyPawnSquare);
	}

	private int[] getDirection() {
		return isWhite() ? new int[]{-1, 0} : new int[]{1, 0};
	}
}
