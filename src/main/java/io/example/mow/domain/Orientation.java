package io.example.mow.domain;

import java.util.Arrays;

public enum Orientation {
    NORTH {
        @Override
        public Orientation toRight() {
            return EAST;
        }

        @Override
        public Orientation toLeft() {
            return WEST;
        }
    }, EAST {
        @Override
        public Orientation toRight() {
            return SOUTH;
        }

        @Override
        public Orientation toLeft() {
            return NORTH;
        }
    }, SOUTH {
        @Override
        public Orientation toRight() {
            return WEST;
        }

        @Override
        public Orientation toLeft() {
            return EAST;
        }
    }, WEST {
        @Override
        public Orientation toRight() {
            return NORTH;
        }

        @Override
        public Orientation toLeft() {
            return SOUTH;
        }
    };

    public static Orientation from(String orientation) {
        return Arrays.stream(values())
                .filter(val -> val.name().startsWith(orientation))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("unknown orientation:" + orientation));
    }

    public abstract Orientation toRight();

    public abstract Orientation toLeft();
}
