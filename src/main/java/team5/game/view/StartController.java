package team5.game.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import team5.game.App;
import team5.game.DatabaseHandler;
import team5.game.model.GameState;

import java.io.IOException;

import javafx.event.ActionEvent;

public class StartController {

   @FXML
   void exitGame(ActionEvent event) {

   }

   @FXML
   void loadGame(ActionEvent event) throws IOException {
      GameState.loadGame();

      App.setRoot("DungeonScene");
   }

   @FXML
   void switchToHeroCreation(ActionEvent event) throws IOException {
      App.setRoot("NameSelection");
   }

}
