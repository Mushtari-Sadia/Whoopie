package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.omg.CORBA.ACTIVITY_COMPLETED;
import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.event.ActionEvent;
import java.io.IOException;

public class instructionsController {

    public void ticTacToeClicked(ActionEvent event) throws IOException {
        Parent Start = FXMLLoader.load(getClass().getResource("instructionsTTT.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void wumpusWorldClicked(ActionEvent event) throws IOException {
        Parent Start = FXMLLoader.load(getClass().getResource("instructionsWumpusWorld.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void unoClicked(ActionEvent event) throws IOException {
        Parent Start = FXMLLoader.load(getClass().getResource("instructionsUNO.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void backButtonClicked(ActionEvent event) throws IOException {
        Parent Start = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

}
