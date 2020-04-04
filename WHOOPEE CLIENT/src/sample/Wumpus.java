package sample;

public class Wumpus {

    boolean killed=false;
    Position p;

    public Wumpus(int x, int y) {
        p = new Position(x, y);
        killed=false;
    }


    void kill() {
        killed = true;
    }

    Position getPosition() {
        return p;
    }

    boolean wumpus_killed()
    {
        return killed;
    }

}
