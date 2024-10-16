package com.guilhermepereira.springchess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgebraicNotationConverterTest {

	@ParameterizedTest
	@MethodSource("getCoordinatesTestCases")
	@DisplayName("Testing if are converted to algebraic notation and back correctly")
	public void shouldConvertCoordinatesToAndBackFromAlgebraicNotation(String algebraicCoordinate, int[] expectedCoordinates) {
		int[] coordinates = AlgebraicNotationConverter.convertAlgebraicCoordinate(algebraicCoordinate);
		assertArrayEquals(coordinates, expectedCoordinates);

		String reconvertedAlgebraicCoordinate = AlgebraicNotationConverter.convertToAlgebraicCoordinate(coordinates[0], coordinates[1]);
		assertEquals(algebraicCoordinate, reconvertedAlgebraicCoordinate);
	}

	private static Stream<Arguments> getCoordinatesTestCases() {
		return Stream.of(
			Arguments.of("a8", new int[]{0, 0}),
			Arguments.of("a1", new int[]{7, 0}),
			Arguments.of("h1", new int[]{7, 7}),
			Arguments.of("h8", new int[]{0, 7}),
			Arguments.of("f7", new int[]{1, 5})
		);
	}
}