package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class pitController {

    public void exitButtonClicked(ActionEvent event)throws IOException{

        Platform.exit();
        System.exit(0);

    }

}
