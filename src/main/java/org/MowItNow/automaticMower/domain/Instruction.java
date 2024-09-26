package org.mowitnow.automaticmower.domain;

import java.util.Arrays;

public enum Instruction {
    ROTATE_LEFT("G"), ROTATE_RIGHT("D"), ADVANCE("A");

    private final String code;

    Instruction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Instruction getByCode(String code) {
        return Arrays.stream(Instruction.values())
                .filter(instruction -> instruction.getCode().equals(code))
                .findFirst().orElseThrow();
    }
}
