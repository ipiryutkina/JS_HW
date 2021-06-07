package ru.hw.hw15.tsk1;

public class Tractor {
    private Position pos = new Position(0, 0);
    private Orientation ori = Orientation.NORTH;

    public Orientation getOrientation() {
        return ori;
    }

    public Position getPosition() {
        return new Position(this.pos);
    }

    public void moveForwards() {
        pos = ori.move(pos);
    }

    public void turnClockwise() {
        ori = ori.turn();
    }

    public static void main(String[] args) {
        Tractor tractor = new Tractor();
        for (int i = 0; i < 4; ++i) {
            tractor.moveForwards();
            tractor.turnClockwise();
        }
        System.out.printf("Position: %s, orientation: %s\n",
                tractor.getPosition().toString(), tractor.getOrientation().toString());
    }
}
