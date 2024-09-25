package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void should_convert_position_to_string() {
        // GIVEN
        var position = new Position(2,4);

        // WHEN
        var result = position.toString();

        // THEN
        Assertions.assertEquals("2 4", result);
    }
}
