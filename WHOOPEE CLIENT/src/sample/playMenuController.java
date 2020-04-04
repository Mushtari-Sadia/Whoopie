package sample;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.Socket;

public class playMenuController {

    public void TicTacToeButtonClicked(ActionEvent event)throws IOException{
        Parent Start = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(600);
        stage.show();
    }

    public void WumpusWorldClicked(ActionEvent event) throws IOException {

        Parent Start = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();
    }

    public void UnoClicked(ActionEvent event) throws IOException {
        Parent Start = FXMLLoader.load(getClass().getResource("sampleUNO.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();

        Socket clientSocket = new Socket("172.20.53.7", 6788);
        Thread t = new Thread(new Networking(clientSocket));
        t.start();

        /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Platform.setImplicitExit(false);
                System.out.println("@@@ close request");

                try {
                    clientSocket.close();
                    System.out.println("clientSocket closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                t.interrupt();
                stage.close();
            }
        });*/
    }

    public void backButtonClicked(ActionEvent event) throws IOException {

        Parent Start = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }


}
