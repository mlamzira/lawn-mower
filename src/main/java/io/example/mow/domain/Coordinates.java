package io.example.mow.domain;

public class Coordinates {
    private int abscissa;
    private int ordinate;

    public Coordinates(int abscissa, int ordinate) {
        this.abscissa = abscissa;
        this.ordinate = ordinate;
    }

    public int getAbscissa() {
        return abscissa;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public void increaseOrdinate() {
        ordinate++;
    }

    public void increaseAbscissa() {
        abscissa++;
    }

    public void decreaseOrdinate() {
        ordinate--;
    }

    public void decreaseAbscissa() {
        abscissa--;
    }
}
