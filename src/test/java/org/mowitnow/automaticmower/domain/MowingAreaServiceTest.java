package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mowitnow.automaticmower.TestUtils.getMockMowerTasks;

public class MowingAreaServiceTest {
    private static final MowingAreaService mowingAreaService = new MowingAreaService();

    @Test
    public void should_mow_area_sequentially(){
        // GIVEN
        var mowerTasks = getMockMowerTasks();

        // WHEN
        mowingAreaService.mowSequentially(mowerTasks);

        // THEN
        Assertions.assertEquals("1 3 N", mowerTasks.get(0).mower().toString());
        Assertions.assertEquals("5 1 E", mowerTasks.get(1).mower().toString());
    }
}
