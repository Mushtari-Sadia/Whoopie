package sample;

import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.io.File;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javax.swing.*;

public class ControllerUNO extends Controller {

    @FXML
    public StackPane parentStack;
    public GridPane bottomGrid;
    public GridPane topGrid;
    public GridPane rightGrid;
    public GridPane leftGrid;
    public ImageView center_imv;
    public ImageView bottom_imv1, bottom_imv2, bottom_imv3, bottom_imv4, bottom_imv5, bottom_imv6, bottom_imv7;

    public Text text;
    public Text colorTxt;
    public Text showColor;
    public Text winText;
    public Text closeText;
    public Text waitText;
    public Text playerIdText;
    public Text topSizeText;
    public Text bottomSizeText;
    public Text leftSizeText;
    public Text rightSizeText;
    public Circle drawButton;
    public Circle unoButton;
    public Rectangle closeButton;
    public Circle blueColor;
    public Circle redColor;
    public Circle greenColor;
    public Circle yellowColor;
    public Rectangle chooseColor;
    public Rectangle waitBox;


    public List<ImageView> bottom_imv = new ArrayList<>();

    public List<Text> allSizeText = new ArrayList<>();


    Random rand = new Random();

    sample.CardObjects cardObjects = new sample.CardObjects();
    public static Cards lastCardPlayed;

    static {
        try {
            lastCardPlayed = new Cards();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static int lastSize;
    public static int turn=0;
    public static List<Cards> bottom = new ArrayList<>();
    public static boolean closeMain = false;

    int all = genRanAll();
    int tempAll = genRanAll();
    int suit = rand.nextInt(13) + 0;
    int tempSuit = rand.nextInt(13) + 0;


    boolean start;
    boolean saidUno;
    public static boolean firstMove;

    public static String stringFromServer;
    public static String gameOn;
    public static String stringToServer;
    public static int myID;
    public static int playerID;
    public static int move = 0;


    //public ControllerUNO() throws MalformedURLException {
    //}

    public ControllerUNO() throws MalformedURLException {
    }


    ;

    @FXML
    public void initialize() throws MalformedURLException, IOException {

        bottom_imv.add(bottom_imv1);
        bottom_imv.add(bottom_imv2);
        bottom_imv.add(bottom_imv3);
        bottom_imv.add(bottom_imv4);
        bottom_imv.add(bottom_imv5);
        bottom_imv.add(bottom_imv6);
        bottom_imv.add(bottom_imv7);

        for (int i = 0; i < bottom_imv.size(); i++) {
            int finalI = i;
            bottom_imv.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    dealCard(finalI);
                    event.consume();
                }
            });
        }
        Networking.getParentController(this);
    }

    public void handleParentStackClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (!start) {
                bottom.clear();
                for (int i = 0; i < 7; i++) {
                    generateRandom();
                    bottom_imv.get(i).setImage(cardObjects.allCards[all][suit].getImage());
                    bottom.add(cardObjects.allCards[all][suit]);
                }
                start = true;

            }
        }
/*
        if (event.getButton() == MouseButton.SECONDARY) {

            if (!gameOn.equals("wait")) {//server sent each client his id
                myID = Integer.parseInt(gameOn);
                System.out.println("@@@@ myID " + myID);
                playerIdText.setVisible(true);
                playerIdText.setText("player " + myID );
                showColor.setText(lastCardPlayed.getColor());
                waitText.setVisible(false);
                waitBox.setVisible(false);
                System.out.println("@@@ lastcard " + lastCardPlayed.getColor() + " " + lastCardPlayed.getValue());
                gameplay();
            }  else {
                waitBox.setVisible(true);
                waitText.setVisible(true);
                for (int i = 0; i < bottom_imv.size(); i++) {
                    bottom_imv.get(i).setMouseTransparent(true);
                }
                unoButton.setMouseTransparent(true);
            }
            text.setText("turn " + turn);
        }
*/
    }

    public void handleWin(MouseEvent event)
    {
        Stage stage = (Stage) parentStack.getScene().getWindow();
        System.exit(0);
        stage.close();
    }
    public void dealCard(int i) {

        System.out.println("@@@ dealCard called");

        //very first move
        if (lastCardPlayed.getValue() == -1) {
            setCenterCard(i);
            //sending the last card to server
            stringToServer = myID + "#" + lastCardPlayed.getColor() + "#" + lastCardPlayed.getValue() + "#" + bottom.size();
            Networking.send(stringToServer);
            System.out.println("@@@ stringToServer " + stringToServer);
            firstMove = true;
            move++;
        } else if (bottom.get(i).getColor().equals("wild")) {
            setCenterCard(i);
            win();
            //sending the last card to server
            stringToServer = myID + "#" + lastCardPlayed.getColor() + "#" + lastCardPlayed.getValue() + "#" + bottom.size();
            Networking.send(stringToServer);
            System.out.println("@@@ stringToServer " + stringToServer);
            move++;
        } else { //every move afterwards

            System.out.println("@@@@ every move afterwards");
            System.out.println("@@@ lastcard in dealCard " + lastCardPlayed.getColor() + " " + lastCardPlayed.getValue());
            System.out.println("@@@ selected card in dealCard " + bottom.get(i).getColor() + " " + bottom.get(i).getValue());

            if (lastCardPlayed.getColor().equals(bottom.get(i).getColor()) || lastCardPlayed.getValue() == bottom.get(i).getValue()) {
                System.out.println("@@@@ follows rule");
                setCenterCard(i);
                //sending the last card to server
                stringToServer = myID + "#" + lastCardPlayed.getColor() + "#" + lastCardPlayed.getValue() + "#" + bottom.size();
                Networking.send(stringToServer);
            System.out.println("@@@ stringToServer " + stringToServer);
                move++;

            }
            win();
        }
    }

    public void leftShift(int i) {
        System.out.println("@@@ leftShift called");
        for (int j = i; j < bottom.size() - 1; j++) {
            bottom_imv.get(j).setImage(bottom_imv.get(j + 1).getImage());
            bottom.set(j, bottom.get(j + 1)); // replacing each element with the next one
            System.out.println("@@@@ j " + j);
        }
        bottom_imv.get(bottom.size() - 1).imageProperty().set(null);
        System.out.println("@@@@@@@ bottom imv list size 3 " + bottom_imv.size());

        bottom.remove(bottom.size() - 1);
        System.out.println("@@@@@@@ bottom list size 3 " + bottom.size());
    }

    public void setCenterCard(int i) {
        System.out.println("@@@ setCenterCard called");
        handleWildCard(i);
        System.out.println("@@@@@@ bottom list color" + bottom.get(i).getColor());
        //center_imv.setImage(bottom_imv.get(i).getImage());
        for (int m = 0; m < bottom.size(); m++) {
            bottom_imv.get(m).setMouseTransparent(true);
        }
        lastCardPlayed = bottom.get(i);//the  object to transfer : color,image,value
        System.out.println("@@@@@ lastCardPlayed color " + lastCardPlayed.getColor());
        //lastSelectedIndex = i; // the index of selected card
        leftShift(i);
    }

    public void handleDrawMouseEntered(MouseEvent event) {
        if (drawButton.isHover()) {
            drawButton.setFill(Paint.valueOf("#5E4EAF"));
        }
    }

    public void handleDrawMouseExited(MouseEvent event) {
        drawButton.setFill(Paint.valueOf(("#1e90ff")));
    }

    public void handleDrawMousePressed(MouseEvent event) {
        if (myID==turn) {
            draw(1);
            if (lastCardPlayed.getValue() == 11 || lastCardPlayed.getValue() == 12) {
                stringToServer = myID + "#" + lastCardPlayed.getColor() + "#" + (lastCardPlayed.getValue() + 10) + "#" + bottom.size();
            } else {
                stringToServer = myID + "#" + lastCardPlayed.getColor() + "#" + lastCardPlayed.getValue() + "#" + bottom.size();
            }
            Networking.send(stringToServer);
        }
    }

    public void handleUnoMouseEntered(MouseEvent event) {
        if (unoButton.isHover()) {
            if (!saidUno)
                unoButton.setFill(Paint.valueOf("#ffef21"));
            /*else
                unoButton.setFill(Paint.valueOf("#2d780f"));*/
        }
    }

    public void handleUnoMouseExited(MouseEvent event) {

        if (!saidUno)
            unoButton.setFill(Paint.valueOf(("#ff1f1f")));
        else
            unoButton.setFill(Paint.valueOf(("#21ff57")));
    }

    public void handleUnoMousePressed(MouseEvent event) {

        if (bottom.size() == 2) {
            saidUno = true;
            unoButton.setFill(Paint.valueOf(("#21ff57")));
        }

    }

    public void gameplay() {
        System.out.println("@@@@ gameplay runs");

        if (myID == turn) {
            for (int m = 0; m < bottom_imv.size(); m++) {
                bottom_imv.get(m).setMouseTransparent(false);
            }
            unoButton.setMouseTransparent(false);

            //handling plus two
            if (lastCardPlayed.getValue() == 10) {
                draw(1);
                draw(1);
            }
            //handling plus four
            else if (lastCardPlayed.getValue() == 14) {
                for (int i = 0; i < 4; i++) {
                    draw(1);
                }
            } else {
                noMatchCase();
            }
            //handling uno
            if (bottom.size() < 2 && !saidUno) {
                draw(1);
                draw(1);
            }
            if (bottom.size() >= 2) {
                saidUno = false;
                unoButton.setFill(Paint.valueOf("#ff1f1f"));
            }
        } else {
            for (int m = 0; m < bottom.size(); m++) {
                bottom_imv.get(m).setMouseTransparent(true);
            }
            unoButton.setMouseTransparent(true);
        }

    }

    public void noMatchCase () {
        System.out.println("@@@ noMatchCase called");
        boolean cardFound = false;
        //handling no match case
        for (int i = 0; i < bottom.size(); i++) {
            if (bottom.get(i).getValue() == lastCardPlayed.getValue() || bottom.get(i).getColor().equals(lastCardPlayed.getColor()) || bottom.get(i).getValue() == 13 || bottom.get(i).getValue() == 14)
                cardFound = true;
            System.out.println("@@@ cardFound " + bottom.get(i).getColor() + " " + bottom.get(i).getValue());
        }
        if (!cardFound) {
            FillTransition ft = new FillTransition(Duration.millis(1000), drawButton, Color.PINK, Color.BLUE);
            ft.setCycleCount(4);
            ft.setAutoReverse(true);

            ft.play();
        }
    }

    public void generateRandom () {
        while (tempAll == all && tempSuit == suit) {
            tempAll = genRanAll();
            tempSuit = rand.nextInt(13) + 0;
        }
        if (tempAll == 4) {
            tempSuit = rand.nextInt(2) + 0;
        }
        all = tempAll;
        suit = tempSuit;


    }

    public int genRanAll () {
        double rng = rand.nextDouble();
        if (rng < .1) {
            return 4;//change this value to 4 for wild cards
        } else {
            return rand.nextInt(4) + 0;
        }
    }

    public void draw ( int number){
        System.out.println("@@@ draw called");
        for (int i = 0; i < number; i++) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ImageView img = new ImageView();
                    img.setFitWidth(65);
                    img.setPreserveRatio(true);
                    generateRandom();
                    //there are already seven existing imageViews
                    if (bottom.size() == 7) {
                        bottom_imv.add(img);
                        bottom_imv.get(bottom.size()).setImage(cardObjects.allCards[all][suit].getImage());
                        bottomGrid.addColumn(bottom.size(), bottom_imv.get(bottom.size()));
                    } else if (bottom.size() >= 8) {
                        bottom_imv.add(img);
                        bottom_imv.get(bottom.size()).setImage(cardObjects.allCards[all][suit].getImage());
                        bottomGrid.addColumn(bottom.size() - 8, bottom_imv.get(bottom.size()));
                    } else {
                        bottom_imv.get(bottom.size()).setImage(cardObjects.allCards[all][suit].getImage());
                    }
                    bottom.add(cardObjects.allCards[all][suit]);
                    for (int j = 7; j < bottom_imv.size(); j++) {
                        int finalI = j;
                        bottom_imv.get(j).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                dealCard(finalI);
                                event.consume();
                            }
                        });
                    }
                }
            });

        }


        System.out.println("@@@@@ bottom list size " + bottom.size());


    }

    public void handleWildCard ( int i){
        if (bottom.get(i).getColor().equals("wild")) {
            //setChooseColor(true);
            int finalI = i;
            String s1 = JOptionPane.showInputDialog(null, "enter color (red/blue/green/yellow)");
            bottom.get(i).setColor(s1);

            /*blueColor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bottom.get(finalI).setColor("blue");
                    setChooseColor(false);
                    event.consume();
                }
            });
            redColor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bottom.get(finalI).setColor("red");
                    setChooseColor(false);
                    event.consume();
                }
            });
            greenColor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bottom.get(finalI).setColor("green");
                    setChooseColor(false);
                    event.consume();
                }
            });
            yellowColor.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bottom.get(finalI).setColor("yellow");
                    setChooseColor(false);
                    event.consume();
                }
            });*/
            System.out.println("@@@ inside func bottom list color " + bottom.get(i).getColor());

        }

    }

    public void setChooseColor ( boolean value){
        colorTxt.setVisible(value);
        chooseColor.setVisible(value);
        chooseColor.setDisable(!value);
        blueColor.setVisible(value);
        blueColor.setDisable(!value);
        redColor.setVisible(value);
        redColor.setDisable(!value);
        greenColor.setVisible(value);
        greenColor.setDisable(!value);
        yellowColor.setVisible(value);
        yellowColor.setDisable(!value);
        bottomGrid.setDisable(value);
    }

    public void win () {

        if(myID==turn) {
            if (bottom.isEmpty()) {
                center_imv.imageProperty().set(null);
                lastCardPlayed.setValue(-1);
                lastCardPlayed.setColor("black");
                start = false;
                bottomGrid.setMouseTransparent(true);
                unoButton.setMouseTransparent(true);
                drawButton.setMouseTransparent(true);

                closeText.setVisible(true);
                closeText.setDisable(false);
                closeButton.setVisible(true);
                closeButton.setDisable(false);
                winText.setVisible(true);
                stringToServer = myID + "#win#-7#-7";
                Networking.send(stringToServer);
            }
        }
        if(lastCardPlayed.getColor().equals("win"))
        {
            center_imv.imageProperty().set(null);
            lastCardPlayed.setValue(-1);
            lastCardPlayed.setColor("black");
            //start = false;
            bottomGrid.setMouseTransparent(true);
            unoButton.setMouseTransparent(true);
            drawButton.setMouseTransparent(true);

            closeText.setVisible(true);
            closeText.setDisable(false);
            closeButton.setVisible(true);
            closeButton.setDisable(false);
            winText.setText("player " + playerID + " has won!!");
            winText.setVisible(true);
        }

    }

    public void setAllSizeText(int id)
    {
        if (id == 0)
        {
            allSizeText.add(bottomSizeText);
            allSizeText.add(rightSizeText);
            allSizeText.add(topSizeText);
            allSizeText.add(leftSizeText);
        }

        else if(id==1) {
            allSizeText.add(leftSizeText);
            allSizeText.add(bottomSizeText);
            allSizeText.add(rightSizeText);
            allSizeText.add(topSizeText);
        }
        else if(id == 2)
        {
            allSizeText.add(topSizeText);
            allSizeText.add(leftSizeText);
            allSizeText.add(bottomSizeText);
            allSizeText.add(rightSizeText);
        }
        else if (id==3)
        {
            allSizeText.add(rightSizeText);
            allSizeText.add(topSizeText);
            allSizeText.add(leftSizeText);
            allSizeText.add(bottomSizeText);
        }
    }
    public void setLastPlayerCardCount(int playID,int size)
    {
           allSizeText.get(playID).setText("Cards Left : " + size);

    }
    public void closeWindow()
    {
        Stage stage = (Stage) parentStack.getScene().getWindow();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.setImplicitExit(false);
                System.out.println("@@@ close request");
                System.exit(0);
                stage.close();
            }
        });
    }


}

