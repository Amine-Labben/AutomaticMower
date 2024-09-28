package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MowerTaskTest {
    @Test
    public void should_execute_the_mower_automatic_task() {
        // GIVEN
        var mower = new Mower(new Area(5, 5), new Position(1, 2), Orientation.NORTH);
        var instructions = List.of(
                Instruction.ROTATE_LEFT,
                Instruction.ADVANCE,
                Instruction.ROTATE_RIGHT,
                Instruction.ADVANCE,
                Instruction.ROTATE_LEFT,
                Instruction.ADVANCE,
                Instruction.ROTATE_RIGHT,
                Instruction.ADVANCE,
                Instruction.ROTATE_RIGHT,
                Instruction.ADVANCE,
                Instruction.ADVANCE
        );

        var mowerTask = new MowerTask(mower, instructions);

        // WHEN
        mowerTask.executeAutomaticTask();

        // THEN
        Assertions.assertEquals(new Position(2, 4), mower.getPosition());
        Assertions.assertEquals(Orientation.EST, mower.getOrientation());
    }
}
