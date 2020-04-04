package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.Collections;

import static java.lang.Thread.sleep;


public class ControllerTTT {


    public GridPane gridPane;
    public boolean[][] crossClicked = new boolean[3][3];
    public boolean[][] dotClicked = new boolean[3][3];
    public boolean crosswins;
    public boolean dotwins;

    //Socket clientSocket = new Socket("localhost", 6789);
    //Thread t = new Thread(new Networking(clientSocket));
        //t.start();


    Image cross = new Image(new File("src\\sample\\cross2.png").toURI().toURL().toString(), 80, 80, true, true);

    Image dot = new Image(new File("src\\sample\\circle.png").toURI().toURL().toString(), 80, 80, true, true);

    public int play = 1;
    public boolean game = true;
    public double firstX=0,firstY=0,lastX=0,lastY=0,translateX=0,translateY=0;

    public StackPane parentStack,stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9;
    public StackPane[] stackPanes = {stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9};
    public Line line;

    public ControllerTTT() throws IOException {
    }


    public void handleMouseClick(MouseEvent event) throws IOException, InterruptedException {
        if (stack1.isPressed()) {
            if (play % 2 == 0) {
                dotClicked[0][0] = true;
            } else
                crossClicked[0][0] = true;

            gameplay(stack1);
            win();

        } else if (stack2.isPressed()) {

            if (play % 2 == 0)
                dotClicked[0][1] = true;
            else
                crossClicked[0][1] = true;

            gameplay(stack2);
            win();
        } else if (stack3.isPressed()) {
            if (play % 2 == 0)
                dotClicked[0][2] = true;
            else
                crossClicked[0][2] = true;

            gameplay(stack3);
            win();
        } else if (stack4.isPressed()) {

            if (play % 2 == 0)
                dotClicked[1][0] = true;
            else
                crossClicked[1][0] = true;

            gameplay(stack4);
            win();
        }
        if (stack5.isPressed()) {

            if (play % 2 == 0)
                dotClicked[1][1] = true;
            else
                crossClicked[1][1] = true;

            gameplay(stack5);
            win();
        }
        if (stack6.isPressed()) {

            if (play % 2 == 0)
                dotClicked[1][2] = true;
            else
                crossClicked[1][2] = true;

            gameplay(stack6);
            win();
        }
        if (stack7.isPressed()) {

            if (play % 2 == 0)
                dotClicked[2][0] = true;
            else
                crossClicked[2][0] = true;

            gameplay(stack7);
            win();
        }
        if (stack8.isPressed()) {
            if (play % 2 == 0)
                dotClicked[2][1] = true;
            else
                crossClicked[2][1] = true;

            gameplay(stack8);
            win();

        }
        if (stack9.isPressed()) {

            if (play % 2 == 0)
                dotClicked[2][2] = true;
            else
                crossClicked[2][2] = true;

            gameplay(stack9);
            win();
        }
    }

    public void gameplay(StackPane stk) {
        if (game) {
            if (play % 2 != 0) {
                stk.getChildren().add(new ImageView(cross));
                stk.setMouseTransparent(true);

            } else {
                stk.getChildren().add(new ImageView(dot));
                stk.setMouseTransparent(true);
            }
            play++;


        }


    }

    public void win() throws IOException, InterruptedException {

        for (int x = 0; x < 3; x++) {
            if (crossClicked[x][0] && crossClicked[x][1] && crossClicked[x][2])
            {
                crosswins = true;
                firstX = -100;
                lastX = 100;
                translateY = -100 + (x*100);
            }

            if (crossClicked[0][x] && crossClicked[1][x] && crossClicked[2][x])
            {
                crosswins = true;
                firstY = 100;
                lastY = -100;
                translateX = -100 + (x*100);
            }

        }

        if (crossClicked[0][0] && crossClicked[1][1] && crossClicked[2][2]) {
            crosswins = true;
            firstX = 100;
            lastX = -100;
            firstY = 100;
            lastY = -100;
        }
        if (crossClicked[0][2] && crossClicked[1][1] && crossClicked[2][0]) {
            crosswins = true;
            firstX = 100;
            lastX = -100;
            firstY = -100;
            lastY = 100;
        }

        if (crosswins) {
            game = false;
            animation();

            //sleep(2000);

            Parent Start = FXMLLoader.load(getClass().getResource("TicTacToeFinished.fxml"));
            Scene scene = new Scene(Start);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.show();

            //display("Game Over", "Player 1 won!");
        }


        for (int x = 0; x < 3; x++) {
            if (dotClicked[x][0] && dotClicked[x][1] && dotClicked[x][2])
            {
                dotwins = true;
                firstX = -100;
                lastX = 100;
                translateY = -100 + (x*100);
            }

            if (dotClicked[0][x] && dotClicked[1][x] && dotClicked[2][x])
            {
                dotwins = true;
                firstY = 100;
                lastY = -100;
                translateX = -100 + (x*100);
            }

        }
        if (dotClicked[0][0] && dotClicked[1][1] && dotClicked[2][2]) {
            dotwins = true;
            firstX = 100;
            lastX = -100;
            firstY = 100;
            lastY = -100;
        }
        if (dotClicked[0][2] && dotClicked[1][1] && dotClicked[2][0]) {
            dotwins = true;
            firstX = 100;
            lastX = -100;
            firstY = -100;
            lastY = 100;
        }


        if (play >= 10 && !crosswins && !dotwins) {
            game = false;

            Parent Start = FXMLLoader.load(getClass().getResource("drawTTT.fxml"));
            Scene scene = new Scene(Start);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.show();

            //display("Game Over", "DRAW");
        }
        if (dotwins) {
            game = false;
            animation();

            //sleep(2000);

            Parent Start = FXMLLoader.load(getClass().getResource("TicTacToeFinished2.fxml"));
            Scene scene = new Scene(Start);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.setMinHeight(400);
            stage.show();

            //display("Game Over", "Player 2 won!");
            System.out.println("player 2 won!");
        }
        if (!game) {
            setAllTransparent(true);
        }

    }

    public void playagain()
    {
        game = true;
        play = 1;
        crosswins = false;
        dotwins = false;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                crossClicked[i][j] = false;
                dotClicked[i][j] = false;
            }
        }
        stack1.getChildren().clear();
        stack2.getChildren().clear();
        stack3.getChildren().clear();
        stack4.getChildren().clear();
        stack5.getChildren().clear();
        stack6.getChildren().clear();
        stack7.getChildren().clear();
        stack8.getChildren().clear();
        stack9.getChildren().clear();
        parentStack.getChildren().remove(line);

        setAllTransparent(false);
    }


    public void display(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Play again");
        closeButton.setOnAction(e -> {
            window.close();
            playagain();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void setAllTransparent(boolean value)
    {
        stack1.setMouseTransparent(value);
        stack2.setMouseTransparent(value);
        stack3.setMouseTransparent(value);
        stack4.setMouseTransparent(value);
        stack5.setMouseTransparent(value);
        stack6.setMouseTransparent(value);
        stack7.setMouseTransparent(value);
        stack8.setMouseTransparent(value);
        stack9.setMouseTransparent(value);
    }

    public void animation()
    {
        line.setVisible(true);
        line.setStartX(firstX);
        line.setStartY(firstY);
        line.setEndX(firstX);
        line.setEndY(firstY);
        line.setTranslateX(translateX);
        line.setTranslateY(translateY);


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.startXProperty(), lastX),
                new KeyValue(line.startYProperty(), lastY)));
        timeline.play();
    }
}