package com.guilhermepereira.springchess.game.pieces;

import com.guilhermepereira.springchess.game.*;
import com.guilhermepereira.springchess.game.moves.CastlingMove;
import com.guilhermepereira.springchess.game.moves.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class King extends Piece {

	public King(Board board, Square square, PieceSide side) {
		super(board, square, PieceType.KING, side);
	}

	@Override
	protected List<? extends Move> getValidMoves() {
		return Stream.of(
			getDiagonallyValidMovementSquares(1),
			getHorizontallyAndVerticallyValidMovementSquares(1),
			getValidCastlingMoves()).flatMap(Collection::stream).toList();
	}

	private List<CastlingMove> getValidCastlingMoves() {
		if (hasMoved) {
			return Collections.emptyList();
		}

		List<CastlingMove> castlingMoves = new ArrayList<>();

		int[][] directions = new int[][]{{0, 1}, {0, -1},};

		for (int[] direction : directions) {
			int row = getRow() + direction[0];
			int column = getColumn() + direction[1];

			Square square = board.getSquare(row, column);
			while (square != null) {
				if (!square.isEmpty()) {
					if (square.getPiece().isRook() && square.getPiece().getSide() == side && !square.getPiece().getHasMoved()) {
						int castlingSquareColumn = column - direction[1];
						Square targetSquare = board.getSquare(row, castlingSquareColumn);

						int rookCastlingColumn = column - direction[1] * 2;
						Square rookTargetSquare = board.getSquare(row, rookCastlingColumn);

						castlingMoves.add(createCastlingMove(targetSquare, square, rookTargetSquare));
					}

					break;
				}

				row += direction[0];
				column += direction[1];

				square = board.getSquare(row, column);
			}
		}

		return castlingMoves;
	}

	private CastlingMove createCastlingMove(Square targetSquare, Square rookInitialSquare, Square rookTargetSquare) {
		return new CastlingMove(square, targetSquare, rookInitialSquare, rookTargetSquare);
	}
}
