package sample;

import java.awt.*;

public class Player {

    public int direction;
    public int total_shots;
    public boolean killed;
    public Position p=new Position();
    public int UP=0;
    public int DOWN=2;
    public int LEFT=3;
    public int RIGHT=1;
    public int MAX_SHOTS=3;

    public Player() {
        direction = DOWN;
        total_shots = MAX_SHOTS;
        killed = false;
    }

    public void turnLeft() {
        if(direction == UP)
            direction = LEFT;
        else if(direction == DOWN)
            direction = RIGHT;
        else if(direction == LEFT)
            direction = DOWN;
        else if(direction == RIGHT)
            direction = UP;
    }

    public void turnRight() {
        if(direction == UP)
            direction = RIGHT;
        else if(direction == DOWN)
            direction = LEFT;
        else if(direction == LEFT)
            direction = UP;
        else if(direction == RIGHT)
            direction = DOWN;
    }

    public void moveForward() {

        if(direction==UP)
        {
            p.moveUp();
        }

        else if(direction==DOWN)
        {
            p.moveDown();
        }

        else if(direction==LEFT)
        {
            p.moveLeft();
        }

        else if(direction==RIGHT)
        {
            p.moveRight();
        }

    }

    public int getX(){ return p.getX(); }

    public int getY(){ return p.getY(); }

    public boolean isAdjacent(Position pos) {
        return p.isAdjacent(pos);
    }

    public boolean isSamePoint(Position pos) {
        return p.isSamePoint(pos);
    }

    public void kill() {
        killed = true;
    }

    public int player_direction()
    {
        return direction;
    }

    public Position player_position()
    {
        return p;
    }

    public String getPositionInfo() {
        return "Current Position: " +p.getX() + ", " + p.getY();
    }

    public String getDirectionInfo() {
        String s="";
        if (direction == UP) s = "up";
        if (direction == DOWN) s = "down";
        if (direction == LEFT) s = "left";
        if (direction == RIGHT) s = "right";
        return "Player Facing: "+s;
    }


}
