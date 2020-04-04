package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class wumpusController {

    public void exitButtonClicked(ActionEvent event)throws IOException{

        /*Parent Start = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene scene = new Scene(Start);
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();

        stage.setScene(scene);
        stage.show();*/

        Platform.exit();
        System.exit(0);


    }

}
