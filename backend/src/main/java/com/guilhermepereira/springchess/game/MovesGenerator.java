package com.guilhermepereira.springchess.game;

import com.guilhermepereira.springchess.game.moves.Move;

public class MovesGenerator {

	private final boolean logging;
	private final Board board = new Board();
	private int initialDepth;

	public MovesGenerator() {
		this(false);
	}

	public MovesGenerator(boolean logging) {
		this.logging = logging;
	}

	public long getNumberOfPossiblePositions(int depth) {
		board.initialize();
		initialDepth = depth;

		return countNumberOfPossiblePositions(depth);
	}

	private long countNumberOfPossiblePositions(int remainingDepth) {
		if (remainingDepth == 0) {
			return 1;
		}

		long positionsCount = 0L;
		for (Move possibleMove : board.getAllCurrentSideMoves()) {
			if (!board.playMove(possibleMove)) {
				continue;
			}

			long possiblePositions = countNumberOfPossiblePositions(remainingDepth - 1);
			if (logging && remainingDepth == initialDepth) {
				System.out.printf("%s: %s\n", possibleMove.getLongAlgebraicNotationRepresentation(), possiblePositions);
			}

			positionsCount += possiblePositions;

			board.undoLastMove();
		}

		return positionsCount;
	}
}
