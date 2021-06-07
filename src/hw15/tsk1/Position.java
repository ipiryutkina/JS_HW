package ru.hw.hw15.tsk1;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public Position changeX(int x0) {
        return new Position(x + x0, y);
    }

    public Position changeY(int y0) {
        return new Position(x, y + y0);
    }

    @Override
    public String toString() {
        return String.format("(%d , %d)", x, y);
    }

}
