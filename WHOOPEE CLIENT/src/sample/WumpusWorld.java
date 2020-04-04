package sample;

import javafx.scene.control.Label;

import java.awt.*;
import java.util.Random;

public class WumpusWorld {

    public Player player;
    public Wumpus wumpus;
    public Position gold_position;
    public Position pit_position;
    public Position flame_position;
    boolean ended=false;
    public int UP=0;
    public int DOWN=2;
    public int LEFT=3;
    public int RIGHT=1;
    public int MAX_SHOTS=3;

    public WumpusWorld() {

    }

    public WumpusWorld(int wumpus_x, int wumpus_y, int gold_x, int gold_y, int pit_x, int pit_y,int pit2_x,int pit2_y) {

        Random random=new Random();

        while((wumpus_x!=gold_x)&&(wumpus_y!=gold_y)){
            wumpus_x=random.nextInt(7)+1;
            wumpus_y=random.nextInt(7)+1;
        }

        while((pit_x!=wumpus_x)&&(pit_y!=wumpus_y)&&(pit_x!=gold_x)&&(pit_y!=gold_y)){
            pit_x=random.nextInt(7)+1;
            pit_y=random.nextInt(7)+1;
        }

        while((pit2_x!=wumpus_x)&&(pit2_y!=wumpus_y)&&(pit2_x!=gold_x)&&(pit2_y!=gold_y)&&(pit2_x!=pit_x)&&(pit2_y!=pit_y)){
            pit2_x=random.nextInt(7)+1;
            pit2_y=random.nextInt(7)+1;
        }
        wumpus=new Wumpus(wumpus_x,wumpus_y);
        gold_position=new Position(gold_x,gold_y);
        pit_position=new Position(pit_x,pit_y);
        flame_position=new Position(pit2_x,pit2_y);
    }

    public Position getGold_position(){ return gold_position; }

    public Position getFlame_position(){ return  flame_position;}

    public Position getPit_position(){ return pit_position; }

    public void moveForward() {
        player.moveForward();
        //return showGameState();
    }

    public void turnLeft() {
        player.turnLeft();
        //return showGameState();
    }

    public void turnRight() {
        player.turnRight();
        //return showGameState();
    }

    /*public void shoot() {

        int arrows=MAX_SHOTS;

        int pl_x=player.player_position().getX();
        int pl_y=player.player_position().getY();
        int wmp_x=wumpus.getPosition().getX();
        int wmp_y=wumpus.getPosition().getY();

        if(arrows==0)
        {
            System.out.println("No arrows left");
        }

        else if(!(wumpus.wumpus_killed())){

            arrows--;

            if(player.player_direction()==UP)
            {
                if((pl_x == wmp_x)&&(pl_y < wmp_y))
                    wumpus.kill();
            }

            else if(player.player_direction()==DOWN)
            {
                if((pl_x == wmp_x)&&(pl_y > wmp_y))
                    wumpus.kill();
            }

            else if(player.player_direction()==LEFT)
            {
                if((pl_y == wmp_y)&&(pl_x > wmp_x))
                    wumpus.kill();
            }

            else if(player.player_direction()==RIGHT)
            {
                if((pl_y == wmp_y)&&(pl_x < wmp_x))
                    wumpus.kill();
            }

            if(wumpus.wumpus_killed())
                System.out.println("Wumpus is dead");

            else
                System.out.println("Nof effective");

        }

        else if(wumpus.wumpus_killed())
            System.out.println("Wumpus is already dead");
    }

    /*void showGameState() {
        cout << player.getPositionInfo() << endl;
        cout << player.getDirectionInfo() << endl;


        if (player.isAdjacent(wumpus.getPosition())){
            if(!(wumpus.wumpus_killed()))
                cout << "stench!" << endl;
        }

        if(player.isAdjacent(pit_position)){
            cout<< "Breeze!" <<endl;
        }

        if(!(wumpus.wumpus_killed())){
            if (player.isSamePoint(wumpus.getPosition())){
                cout << "Player is killed!" << endl;
                player.kill();
                cout << "Game over! You were devoured by the Wumpus!" << endl;
                ended = true;
            }
        }

        if(player.isSamePoint(pit_position)){
            cout<< "Player is killed" <<endl;
            player.kill();
            cout<< "Game Over! You fell into the pit!" <<endl;
            ended = true;
        }

        if (player.isSamePoint(gold_position)) {
            cout << "Got the gold!" << endl;
            cout << "Game ended, you won!" << endl;
            ended = true;
        }
    }*/

    public boolean isOver() {
        return ended;
    }


}
