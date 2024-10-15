package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.moves.Move;

public class MovesGenerator {

	private final Board board = new Board();
	private int movesCount;

	public int getNumberOfMoves(int depth) {
		board.initialize();
		movesCount = 0;

		countNumberOfMoves(depth);
		return movesCount;
	}

	private void countNumberOfMoves(int remainingDepth) {
		if (remainingDepth == 0) {
			movesCount++;
			return;
		}

		for (Move possibleMove : board.getAllCurrentSideMoves()) {
			if (board.playMove(possibleMove)) {
				countNumberOfMoves(remainingDepth - 1);
				board.undoLastMove();
			}
		}
	}
}
