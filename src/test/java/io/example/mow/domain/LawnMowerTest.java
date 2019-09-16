package io.example.mow.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LawnMowerTest {

    @Test
    void aLawnMowerShouldBeHeadingToTheSpecifiedOrientation() {
        LawnMower lawnMower1 = lawnMower(Orientation.NORTH, 0, 0);
        LawnMower lawnMower2 = lawnMower(Orientation.EAST, 0, 0);
        LawnMower lawnMower3 = lawnMower(Orientation.SOUTH, 0, 0);
        LawnMower lawnMower4 = lawnMower(Orientation.WEST, 0, 0);

        assertThat(lawnMower1.orientation()).isEqualTo(Orientation.NORTH);
        assertThat(lawnMower2.orientation()).isEqualTo(Orientation.EAST);
        assertThat(lawnMower3.orientation()).isEqualTo(Orientation.SOUTH);
        assertThat(lawnMower4.orientation()).isEqualTo(Orientation.WEST);
    }

    @ParameterizedTest
    @MethodSource("turningRightParams")
    void turnRightScenarios(Orientation originalOrientation, Orientation expectedOrientation) {
        LawnMower lawnMower = lawnMower(originalOrientation, 0, 0);

        lawnMower.turnRight();

        assertThat(lawnMower.orientation()).isEqualTo(expectedOrientation);
    }

    public static Stream<Arguments> turningRightParams() {
        return Stream.of(
                arguments(Orientation.NORTH, Orientation.EAST),
                arguments(Orientation.EAST, Orientation.SOUTH),
                arguments(Orientation.SOUTH, Orientation.WEST),
                arguments(Orientation.WEST, Orientation.NORTH)
        );
    }

    @ParameterizedTest
    @MethodSource("turningLeftParams")
    void turnLeftScenarios(Orientation originalOrientation, Orientation expectedOrientation) {
        LawnMower lawnMower = lawnMower(originalOrientation, 0, 0);

        lawnMower.turnLeft();

        assertThat(lawnMower.orientation()).isEqualTo(expectedOrientation);
    }

    public static Stream<Arguments> turningLeftParams() {
        return Stream.of(
                arguments(Orientation.NORTH, Orientation.WEST),
                arguments(Orientation.WEST, Orientation.SOUTH),
                arguments(Orientation.SOUTH, Orientation.EAST),
                arguments(Orientation.EAST, Orientation.NORTH)
        );
    }

    @Test
    void aLawnMowerShouldBeAtTheSpecifiedCoordinates() {
        LawnMower lawnMower = lawnMower(Orientation.NORTH, 2, 5);
        assertThat(lawnMower.abscissa()).isEqualTo(2);
        assertThat(lawnMower.ordinate()).isEqualTo(5);
    }

    @Test
    void aLawnMowerHeadingNorthShouldBeAt_0_1_AfterHeadingForward() {
        LawnMower lawnMower = lawnMower(Orientation.NORTH, 0, 0);

        lawnMower.moveForward();

        assertThat(lawnMower.orientation()).isEqualTo(Orientation.NORTH);
        assertThat(lawnMower.abscissa()).isEqualTo(0);
        assertThat(lawnMower.ordinate()).isEqualTo(1);
    }

    @Test
    void aLawnMowerHeadingEastShouldBeAt_1_0_AfterHeadingForward() {
        LawnMower lawnMower = lawnMower(Orientation.EAST, 0, 0);

        lawnMower.moveForward();

        assertThat(lawnMower.orientation()).isEqualTo(Orientation.EAST);
        assertThat(lawnMower.abscissa()).isEqualTo(1);
        assertThat(lawnMower.ordinate()).isEqualTo(0);
    }

    @Test
    void aLawnMowerHeadingSouthShouldBeAt_0_0_AfterHeadingForward() {
        LawnMower lawnMower = lawnMower(Orientation.SOUTH, 0, 1);

        lawnMower.moveForward();

        assertThat(lawnMower.orientation()).isEqualTo(Orientation.SOUTH);
        assertThat(lawnMower.abscissa()).isEqualTo(0);
        assertThat(lawnMower.ordinate()).isEqualTo(0);
    }

    @Test
    void aLawnMowerHeadingWestShouldBeAt_0_0_AfterHeadingForward() {
        LawnMower lawnMower = lawnMower(Orientation.WEST, 1, 0);

        lawnMower.moveForward();

        assertThat(lawnMower.orientation()).isEqualTo(Orientation.WEST);
        assertThat(lawnMower.abscissa()).isEqualTo(0);
        assertThat(lawnMower.ordinate()).isEqualTo(0);
    }
    @Test
    void cannotMoveWhenHeadingSouthAt_0_0() {
        LawnMower lawnMower = lawnMower(Orientation.SOUTH, 0, 0);

        lawnMower.moveForward();

        assertThat(lawnMower.abscissa()).isEqualTo(0);
        assertThat(lawnMower.ordinate()).isEqualTo(0);
    }

    @Test
    void cannotMoveWhenHeadingNorthAt_0_5() {
        LawnMower lawnMower = lawnMower(Orientation.NORTH, 0, 5);

        lawnMower.moveForward();

        assertThat(lawnMower.abscissa()).isEqualTo(0);
        assertThat(lawnMower.ordinate()).isEqualTo(5);
    }
    @Test
    void cannotMoveWhenHeadingEastAt_5_0() {
        LawnMower lawnMower = lawnMower(Orientation.EAST, 5, 0);

        lawnMower.moveForward();

        assertThat(lawnMower.abscissa()).isEqualTo(5);
        assertThat(lawnMower.ordinate()).isEqualTo(0);
    }

    @Test
    void cannotMoveWhenHeadingWestAt_0_3() {
        LawnMower lawnMower = lawnMower(Orientation.WEST, 0, 3);

        lawnMower.moveForward();

        assertThat(lawnMower.abscissa()).isEqualTo(0);
        assertThat(lawnMower.ordinate()).isEqualTo(3);
    }

    private LawnMower lawnMower(Orientation orientation, int abscissa, int ordinate) {
        Coordinates coordinates = new Coordinates(abscissa, ordinate);
        Surface surface = new Surface(5, 5);
        return new SimpleLawnMower(surface, orientation, coordinates);
    }
}
