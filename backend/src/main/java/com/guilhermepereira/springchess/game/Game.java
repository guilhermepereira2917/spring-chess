package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.moves.Move;

public class Game {

	private final Board board = new Board();

	public void initialize() {
		board.initialize();
	}

	public void initialize(String fenString) {
		board.initialize(fenString);
	}

	public boolean playMove(String move) {
		return board.playMove(move);
	}

	public boolean playMove(Move move) {
		return board.playMove(move);
	}

	public Board getBoard() {
		return board;
	}
}
