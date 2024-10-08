package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.moves.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

	private static final String fenStringInitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private Square[][] squares = new Square[8][8];
	private Map<PieceSide, Map<PieceType, List<Piece>>> pieces;

	private PieceSide turnSide;
	private Square enPassantSquare;

	public void initialize() {
		initialize(fenStringInitialPosition);
	}

	public void initialize(String fenString) {
		pieces = new HashMap<>();

		String[] stringInfo = fenString.split(" ");

		placePieces(stringInfo[0]);
		turnSide = stringInfo[1].equals("w") ? PieceSide.WHITE : PieceSide.BLACK;
		enPassantSquare = null;
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
				Piece newPiece = PieceFactory.createPieceFromPieceType(this, newSquare, character);
				if (newPiece != null) {
					addPiece(newPiece);
				}

				newSquare.setPiece(newPiece);

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

	public boolean playMove(String algebraicMove) {
		Move move = AlgebraicNotationConverter.convertAlgebraicMove(this, algebraicMove);
		return move != null && playMove(move);
	}

	public boolean playMove(Move move) {
		if (move.getOriginalSquare().isEmpty()) {
			return false;
		}

		Piece piece = move.getOriginalSquare().getPiece();
		if (piece.getSide() != turnSide) {
			return false;
		}

		if (!piece.canMakeMove(move.getTargetSquare())) {
			return false;
		}

		Move executableMove = piece.getMove(move);
		executableMove.execute(this);

		turnSide = turnSide == PieceSide.WHITE ? PieceSide.BLACK : PieceSide.WHITE;
		if (!executableMove.isPawnDoubleSquareMove()) {
			enPassantSquare = null;
		}

		return true;
	}

	public void movePiece(Piece piece, Square targetSquare) {
		capturePiece(targetSquare.getPiece());

		piece.getSquare().setPiece(null);
		targetSquare.setPiece(piece);

		piece.setSquare(targetSquare);
		piece.setHasMoved(true);
	}

	public void capturePiece(Piece piece) {
		if (piece == null) {
			return;
		}

		piece.getSquare().setPiece(null);
		removePiece(piece);
	}

	public void addPiece(Piece piece) {
		pieces.computeIfAbsent(piece.getSide(), k -> new HashMap<>()).computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
	}

	public void removePiece(Piece piece) {
		pieces.get(piece.getSide()).get(piece.getType()).remove(piece);
	}

	public Square getSquare(int row, int column) {
		if (row >= 0 && row < squares.length && column >= 0 && column < squares[0].length) {
			return squares[row][column];
		}

		return null;
	}

	public Square getSquare(String algebraicCoordinate) {
		int[] coordinates = AlgebraicNotationConverter.convertAlgebraicCoordinate(algebraicCoordinate);

		return squares[coordinates[0]][coordinates[1]];
	}

	public List<? extends Move> getAllCurrentSideMoves() {
		return getAllCurrentSidePieces().stream().map(Piece::getValidMoves).flatMap(List::stream).toList();
	}

	public List<Piece> getAllCurrentSidePieces() {
		return pieces.get(turnSide).values().stream().flatMap(List::stream).toList();
	}

	public List<Piece> getAllCurrentSidePiecesOfType(PieceType type) {
		return pieces.get(turnSide).get(type);
	}

	public boolean isWhiteTurn() {
		return turnSide == PieceSide.WHITE;
	}

	public boolean isBlackTurn() {
		return turnSide == PieceSide.BLACK;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public Square getEnPassantSquare() {
		return enPassantSquare;
	}

	public void setEnPassantSquare(Square enPassantSquare) {
		this.enPassantSquare = enPassantSquare;
	}
}
