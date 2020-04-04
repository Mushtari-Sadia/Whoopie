package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class mainMenuController {

    public void playButtonClicked(ActionEvent event) throws IOException {

        Parent Start = FXMLLoader.load(getClass().getResource("playMenu.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    public void instructionButtonClicked(ActionEvent event)throws IOException{

        Parent Start = FXMLLoader.load(getClass().getResource("instructions.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    public void creditButtonClicked(ActionEvent event)throws IOException{

        Parent Start = FXMLLoader.load(getClass().getResource("CreditsController.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    public void exitButtonClicked(ActionEvent event) throws IOException {

        //Parent GO = FXMLLoader.load(getClass().getResource("GameOverview.fxml"));
        //Scene scene = new Scene(GO);

        //stage.setScene(scene);
        //stage.show();
        Platform.exit();
        System.exit(0);
    }

}
