package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AreaTest {
    static Stream<Arguments> providePositionsForOutOfBoundsTest() {
        return Stream.of(
                Arguments.of(new Position(1, 2), false),
                Arguments.of(new Position(3, 5), false),
                Arguments.of(new Position(-1, 2), true),
                Arguments.of(new Position(1, -1), true),
                Arguments.of(new Position(5, 2), true),
                Arguments.of(new Position(1, 6), true)
        );
    }

    @ParameterizedTest
    @MethodSource("providePositionsForOutOfBoundsTest")
    public void should_test_if_position_is_out_of_area(Position position, boolean expectedResult) {
        Area area = new Area(3,5);
        Assertions.assertEquals(expectedResult, area.isOutOfBounds(position));
    }
}
