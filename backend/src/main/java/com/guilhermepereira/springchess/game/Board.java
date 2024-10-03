package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.pieces.*;

import java.util.List;

public class Board {

	private static final String fenStringInitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private Square[][] squares = new Square[8][8];

	private PieceSide turnSide;

	public void initialize() {
		initialize(fenStringInitialPosition);
	}

	public void initialize(String fenString) {
		String[] stringInfo = fenString.split(" ");

		placePieces(stringInfo[0]);
		turnSide = stringInfo[1].equals("w") ? PieceSide.WHITE : PieceSide.BLACK;
	}

	private void placePieces(String piecesPositionString) {
		int position = 0;

		for (char character : piecesPositionString.toCharArray()) {
			if (character == '/') {
				continue;
			}

			if (!Character.isDigit(character)) {
				int row = position / 8;
				int column = position % 8;

				Square newSquare = new Square(row, column);
				newSquare.setPiece(createPiece(newSquare, character));

				squares[row][column] = newSquare;
				position++;
			} else {
				for (int i = 0; i < Character.getNumericValue(character); i++) {
					int row = position / 8;
					int column = position % 8;

					squares[row][column] = new Square(row, column);
					position++;
				}
			}
		}
	}

	private Piece createPiece(Square square, char character) {
		PieceSide side = Character.isUpperCase(character) ? PieceSide.WHITE : PieceSide.BLACK;

		if (character == 'r' || character == 'R') {
			return new Rook(this, square, side);
		}

		if (character == 'n' || character == 'N') {
			return new Knight(this, square, side);
		}

		if (character == 'b' || character == 'B') {
			return new Bishop(this, square, side);
		}

		if (character == 'q' || character == 'Q') {
			return new Queen(this, square, side);
		}

		if (character == 'k' || character == 'K') {
			return new King(this, square, side);
		}

		if (character == 'p' || character == 'P') {
			return new Pawn(this, square, side);
		}

		return null;
	}

	public boolean playMove(Move move) {
		if (move.getOriginalSquare().isEmpty()) {
			return false;
		}

		Piece piece = move.getOriginalSquare().getPiece();
		if (piece.getSide() != turnSide) {
			return false;
		}

		List<Square> validMovementSquares = piece.getValidMovementSquares();
		if (!validMovementSquares.contains(move.getTargetSquare())) {
			return false;
		}

		move.getOriginalSquare().setPiece(null);
		move.getTargetSquare().setPiece(piece);

		piece.setSquare(move.getTargetSquare());
		piece.setHasMoved(true);

		turnSide = turnSide == PieceSide.WHITE ? PieceSide.BLACK : PieceSide.WHITE;

		return true;
	}

	public Square getSquare(int row, int column) {
		if (row >= 0 && row < squares.length && column >= 0 && column < squares[0].length) {
			return squares[row][column];
		}

		return null;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}
}
