package com.guilhermepereira.springchess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovesGeneratorTest {

	@Test
	@DisplayName("Testing if number of generated moves is equivalent to Shannon Number")
	public void shouldGenerateCorrectNumberOfMoves() {
		var movesGenerator = new MovesGenerator();

		assertEquals(20, movesGenerator.getNumberOfMoves(1));
	}
}