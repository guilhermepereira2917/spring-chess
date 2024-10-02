package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.pieces.*;

public class Board {

	private static final String fenStringInitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private Square[][] squares = new Square[8][8];

	public void initialize() {
		initialize(fenStringInitialPosition);
	}

	public void initialize(String fenString) {
		String[] stringInfo = fenString.split(" ");

		placePieces(stringInfo[0]);
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
				newSquare.setPiece(getPiece(character));

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

	private Piece getPiece(char character) {
		PieceSide side = Character.isUpperCase(character) ? PieceSide.WHITE : PieceSide.BLACK;

		if (character == 'r' || character == 'R') {
			return new Rook(side);
		}

		if (character == 'n' || character == 'N') {
			return new Knight(side);
		}

		if (character == 'b' || character == 'B') {
			return new Bishop(side);
		}

		if (character == 'q' || character == 'Q') {
			return new Queen(side);
		}

		if (character == 'k' || character == 'K') {
			return new King(side);
		}

		if (character == 'p' || character == 'P') {
			return new Pawn(side);
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
