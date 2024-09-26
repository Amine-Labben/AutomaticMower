package org.mowitnow.automaticmower.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
}
