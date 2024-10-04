package com.guilhermepereira.springchess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgebraicNotationConverterTest {

	@Test
	@DisplayName("Testing if algebraic coordinate a8 returns coordinates {0, 0}")
	public void shouldReturnCoordinates0And0ForSquareA8() {
		int[] coordinates = AlgebraicNotationConverter.convertAlgebraicCoordinate("a8");

		assertEquals(0, coordinates[0]);
		assertEquals(0, coordinates[1]);
	}

	@Test
	@DisplayName("Testing if algebraic coordinate a1 returns coordinates {7, 0}")
	public void shouldReturnCoordinates7And0ForSquareA1() {
		int[] coordinates = AlgebraicNotationConverter.convertAlgebraicCoordinate("a1");

		assertEquals(7, coordinates[0]);
		assertEquals(0, coordinates[1]);
	}

	@Test
	@DisplayName("Testing if algebraic coordinate h1 returns coordinates {7, 7}")
	public void shouldReturnCoordinates7And7ForSquareH1() {
		int[] coordinates = AlgebraicNotationConverter.convertAlgebraicCoordinate("h1");

		assertEquals(7, coordinates[0]);
		assertEquals(7, coordinates[1]);
	}

	@Test
	@DisplayName("Testing if algebraic coordinate h8 returns coordinates {0, 7}")
	public void shouldReturnCoordinates0And7ForSquareH8() {
		int[] coordinates = AlgebraicNotationConverter.convertAlgebraicCoordinate("h8");

		assertEquals(0, coordinates[0]);
		assertEquals(7, coordinates[1]);
	}
}