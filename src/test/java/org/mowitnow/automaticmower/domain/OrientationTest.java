package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class OrientationTest {

    public static Stream<Arguments> provideRotationLeftArguments() {
        return Stream.of(
                Arguments.of(Orientation.NORTH, Orientation.WEST),
                Arguments.of(Orientation.EST, Orientation.NORTH),
                Arguments.of(Orientation.SOUTH, Orientation.EST),
                Arguments.of(Orientation.WEST, Orientation.SOUTH)
        );
    }

    public static Stream<Arguments> provideRotationRightArguments() {
        return Stream.of(
                Arguments.of(Orientation.NORTH, Orientation.EST),
                Arguments.of(Orientation.EST, Orientation.SOUTH),
                Arguments.of(Orientation.SOUTH, Orientation.WEST),
                Arguments.of(Orientation.WEST, Orientation.NORTH)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRotationLeftArguments")
    public void should_return_orientation_at_the_left(Orientation initialOrientation, Orientation expectedOrientation) {
        // WHEN
        Orientation actualOrientation = initialOrientation.getOrientationAtLeft();

        // THEN
        Assertions.assertEquals(expectedOrientation, actualOrientation);
    }

    @ParameterizedTest
    @MethodSource("provideRotationRightArguments")
    public void should_return_orientation_at_the_right(Orientation initialOrientation, Orientation expectedOrientation) {
        // WHEN
        Orientation actualOrientation = initialOrientation.getOrientationAtRight();

        // THEN
        Assertions.assertEquals(expectedOrientation, actualOrientation);
    }
}
