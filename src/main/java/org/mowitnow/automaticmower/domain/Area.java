package org.mowitnow.automaticmower.domain;

public record Area(int width, int height) {
    public boolean isOutOfBounds(Position position) {
        return position.x() < 0 || position.x() > width || position.y() < 0 || position.y() > height;
    }
}
