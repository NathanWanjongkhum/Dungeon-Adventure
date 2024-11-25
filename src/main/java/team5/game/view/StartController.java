package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import team5.game.App;

public class StartController {

   @FXML
   private VBox myScene;

   @FXML
   void exitGame(ActionEvent event) {
      
   }

   @FXML
   void loadGame(ActionEvent event) {

   }

   @FXML
   void switchToHeroCreation(ActionEvent event) throws IOException {
      App.setRoot("NameSelection");
   }

}
