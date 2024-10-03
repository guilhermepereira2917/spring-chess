package com.guilhermepereira.springchess.game;

public class Game {

	private final Board board = new Board();

	public void initialize() {
		board.initialize();
	}

	public boolean playMove(Move move) {
		return board.playMove(move);
	}

	public Board getBoard() {
		return board;
	}
}
