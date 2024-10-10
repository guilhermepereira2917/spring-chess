package com.guilhermepereira.springchess.game;

public class MovesGenerator {

	private final Board board = new Board();

	public int getNumberOfMoves(int depth) {
		if (depth == 0) {
			return 1;
		}

		board.initialize();
		return board.getAllCurrentSideMoves().size();
	}
}
