package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;
import com.guilhermepereira.springchess.game.moves.EnPassantMove;
import com.guilhermepereira.springchess.game.moves.Move;
import com.guilhermepereira.springchess.game.moves.PawnDoubleSquareMove;

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
		return Stream.concat(getNormalMovementSquares().stream(), getCaptureMovementSquares().stream()).toList();
	}

	private List<Move> getNormalMovementSquares() {
		List<Move> normalMovementSquares = new ArrayList<>();

		int[] direction = getDirection();

		int row = square.getRow();
		int column = square.getColumn();

		int numberOfSquares = hasMoved ? 1 : 2;

		for (int i = 0; i < numberOfSquares; i++) {
			row += direction[0];
			column += direction[1];

			Square candidateSquare = board.getSquare(row, column);
			if (candidateSquare.isEmpty()) {
				if (i == 0) {
					normalMovementSquares.add(createMove(candidateSquare));
				} else {
					normalMovementSquares.add(createDoubleSquareMove(candidateSquare, board.getSquare(row - direction[0], column)));
				}
			}
		}

		return normalMovementSquares;
	}

	private List<Move> getCaptureMovementSquares() {
		List<Move> captureMovementSquares = new ArrayList<>();

		int[] direction = getDirection();

		Square leftSquare = board.getSquare(square.getRow() + direction[0], square.getColumn() - 1);
		Square rightSquare = board.getSquare(square.getRow() + direction[0], square.getColumn() + 1);

		for (Square square : Stream.of(leftSquare, rightSquare).filter(Objects::nonNull).toList()) {
			if (square != null) {
				if (square.hasEnemyPiece(side)) {
					captureMovementSquares.add(createMove(square));
				} else if (square.equals(board.getEnPassantSquare())) {
					captureMovementSquares.add(createEnPassantMove(square, board.getSquare(square.getRow() - direction[0], square.getColumn())));
				}
			}
		}

		return captureMovementSquares;
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
