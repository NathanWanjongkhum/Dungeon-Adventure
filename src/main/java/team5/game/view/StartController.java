package team5.game.view;

import javafx.fxml.FXML;
import team5.game.App;

import java.io.IOException;

import javafx.event.ActionEvent;
 

public class StartController {

     @FXML
     void exitGame(ActionEvent event) {
 
     }
 
     @FXML
     void loadGame(ActionEvent event) {
 
     }
 
     @FXML
     void switchToHeroCreation(ActionEvent event) throws IOException {
        App.setRoot("HeroSelection");
     }

}
