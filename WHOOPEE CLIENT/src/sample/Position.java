package sample;

import java.awt.*;

public class Position {

    private int x;
    private int y;
    public Label alert;

    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x=0;
        this.y=0;
    }

    void moveRight() {
        if(this.x<8)
        {
            this.x++;
        }

        else
        {
            //alert.setText("this.you are not allowed to do that");
        }


    }

    void moveLeft() {
        if(this.x>0)
        {
          this.x--;
        }

        else
        {
            //alert.setText("this.you are not allowed to do that");
        }

    }

    void moveUp() {
        if(this.y>0)
        {
            this.y--;
        }

        else
        {
            //alert.setText("this.you are not allowed to do that");
        }
    }

    void moveDown() {
        if(this.y<8)
        {
            this.y++;
        }

        else
        {
            //alert.setText("this.you are not allowed to do that");
        }
    }

    boolean isAdjacent(Position p) {
        if (x == p.getX()) {
            if (((p.getY() - this.y) == 1) || ((this.y - p.getY()) == 1))
                return true;
            else
                return false;
        }

        else if (this.y == p.getY()) {
            if (((p.getX() - x) == 1) || ((x - p.getX()) == 1))
                return true;
            else
                return false;
        }

        else
            return false;
    }

    boolean isSamePoint(Position p) {

        if(p.getX()==this.x && p.getY()==this.y)
            return true;
        else
            return false;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

}
