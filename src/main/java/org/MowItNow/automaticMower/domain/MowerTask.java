package org.mowitnow.automaticmower.domain;

import java.util.List;

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
}
