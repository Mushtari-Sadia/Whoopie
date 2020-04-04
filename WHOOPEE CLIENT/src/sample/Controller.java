package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Random;

public class Controller {

        public Window window;
        public Player player=new Player();
        public Position position=new Position();
        public Random random=new Random();
        public int wumpus_x=random.nextInt(7)+1;
        public int wumpus_y=random.nextInt(7)+1;
        public int gold_x=random.nextInt(7)+1;
        public int gold_y=random.nextInt(7)+1;
        public int pit_x=random.nextInt(7)+1;
        public int pit_y=random.nextInt(7)+1;
        public int pit2_x=random.nextInt(7)+1;
        public int pit2_y=random.nextInt(7)+1;
        public Wumpus wumpus=new Wumpus(wumpus_x,wumpus_y);
        public WumpusWorld wumpusWorld=new WumpusWorld(wumpus_x,wumpus_y,gold_x,gold_y,pit_x,pit_y,pit2_x,pit2_y);

        public Button turnLeft=new Button();
        public Button turnRight=new Button();
        public Button moveForward=new Button();
        public Button shootArrow=new Button();
        public Label currentPosition=new Label(player.getPositionInfo());
        public Label arrows=new Label();
        public Label playerFacing=new Label(player.getDirectionInfo());
        public Label turnCount=new Label();
        public Label alert=new Label();
        public Label shoot=new Label();
        public Label wumpuslife=new Label();
        public Label gameStatus=new Label();
        public Label label=new Label();
        public Text text=new Text();
        Image flameImage = new Image(new File("src\\sample\\flame.png").toURI().toURL().toString(), 75, 75, true, true);
        Image pitImage = new Image(new File("src\\sample\\pit2.png").toURI().toURL().toString(), 80, 80, true, true);
        Image wumpusImage = new Image(new File("src\\sample\\wumpus2.png").toURI().toURL().toString(), 80, 80, true, true);
        Image goldImage = new Image(new File("src\\sample\\gold2.png").toURI().toURL().toString(), 80, 70, true, true);
        Image upImage = new Image(new File("src\\sample\\up2.png").toURI().toURL().toString(), 80, 72, false, true);
        Image downImage = new Image(new File("src\\sample\\down2.png").toURI().toURL().toString(), 80, 72, false, true);
        Image leftImage = new Image(new File("src\\sample\\left2.png").toURI().toURL().toString(), 80, 72, false, true);
        Image rightImage = new Image(new File("src\\sample\\right2.png").toURI().toURL().toString(), 80, 72, false, true);
        Image grassImage=new Image(new File("src\\sample\\cutGrass.jpg").toURI().toURL().toString(), 85, 85, false, true);

        //public ImageView whiteImageView=new ImageView(whiteImage);
        public ImageView img=new ImageView();
        public GridPane gridPane=new GridPane();
        public int arrowCount=3;
        public int turncounter=0;

    public Controller() throws MalformedURLException {
    }

    public void turningLeft(ActionEvent event)
        {   try {
            gridPane.getChildren().remove(img);

            //imageView.setImage(playerImage);
            //removeImage();
            player.turnLeft();
            turncounter++;
            turnCount.setText("Turn Count: " + turncounter);
            currentPosition.setText(player.getPositionInfo());
            playerFacing.setText(player.getDirectionInfo());
            //gridPane.add(imageView,player.getX(),player.getY());
            addImage();
            gridPane.getChildren().remove(text);
            gamePlay();
        }
        catch (Exception e)
        {   //alert.setText("this.you are not allowed to do that");
            System.out.println(e);
        }
        }

        public void turningRight(ActionEvent event) throws IOException {
            try {
                gridPane.getChildren().remove(img);
                //imageView.setImage(playerImage);
                //removeImage();
                player.turnRight();
                turncounter++;
                turnCount.setText("Turn Count: " + turncounter);
                currentPosition.setText(player.getPositionInfo());
                playerFacing.setText(player.getDirectionInfo());
                addImage();
                //gridPane.add(imageView,player.getX(),player.getY());
                gridPane.getChildren().remove(text);
                gamePlay();
                }
            catch (Exception e)
            {   //alert.setText("this.you are not allowed to do that");
                System.out.println(e);
            }
        }

        public void movingForward(ActionEvent event) throws IOException
        {   try {
            gridPane.getChildren().remove(img);
            ImageView imageView=new ImageView();
            imageView.setImage(grassImage);
            gridPane.add(imageView,player.getX(),player.getY());
            //removeImage();
            //imageView.setImage(playerImage);
            player.moveForward();
            //gridPane.add(whiteImageView,player.getX(),player.getY());
            turncounter++;
            turnCount.setText("Turn Count: " + turncounter);
            currentPosition.setText(player.getPositionInfo());
            playerFacing.setText(player.getDirectionInfo());

            //gridPane.add(imageView,player.getX(),player.getY());

            addImage();
            gridPane.getChildren().remove(text);
            //gridPane.add(imageView,player.getX(),player.getY());
            gamePlay();
        }
        catch (Exception e)
        {   //alert.setText("You are not allowed to do that");
            System.out.println(e);
        }

        }

        public void shootArrow(ActionEvent event)
        {
            turncounter++;
            turnCount.setText("Turn Count: " + turncounter);
            int pl_x=player.player_position().getX();
            int pl_y=player.player_position().getY();
            int wmp_x=wumpus.getPosition().getX();
            int wmp_y=wumpus.getPosition().getY();

            if(arrowCount==0)
            {
                shoot.setText("No arrows left");
            }

            else if(!(wumpus.wumpus_killed())){

                arrowCount--;
                arrows.setText("Arrows Left: "+arrowCount);

                if(player.player_direction()==2)
                {
                    if((pl_x == wmp_x)&&(pl_y < wmp_y))
                        wumpus.kill();
                }

                else if(player.player_direction()==0)
                {
                    if((pl_x == wmp_x)&&(pl_y > wmp_y))
                        wumpus.kill();
                }

                else if(player.player_direction()==3)
                {
                    if((pl_y == wmp_y)&&(pl_x > wmp_x))
                        wumpus.kill();
                }

                else if(player.player_direction()==1)
                {
                    if((pl_y == wmp_y)&&(pl_x < wmp_x))
                        wumpus.kill();
                }

                if(wumpus.wumpus_killed())
                    wumpuslife.setText("Wumpus Life: Wumpus is dead");

                else
                    shoot.setText("Not effective");

            }

            else if(wumpus.wumpus_killed())
                shoot.setText("Wumpus is already dead");
        }

        public void gamePlay() throws Exception {
            alert.setText("Alert: Nothing to worry");
                if(player.isAdjacent(wumpusWorld.getPit_position())&&player.isAdjacent(wumpus.getPosition())&&player.isAdjacent(wumpusWorld.flame_position)&&(wumpus.wumpus_killed()==false))
                {
                    addLabel("pit and wumpus and heat");
                    alert.setText("Alert: Breeze and Heat and Stench!!");
                }

                else if(player.isAdjacent(wumpusWorld.getFlame_position())&&player.isAdjacent(wumpusWorld.getPit_position())){
                    addLabel("pit and heat");
                    alert.setText("Alert: Breeze and Heat!!");
                }

                else if(player.isAdjacent(wumpusWorld.getFlame_position())&&player.isAdjacent(wumpus.getPosition())&&wumpus.wumpus_killed()==false){
                    addLabel("heat and wumpus");
                    alert.setText("Alert: Heat and Stench!!");
                }

                else if(player.isAdjacent(wumpusWorld.getPit_position())&&player.isAdjacent(wumpus.getPosition())&&wumpus.wumpus_killed()==false){
                    addLabel("pit and wumpus");
                    alert.setText("Alert: Breeze and Stench!!");
                }
                else if(player.isAdjacent(wumpusWorld.getPit_position()))
                {
                    addLabel("pit");
                    alert.setText("Alert: Breeze!!");
                }

                else if(player.isAdjacent(wumpusWorld.getFlame_position())){
                    addLabel("heat");
                    alert.setText("Alert: Heat!!");
                }

                else if(player.isAdjacent(wumpus.getPosition())&&(wumpus.wumpus_killed()==false))
                {   addLabel("wumpus");
                    alert.setText("Alert: Stench!!");
                }

                if(player.isSamePoint(wumpus.getPosition())&&(wumpus.wumpus_killed()==false))
                {   //ActionEvent event=null;
                    Parent Start = FXMLLoader.load(getClass().getResource("wumpusEaten.fxml"));
                    Scene scene = new Scene(Start);
                    //Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
                    Stage stage=new Stage();

                    stage.setScene(scene);
                    stage.setMinWidth(600);
                    stage.setMinHeight(400);
                    stage.show();

                    setAllTransparent(true);

                    alert.setText("Alert: Player is dead");
                }

                else if(player.isSamePoint(wumpusWorld.getPit_position()))
                {
                    //Platform.exit();
                    Parent Start = FXMLLoader.load(getClass().getResource("fallenIntoPit.fxml"));
                    Scene scene = new Scene(Start);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.show();

                    setAllTransparent(true);

                    //Platform.exit();

                    alert.setText("Player has fallen inside the pit.");
                }

                else if(player.isSamePoint(wumpusWorld.getFlame_position())){
                    Parent Start = FXMLLoader.load(getClass().getResource("flame.fxml"));
                    Scene scene = new Scene(Start);

                    setAllTransparent(true);

                    Stage stage = new Stage();

                    //stage.close();
                    stage.setScene(scene);
                    stage.show();
                }

                else if(player.isSamePoint(wumpusWorld.getGold_position()))
                {
                    Parent Start = FXMLLoader.load(getClass().getResource("goldAchieved.fxml"));

                    setAllTransparent(true);

                    Scene scene = new Scene(Start);
                    Stage stage = new Stage();

                    //stage.close();
                    stage.setScene(scene);
                    stage.show();

                    gameStatus.setText("Game Status: Congratulations! You've won the game.");
                }

        }

        public void showEverything(ActionEvent event)
        {
            gridPane.getChildren().remove(img);

            ImageView img1=new ImageView();
            ImageView img2=new ImageView();
            ImageView img3=new ImageView();
            ImageView img4=new ImageView();
            ImageView img5=new ImageView();

            addImage();
            img1.setImage(flameImage);
            img2.setImage(wumpusImage);
            img3.setImage(pitImage);
            img4.setImage(goldImage);
            img5.setImage(pitImage);

            gridPane.add(img1,wumpusWorld.getFlame_position().getX(),wumpusWorld.getFlame_position().getY());
            gridPane.add(img2,wumpus.getPosition().getX(),wumpus.getPosition().getY());
            gridPane.add(img3,wumpusWorld.getPit_position().getX(),wumpusWorld.getPit_position().getY());
            //gridPane.add(img5,wumpusWorld.getFlame_position().getX(),wumpusWorld.getFlame_position().getY());
            gridPane.add(img4,wumpusWorld.getGold_position().getX(),wumpusWorld.getGold_position().getY());
        }

        public void addImage(){

            if(player.player_direction()==0){
                img.setImage(upImage);
                gridPane.add(img,player.getX(),player.getY());
            }
            else if(player.player_direction()==2){
                img.setImage(downImage);
                gridPane.add(img,player.getX(),player.getY());
            }
            else if(player.player_direction()==3){
                img.setImage(leftImage);
                gridPane.add(img,player.getX(),player.getY());
            }
            else if(player.player_direction()==1){
                img.setImage(rightImage);
                gridPane.add(img,player.getX(),player.getY());
            }
        }

        public void addLabel(String string){
            if(string.equals("pit")){
                text.setText("BREEZE!");
            }
            else if(string.equals("wumpus")){
                text.setText("STENCH!!");
            }
            else if(string.equals("heat")){
                text.setText("HEAT!!");
            }
            else if(string.equals("pit and wumpus")){
                text.setText("BREEZE AND STENCH!!");
            }
            else if(string.equals("pit and heat")){
                text.setText("BREEZE AND HEAT!!");
            }
            else if(string.equals("heat and wumpus")){
                text.setText("HEAT AND STENCH!!");
            }
            else if(string.equals("pit and wumpus and heat")){
                text.setText("BREEZE AND HEAT AND STENCH!!");
            }

            text.setFill(Color.WHITE);
            text.setFont(new Font("Lucida",18));
            text.setTextAlignment(TextAlignment.CENTER);
            gridPane.add(text,player.getX(),player.getY());
        }

        public void setAllTransparent(Boolean value){
            turnLeft.setMouseTransparent(true);
            turnRight.setMouseTransparent(true);
            moveForward.setMouseTransparent(true);
            shootArrow.setMouseTransparent(true);
        }
}
