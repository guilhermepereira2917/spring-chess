package com.guilhermepereira.springchess.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilhermepereira.springchess.game.moves.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

	@JsonIgnore
	protected final Board board;
	@JsonIgnore
	protected Square square;

	protected final PieceType type;
	protected final PieceSide side;
	protected boolean hasMoved = false;

	public Piece(Board board, Square square, PieceType type, PieceSide side) {
		this.board = board;
		this.square = square;
		this.type = type;
		this.side = side;
	}

	protected abstract List<? extends Move> getValidMoves();

	public boolean canMakeMove(Square targetSquare) {
		return getValidMoves().stream().anyMatch(m -> m.getTargetSquare().equals(targetSquare));
	}

	public Move getMove(Square targetSquare) {
		return getValidMoves().stream().filter(m -> m.getTargetSquare().equals(targetSquare)).findFirst().orElse(null);
	}

	protected List<Move> getHorizontallyAndVerticallyValidMovementSquares() {
		return getHorizontallyAndVerticallyValidMovementSquares(null);
	}

	protected List<Move> getHorizontallyAndVerticallyValidMovementSquares(Integer maxDistance) {
		int[][] directions = new int[][] {
			{1, 0},
			{0, 1},
			{-1, 0},
			{0, -1},
		};

		return getValidMovementSquaresFromDirections(directions, maxDistance);
	}

	protected List<Move> getDiagonallyValidMovementSquares() {
		return getDiagonallyValidMovementSquares(null);
	}

	protected List<Move> getDiagonallyValidMovementSquares(Integer maxDistance) {
		int[][] directions = new int[][] {
			{1, 1},
			{-1, 1},
			{-1, -1},
			{1, -1},
		};

		return getValidMovementSquaresFromDirections(directions, maxDistance);
	}

	private List<Move> getValidMovementSquaresFromDirections(int[][] directions, Integer maxDistance) {
		List<Move> validMovementSquares = new ArrayList<>();

		for (int[] direction : directions) {
			int row = getRow() + direction[0];
			int column = getColumn() + direction[1];
			int currentDistance = 1;

			Square candidateSquare = board.getSquare(row, column);
			while (candidateSquare != null && (maxDistance == null || currentDistance <= maxDistance)) {
				if (!candidateSquare.hasAllyPiece(side)) {
					validMovementSquares.add(createMove(candidateSquare));
				}

				if (!candidateSquare.isEmpty()) {
					break;
				}

				row += direction[0];
				column += direction[1];
				currentDistance++;

				candidateSquare = board.getSquare(row, column);
			}
		}

		return validMovementSquares;
	}

	protected Move createMove(Square targetSquare) {
		return new Move(square, targetSquare);
	}

	public boolean isPawn() {
		return type == PieceType.PAWN;
	}

	public boolean isKnight() {
		return type == PieceType.KNIGHT;
	}

	public boolean isBishop() {
		return type == PieceType.BISHOP;
	}

	public boolean isRook() {
		return type == PieceType.ROOK;
	}

	public boolean isQueen() {
		return type == PieceType.QUEEN;
	}

	public boolean isKing() {
		return type == PieceType.KING;
	}

	public boolean isBlack() {
		return this.getSide() == PieceSide.BLACK;
	}

	public boolean isWhite() {
		return this.getSide() == PieceSide.WHITE;
	}

	public int getRow() {
		return square.getRow();
	}

	public int getColumn() {
		return square.getColumn();
	}

	public Board getBoard() {
		return board;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public PieceType getType() {
		return type;
	}

	public PieceSide getSide() {
		return side;
	}

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
