package sample;

import javafx.application.Platform;

public class flameController {

        public void exitButtonClicked(){
            Platform.exit();
            System.exit(0);
        }
}
