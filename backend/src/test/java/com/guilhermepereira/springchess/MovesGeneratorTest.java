package com.guilhermepereira.springchess;

import com.guilhermepereira.springchess.game.MovesGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovesGeneratorTest {

	@Test
	@DisplayName("Testing if number of generated moves is equivalent to Shannon Number")
	public void shouldGenerateCorrectNumberOfMoves() {
		var movesGenerator = new MovesGenerator(false);

		assertEquals(20, movesGenerator.getNumberOfPossiblePositions(1));
		assertEquals(400, movesGenerator.getNumberOfPossiblePositions(2));
		assertEquals(8_902, movesGenerator.getNumberOfPossiblePositions(3));
		assertEquals(197_281, movesGenerator.getNumberOfPossiblePositions(4));
		assertEquals(4_865_609, movesGenerator.getNumberOfPossiblePositions(5));
		assertEquals(119_060_234, movesGenerator.getNumberOfPossiblePositions(6));
	}
}