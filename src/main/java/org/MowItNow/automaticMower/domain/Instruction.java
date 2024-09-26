package org.mowitnow.automaticmower.domain;

public enum Instruction {
    ROTATE_LEFT("G"), ROTATE_RIGHT("D"), ADVANCE("A");

    private final String code;

    Instruction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
