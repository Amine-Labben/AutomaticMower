package org.mowitnow.automaticmower.domain;

import java.util.Arrays;

public enum Orientation {
    NORTH("N", 1),
    EST("E", 2),
    SOUTH("S", 3),
    WEST("W", 4);

    private final String letter;
    private final int position;

    Orientation(String letter, int position) {
        this.letter = letter;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getLetter() {
        return letter;
    }

    public Orientation getOrientationAtLeft() {
        var resultPosition = this.position == 1 ? Orientation.values().length : this.position - 1;
        return Orientation.getByPosition(resultPosition);
    }

    public Orientation getOrientationAtRight() {
        var resultPosition = this.position == Orientation.values().length ? 1 : this.position + 1;
        return Orientation.getByPosition(resultPosition);
    }

    private static Orientation getByPosition(int position) {
        return Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getPosition() == position)
                .findFirst().orElseThrow();
    }
}
