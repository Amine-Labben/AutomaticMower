package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class MowingAreaServiceTest {
    private static final MowingAreaService mowingAreaService = new MowingAreaService();

    @Test
    public void should_mow_area_sequentially(){
        // GIVEN
        var area = new Area(5, 5);

        var mower1 = new Mower(UUID.randomUUID(), area, new Position(1, 2), Orientation.NORTH);
        var instructionsForMower1 = List.of(
                Instruction.ROTATE_LEFT,
                Instruction.ADVANCE,
                Instruction.ROTATE_LEFT,
                Instruction.ADVANCE,
                Instruction.ROTATE_LEFT,
                Instruction.ADVANCE,
                Instruction.ROTATE_LEFT,
                Instruction.ADVANCE,
                Instruction.ADVANCE
        );

        var mower2 = new Mower(UUID.randomUUID(), area, new Position(3, 3), Orientation.EST);
        var instructionsForMower2 = List.of(
                Instruction.ADVANCE,
                Instruction.ADVANCE,
                Instruction.ROTATE_RIGHT,
                Instruction.ADVANCE,
                Instruction.ADVANCE,
                Instruction.ROTATE_RIGHT,
                Instruction.ADVANCE,
                Instruction.ROTATE_RIGHT,
                Instruction.ROTATE_RIGHT,
                Instruction.ADVANCE
        );

        MowerTask mowerTask1 = new MowerTask(mower1, instructionsForMower1);
        MowerTask mowerTask2 = new MowerTask(mower2, instructionsForMower2);

        // WHEN
        mowingAreaService.mowSequentially(List.of(mowerTask1, mowerTask2));

        // THEN
        Assertions.assertEquals("1 3 N", mower1.toString());
        Assertions.assertEquals("5 1 E", mower2.toString());
    }
}
