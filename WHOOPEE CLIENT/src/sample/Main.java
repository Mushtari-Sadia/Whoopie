package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

//import javax.print.attribute.standard.Media;
//import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        primaryStage.setTitle("Whoopee");
        primaryStage.setScene(new Scene(root, 1000, 749));
        primaryStage.show();

    }


    public static void main(String[] args) {
        //String musicFile = "H:\\WumpusWorld\\src\\sample\\castle.mp3";     // For example
        //Media sound = new Media(new File(musicFile).toURI().toString());
       // MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();
        launch(args);

    }
}
