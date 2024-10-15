package com.guilhermepereira.springchess.game;

public enum PieceSide {
	WHITE {
		@Override
		public PieceSide getOtherSide() {
			return BLACK;
		}
	},
	BLACK {
		@Override
		public PieceSide getOtherSide() {
			return WHITE;
		}
	};

	public abstract PieceSide getOtherSide();
}
