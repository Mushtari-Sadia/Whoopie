package sample;

import javafx.application.Platform;

import javafx.event.ActionEvent;

public class playerTwoWin {

    public void exitButtonClicked(ActionEvent event){

        Platform.exit();
        System.exit(0);

    }

}
