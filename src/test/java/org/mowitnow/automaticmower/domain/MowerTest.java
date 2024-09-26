package org.mowitnow.automaticmower.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MowerTest {
    private final static UUID uuid = UUID.randomUUID();

    @Test
    public void should_turn_left() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(2, 4), Orientation.NORTH);

        // WHEN
        mower.turnLeft();

        // THEN
        Assertions.assertEquals(new Position(2, 4), mower.getPosition());
        Assertions.assertEquals(Orientation.WEST, mower.getOrientation());
    }

    @Test
    public void should_turn_right() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(2, 4), Orientation.NORTH);

        // WHEN
        mower.turnRight();

        // THEN
        Assertions.assertEquals(new Position(2, 4), mower.getPosition());
        Assertions.assertEquals(Orientation.EST, mower.getOrientation());
    }

    @Test
    public void should_advance_north() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(1, 1), Orientation.NORTH);

        // WHEN
        mower.advance();

        // THEN
        Assertions.assertEquals(new Position(1, 2), mower.getPosition());
        Assertions.assertEquals(Orientation.NORTH, mower.getOrientation());
    }

    @Test
    public void should_advance_est() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(1, 1), Orientation.EST);

        // WHEN
        mower.advance();

        // THEN
        Assertions.assertEquals(new Position(2, 1), mower.getPosition());
        Assertions.assertEquals(Orientation.EST, mower.getOrientation());
    }


    @Test
    public void should_advance_south() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(1, 1), Orientation.SOUTH);

        // WHEN
        mower.advance();

        // THEN
        Assertions.assertEquals(new Position(1, 0), mower.getPosition());
        Assertions.assertEquals(Orientation.SOUTH, mower.getOrientation());
    }

    @Test
    public void should_advance_west() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(1, 1), Orientation.WEST);

        // WHEN
        mower.advance();

        // THEN
        Assertions.assertEquals(new Position(0, 1), mower.getPosition());
        Assertions.assertEquals(Orientation.WEST, mower.getOrientation());
    }

    @Test
    public void should_not_advance_outside_area() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(5, 4), Orientation.EST);

        // WHEN
        mower.advance();

        // THEN
        Assertions.assertEquals(new Position(5, 4), mower.getPosition());
        Assertions.assertEquals(Orientation.EST, mower.getOrientation());
    }

    @Test
    public void should_convert_mower_to_string() {
        // GIVEN
        var mower = new Mower(uuid, new Area(5, 4), new Position(1, 2), Orientation.EST);

        // WHEN
        var result = mower.toString();

        // THEN
        Assertions.assertEquals("1 2 E", result);
    }
}
