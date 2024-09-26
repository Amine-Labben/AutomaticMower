package org.mowitnow.automaticmower.application;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AutomaticMowerClientTest {

    @Test
    public void should_mow_automatically_the_area_based_on_instructions_defined_in_file() throws Exception {
        // GIVEN
        var fileName = "automatic-mowing-input.txt";

        // WHEN
        var result = AutomaticMowerClient.getAutomaticMowingResult(fileName);

        // THEN
        Assertions.assertEquals(List.of("1 3 N", "5 1 E"), result);
    }
}
