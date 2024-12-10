package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import team5.game.App;
import team5.game.model.GameState;
/**
 * The GUI Controller for StartScreenController
 * 
 * @author 
 * @version December 5 2024
 */
public class StartController {
   @FXML
   /** The title text of the game */
   private Text myTitle;

   @FXML
   /** The backmost container of the scene */
   private VBox myScene;
   
   @FXML
   /** Label to indicate cheats enabled */
   private Label myCheatIndicator;
   /** The boolean to determine cheats */
   private boolean myCheats;
   /** The amount of clicks that occured */
   private int myCount;


   @FXML
    private void initialize() {
      myCheats = false;
      BackgroundImage back = App.getBackgroundImage("TitleScreen");
      myScene.setBackground(new Background(back));
      GameState.getInstance().setCheats(false);
      myCount = 0;
    }
   @FXML
   /**
    * Exits the game button
    *
    * @param theEvent the button press
    */
   private void exitGame(final ActionEvent theEvent) {
      App.close();
   }

   @FXML
   /**
    * Loads the lastest save in the database
    *
    * @param theEvent the button press
    * @throws IOException when fxml is not found
    */
   private void loadGame(final ActionEvent theEvent) throws IOException {
      GameState.loadGame();

      App.setRoot("DungeonScene");
   }

   @FXML
   /**
    * Changes the scene to name selection 
    *
    * @param theEvent the button press
    * @throws IOException when the fxml is not found
    */
   private void switchToHeroCreation(final ActionEvent theEvent) throws IOException {
      App.setRoot("NameSelection");
   }
   @FXML
   /**
    * The cheats enabler 
    *
    * @param theEvent the mouse clicks
    */
   private void enableCheats(final MouseEvent theEvent) {
      myCount++;
      if (!myCheats && myCount > 4) {
         myCheatIndicator.setText("Cheat Characters Added");
         myCheatIndicator.setVisible(true);
         myCheats = true;
         GameState.getInstance().setCheats(myCheats);
      }
   }
}
