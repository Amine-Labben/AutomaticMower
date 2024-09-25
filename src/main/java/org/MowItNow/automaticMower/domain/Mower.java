package org.mowitnow.automaticmower.domain;

public class Mower {
    private Area area;
    private Position position;
    private Orientation orientation;

    public Mower(Area area, Position position, Orientation orientation) {
        this.area = area;
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void turnLeft() {
        this.orientation = this.orientation.getOrientationAtLeft();
    }

    public void turnRight() {
        this.orientation = this.orientation.getOrientationAtRight();
    }

    public void advance() {
        this.position = switch (this.orientation) {
            case NORTH -> new Position(this.position.x(), Math.min(this.area.height(), this.position.y() + 1));
            case EST -> new Position(Math.min(this.area.width(), this.position.x() + 1), this.position.y());
            case SOUTH -> new Position(this.position.x(), Math.max(0, this.position.y() - 1));
            case WEST -> new Position(Math.max(0, this.position.x() - 1), this.position.y());
        };
    }

    @Override
    public String toString() {
        return String.join(" ", position.toString(), orientation.getLetter());
    }
}
