package sample;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.io.*;
import java.net.*;

import static sample.Controller.*;
import static sample.ControllerUNO.*;

public class Networking implements Runnable {

    private String string;
    private Socket clientSocket;
    private static ControllerUNO parentController;

    public static PrintWriter outToServer;
    public static BufferedReader inFromServer;


    public Networking(Socket clientSocket)throws MalformedURLException {
        this.clientSocket = clientSocket;
    }

    public static void getParentController(ControllerUNO controller)
    {
        parentController = controller;
    }

    @Override
    public void run(){
        try {
            outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            gameOn = inFromServer.readLine();
            System.out.println("@@@@ 1.gameOn " + gameOn);
            while (true) {
                parentController.closeWindow();


                if (!gameOn.equals("wait")) {
                    myID = Integer.parseInt(gameOn);
                    parentController.setAllSizeText(myID);
                    parentController.text.setText("turn " + turn);
                    parentController.playerIdText.setVisible(true);
                    parentController.playerIdText.setText("player " + myID);
                    parentController.showColor.setText(lastCardPlayed.getColor());
                    parentController.waitText.setVisible(false);
                    parentController.waitBox.setVisible(false);
                    System.out.println("@@@ lastcard " + lastCardPlayed.getColor() + " " + lastCardPlayed.getValue());
                    if (myID == 0 && !firstMove) {
                        if(turn==0){
                        for (int m = 0; m < parentController.bottom_imv.size(); m++) {
                            parentController.bottom_imv.get(m).setMouseTransparent(false);
                        }
                        parentController.unoButton.setMouseTransparent(false);}
                        System.out.println("@@@ firstmove not true");
                        continue;
                    }
                    System.out.println("@@@ firstmove true for Id " + myID);

                    stringFromServer = inFromServer.readLine();
                    System.out.println("@@@@ stringFromServer " + ControllerUNO.stringFromServer);
                    //setting up the lastcard
                    String[] tokens = stringFromServer.split("#");
                    //0=turn,1=id,2=color,3=value,4=size

                    turn = Integer.parseInt(tokens[0]);
                    ControllerUNO.playerID = Integer.parseInt(tokens[1]);
                    lastCardPlayed.setColor(tokens[2]);
                    lastCardPlayed.setValue(Integer.parseInt(tokens[3]));
                    lastSize = Integer.parseInt(tokens[4]);

                    parentController.text.setText("turn " + turn);
                    if(lastCardPlayed.getColor().equals("win"))
                    {
                        parentController.win();
                        break;
                    }

                    try {

                        if(lastCardPlayed.getValue() == 13)
                        {
                            lastCardPlayed.setImage(new Image(new File("src\\sample\\uno_assets_2d\\PNGs\\large\\wild_color_large.png").toURI().toURL().toString()));
                        }
                        else if(lastCardPlayed.getValue() == 14)
                        {
                            lastCardPlayed.setImage(new Image(new File("src\\sample\\uno_assets_2d\\PNGs\\large\\wild_pick_four_large.png").toURI().toURL().toString()));
                        }
                        else {
                            lastCardPlayed.setImage(new Image(new File("src\\sample\\uno_assets_2d\\PNGs\\large\\" + lastCardPlayed.getColor() + "_" + lastCardPlayed.getValue() + "_large.png").toURI().toURL().toString()));
                        }
                    } catch (MalformedURLException e) {
                        System.err.println("could not set image");
                    }
                    //end of set up
                    System.out.println("@@@ 2-lastcard " + lastCardPlayed.getColor() + " " + lastCardPlayed.getValue());
                    parentController.setLastPlayerCardCount(playerID,lastSize);
                    parentController.center_imv.setImage(lastCardPlayed.getImage());
                    parentController.gameplay();

                } else {
                        parentController.waitBox.setVisible(true);
                        parentController.waitText.setVisible(true);
                        for (int i = 0; i < parentController.bottom_imv.size(); i++) {
                            parentController.bottom_imv.get(i).setMouseTransparent(true);
                        }
                        parentController.unoButton.setMouseTransparent(true);
                    gameOn = inFromServer.readLine();
                    //System.out.println("@@@@ gameOn " + Controller.gameOn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void send(String str)
    {
        System.out.println("SENDING "+str);
        outToServer.println(str);
    }

    public static String receive()
    {
        String string = "";
        try {
            string = inFromServer.readLine();
            System.out.println("@@@@ string in networking class receive func " + string);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("error returning string");
        }
        return string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}