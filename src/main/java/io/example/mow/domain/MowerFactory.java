package io.example.mow.domain;

public class MowerFactory {

    public LawnMower create(Orientation orientation, int abscissa, int ordinate, Surface surface) {
        Coordinates coordinates = new Coordinates(abscissa, ordinate);
        return new SimpleLawnMower(surface, orientation, coordinates);
    }
}
