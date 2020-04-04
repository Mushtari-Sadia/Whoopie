package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.Socket;

public class MainUNO extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Socket clientSocket = new Socket("172.20.53.7", 6788);
        Thread t = new Thread(new Networking(clientSocket));
        t.start();

        primaryStage.setTitle("Uno");
        primaryStage.setScene(new Scene(root, 1250, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
