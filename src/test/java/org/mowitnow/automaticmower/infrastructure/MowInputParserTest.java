package org.mowitnow.automaticmower.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mowitnow.automaticmower.application.InvalidInputException;


import java.util.stream.Stream;

import static org.mowitnow.automaticmower.TestUtils.getMockMowerTasks;

public class MowInputParserTest {
    private static final MowInputParser mowInputParser = new MowInputParser();

    @Test
    public void should_parse_input_file_into_mower_tasks() throws Exception {
        // GIVEN
        var filePath = "automatic-mowing-input.txt";

        // WHEN
        var result = mowInputParser.parseMowingTasks(filePath);

        // THEN
        Assertions.assertEquals(getMockMowerTasks(), result);
    }

    @ParameterizedTest
    @MethodSource("provideFileInvalidInputArguments")
    public void should_throws_invalidInputException_when_input_file_format_invalid(String inputFileName, String expectedMessage) {
        // WHEN
        // THEN
        var exception = Assertions.assertThrows(InvalidInputException.class, () -> mowInputParser.parseMowingTasks(inputFileName));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideFileInvalidInputArguments() {
        return Stream.of(
                Arguments.of("empty-file.txt", "Input file must not be empty"),
                Arguments.of("mower-has-only-one-line.txt", "Every mower task must have be defined by exactly two lines"),
                Arguments.of("file-with-no-mowers.txt", "Input file must have at least one mower"),
                Arguments.of("file-with-missing-area-height.txt", "Area dimensions should contain exactly two numbers"),
                Arguments.of("file-with-negative-area-width.txt", "Area dimensions must be positive numbers"),
                Arguments.of("file-with-invalid-number-area-width.txt", "Area dimensions must be valid numbers"),
                Arguments.of("mower-with-missing-orientation.txt", "Mower initial position should have two numbers and an orientation"),
                Arguments.of("mower-with-inital-position-out-of-area.txt", "Mower position is out of area"),
                Arguments.of("file-with-invalid-instruction-char.txt", "Invalid instruction: T")
        );
    }
}
