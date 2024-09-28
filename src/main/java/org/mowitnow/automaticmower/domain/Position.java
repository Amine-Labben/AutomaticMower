package org.mowitnow.automaticmower.domain;

public record Position(int x, int y) {
    @Override
    public String toString() {
        return String.join(" ", String.valueOf(x), String.valueOf(y));
    }
}
