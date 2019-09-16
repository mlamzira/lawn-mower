package io.example.mow.domain;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleLawnMower implements LawnMower {

    private Orientation orientation;
    private Surface surface;
    private Coordinates coordinates;

    public SimpleLawnMower(Surface surface, Orientation orientation, Coordinates coordinates) {
        this.orientation = orientation;
        this.surface = surface;
        this.coordinates = coordinates;
    }

    @Override
    public void turnRight() {
        orientation = orientation.toRight();
    }

    @Override
    public void turnLeft() {
        orientation = orientation.toLeft();
    }

    @Override
    public void moveForward() {

        LawnMowerBehaviour behaviour = movingBehaviourByOrientation.get(orientation);
        behaviour.moveForwardIfPossible(this);
    }

    @Override
    public Orientation orientation() {
        return orientation;
    }

    @Override
    public int abscissa() {
        return coordinates.getAbscissa();
    }

    @Override
    public int ordinate() {
        return coordinates.getOrdinate();
    }

    private boolean isNotAtWestBorder() {
        return surface.isNotAtWestBorder(coordinates);
    }

    private boolean isNotAtEastBorder() {
        return surface.isNotAtEastBorder(coordinates);
    }

    private boolean isNotAtNorthBorder() {
        return surface.isNotAtNorthBorder(coordinates);
    }

    private boolean isNotAtSouthBorder() {
        return surface.isNotAtSouthBorder(coordinates);
    }

    private static class LawnMowerBehaviour {
        private Predicate<SimpleLawnMower> isNotAtBorder;
        private Consumer<Coordinates> moveForward;

        public LawnMowerBehaviour(Predicate<SimpleLawnMower> isNotAtBorder, Consumer<Coordinates> moveForward) {
            this.isNotAtBorder = isNotAtBorder;
            this.moveForward = moveForward;
        }

        public void moveForwardIfPossible(SimpleLawnMower lawnMower) {
            if (isNotAtBorder.test(lawnMower)) {
                this.moveForward.accept(lawnMower.coordinates);
            }
        }
    }

    private static Map<Orientation, LawnMowerBehaviour> movingBehaviourByOrientation = Map.of(
            Orientation.NORTH, new LawnMowerBehaviour(SimpleLawnMower::isNotAtNorthBorder, Coordinates::increaseOrdinate),
            Orientation.EAST, new LawnMowerBehaviour(SimpleLawnMower::isNotAtEastBorder, Coordinates::increaseAbscissa),
            Orientation.SOUTH, new LawnMowerBehaviour(SimpleLawnMower::isNotAtSouthBorder, Coordinates::decreaseOrdinate),
            Orientation.WEST, new LawnMowerBehaviour(SimpleLawnMower::isNotAtWestBorder, Coordinates::decreaseAbscissa));
}
