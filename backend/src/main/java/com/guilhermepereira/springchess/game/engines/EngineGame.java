package com.guilhermepereira.springchess.game.engines;

import com.guilhermepereira.springchess.game.Game;
import com.guilhermepereira.springchess.game.moves.Move;

public class EngineGame extends Game {

	private final UCIEngine engine;

	public EngineGame(UCIEngine engine) {
		this.engine = engine;
	}

	@Override
	public void initialize() {
		super.initialize();
		engine.initialize();
	}

	public void finish() {
		engine.finish();
	}

	@Override
	public boolean playMove(Move move) {
		if (!super.playMove(move)) {
			return false;
		}

		return board.playUCIEngineMove(engine.getBestMove(board));
	}

	@Override
	public boolean playMove(String move) {
		if (!super.playMove(move)) {
			return false;
		}

		return board.playUCIEngineMove(engine.getBestMove(board));
	}
}
