package io.example.mow.domain;

public class Surface {
    private final int northCorner;
    private final int eastCorner;

    public Surface(int northCorner, int eastCorner) {

        this.northCorner = northCorner;
        this.eastCorner = eastCorner;
    }

    public boolean isNotAtSouthBorder(Coordinates coordinates) {
        return coordinates.getOrdinate() != 0;
    }

    public boolean isNotAtNorthBorder(Coordinates coordinates) {
        return coordinates.getOrdinate() != northCorner;
    }

    public boolean isNotAtEastBorder(Coordinates coordinates) {
        return coordinates.getAbscissa() != eastCorner;
    }

    public boolean isNotAtWestBorder(Coordinates coordinates) {
        return coordinates.getAbscissa() != 0;
    }
}
