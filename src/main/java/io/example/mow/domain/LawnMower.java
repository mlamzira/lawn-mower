package io.example.mow.domain;

public interface LawnMower {
    void turnRight();

    void turnLeft();

    void moveForward();

    Orientation orientation();

    int abscissa();

    int ordinate();
}
