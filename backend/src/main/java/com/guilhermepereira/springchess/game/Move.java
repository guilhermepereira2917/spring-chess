package com.guilhermepereira.springchess.game;

public class Move {
	private final Square originalSquare;
	private final Square targetSquare;

	public Move(Square originalSquare, Square targetSquare) {
		this.originalSquare = originalSquare;
		this.targetSquare = targetSquare;
	}

	public Square getOriginalSquare() {
		return originalSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}
}
