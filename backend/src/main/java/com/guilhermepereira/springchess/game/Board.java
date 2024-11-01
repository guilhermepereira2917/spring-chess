package com.guilhermepereira.springchess.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilhermepereira.springchess.game.moves.Move;

import java.util.*;

public class Board {

	private static final String fenStringInitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	private Square[][] squares = new Square[8][8];
	private Map<PieceSide, Map<PieceType, List<Piece>>> pieces;

	private PieceSide turnSide;
	private Square enPassantSquare;
	private GameResultEnum gameResult;

	@JsonIgnore
	private List<Move> playedMoves;
	@JsonIgnore
	private List<Square> enPassantSquares;

	public void initialize() {
		initialize(fenStringInitialPosition);
	}

	public void initialize(String fenString) {
		pieces = new HashMap<>();

		String[] stringInfo = fenString.split(" ");

		placePieces(stringInfo[0]);
		turnSide = stringInfo[1].equals("w") ? PieceSide.WHITE : PieceSide.BLACK;
		enPassantSquare = null;

		playedMoves = new ArrayList<>();
		enPassantSquares = new ArrayList<>();
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

	public boolean playUCIEngineMove(String UCIEngineMoveRepresentation) {
		Square originalSquare = getSquare(UCIEngineMoveRepresentation.substring(0, 2));
		Square targetSquare = getSquare(UCIEngineMoveRepresentation.substring(2, 4));

		return playMove(new Move(originalSquare, targetSquare));
	}

	public boolean playMove(Move move) {
		return playMove(move, true);
	}

	public boolean playMove(Move move, boolean validateCheckmate) {
		if (gameResult != null) {
			return false;
		}

		if (move.getOriginalSquare().isEmpty()) {
			return false;
		}

		Piece piece = move.getOriginalSquare().getPiece();
		if (piece.getSide() != turnSide) {
			return false;
		}

		Move executableMove = piece.getMove(move);
		if (executableMove == null) {
			return false;
		}

		enPassantSquares.add(enPassantSquare);
		executableMove.execute(this);
		playedMoves.add(executableMove);
		turnSide = turnSide.getOtherSide();

		if (!executableMove.isPawnDoubleSquareMove()) {
			enPassantSquare = null;
		}

		if (isKingInCheck(turnSide.getOtherSide())) {
			undoLastMove();
			return false;
		}

		if (validateCheckmate && isCheckMate()) {
			gameResult = turnSide == PieceSide.WHITE ? GameResultEnum.BLACK_WINS : GameResultEnum.WHITE_WINS;
		}

		return true;
	}

	public boolean undoLastMove() {
		if (playedMoves.isEmpty() || enPassantSquares.isEmpty()) {
			return false;
		}

		playedMoves.getLast().undo(this);
		playedMoves.removeLast();

		enPassantSquare = enPassantSquares.getLast();
		enPassantSquares.removeLast();

		turnSide = turnSide.getOtherSide();

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

	public void placePiece(Piece piece, Square targetSquare) {
		if (piece == null) {
			return;
		}

		targetSquare.setPiece(piece);
		piece.setSquare(targetSquare);
		addPiece(piece);
	}

	public void addPiece(Piece piece) {
		pieces.computeIfAbsent(piece.getSide(), k -> new HashMap<>()).computeIfAbsent(piece.getType(), k -> new ArrayList<>()).add(piece);
	}

	public void removePiece(Piece piece) {
		pieces.get(piece.getSide()).get(piece.getType()).remove(piece);
	}

	private boolean isCheckMate() {
		if (!isKingInCheck(turnSide)) {
			return false;
		}

		for (Move move : getAllCurrentSideMoves()) {
			if (playMove(move, false)) {
				undoLastMove();
				return false;
			}
		}

		return true;
	}

	private boolean isKingInCheck(PieceSide side) {
		return isPieceAttacked(getKing(side));
	}

	private boolean isPieceAttacked(Piece piece) {
		for (Piece enemyPiece : getAllSidePieces(piece.getSide().getOtherSide())) {
			if (enemyPiece.canMakeMove(piece.getSquare())) {
				return true;
			}
		}

		return false;
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

	private Piece getKing(PieceSide side) {
		return pieces.get(side).get(PieceType.KING).getFirst();
	}

	@JsonIgnore
	public List<? extends Move> getAllCurrentSideMoves() {
		return getAllSideMoves(turnSide);
	}

	private List<? extends Move> getAllSideMoves(PieceSide side) {
		return getAllSidePieces(side).stream().map(Piece::getValidMoves).flatMap(List::stream).toList();
	}

	private List<Piece> getAllSidePieces(PieceSide side) {
		return pieces.get(side).values().stream().flatMap(List::stream).toList();
	}

	public List<Piece> getAllCurrentSidePiecesOfType(PieceType type) {
		return pieces.get(turnSide).get(type);
	}

	@JsonIgnore
	public boolean isWhiteTurn() {
		return turnSide == PieceSide.WHITE;
	}

	@JsonIgnore
	public boolean isBlackTurn() {
		return turnSide == PieceSide.BLACK;
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public PieceSide getTurnSide() {
		return turnSide;
	}

	public Square getEnPassantSquare() {
		return enPassantSquare;
	}

	public void setEnPassantSquare(Square enPassantSquare) {
		this.enPassantSquare = enPassantSquare;
	}

	public GameResultEnum getGameResult() {
		return gameResult;
	}

	public List<Move> getPlayedMoves() {
		return playedMoves;
	}
}
