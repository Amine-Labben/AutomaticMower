package org.mowitnow.automaticmower.domain;

import java.util.List;
import java.util.Objects;

public record MowerTask(Mower mower, List<Instruction> instructions) {

    public void executeAutomaticTask() {
        instructions.forEach(instruction -> executeInstruction(mower, instruction));
    }

    private void executeInstruction(Mower mower, Instruction instruction) {
        switch (instruction){
            case ROTATE_LEFT -> mower.turnLeft();
            case ROTATE_RIGHT -> mower.turnRight();
            case ADVANCE -> mower.advance();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MowerTask mowerTask = (MowerTask) o;
        return mower.equals(mowerTask.mower()) && instructions.equals(mowerTask.instructions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mower, instructions);
    }
}
