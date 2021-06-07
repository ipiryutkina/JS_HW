package ru.hw.hw15.tsk1;

public enum Orientation {
    NORTH {
        @Override
        public Orientation turn() {
            return EAST;
        }

        @Override
        public Position move(Position p) {
            return p.changeY(1);
        }
    },
    WEST {
        @Override
        public Orientation turn() {
            return NORTH;
        }

        @Override
        public Position move(Position p) {
            return p.changeX(-1);
        }
    },
    SOUTH {
        @Override
        public Orientation turn() {
            return WEST;
        }

        @Override
        public Position move(Position p) {
            return p.changeY(-1);
        }
    },
    EAST {
        @Override
        public Orientation turn() {
            return SOUTH;
        }

        @Override
        public Position move(Position p) {
            return p.changeX(1);
        }
    };

    public abstract Position move(Position p);

    public abstract Orientation turn();
}
