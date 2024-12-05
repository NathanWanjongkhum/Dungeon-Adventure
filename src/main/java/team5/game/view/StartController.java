package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import team5.game.App;
import team5.game.model.GameState;

public class StartController implements Initializable{
   @FXML
   private Button myLoad;

   @FXML
   private Button myNew;

   @FXML
   private Button myQuit;

    @FXML
    private Text myTitle;

   @FXML
   private VBox myScene;
   
   @FXML
   private Label myCheatIndicator;

   private boolean myCheats = false;
   private int myCount;


   @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
      BackgroundImage back = App.getBackgroundImage("TitleScreen");
      myScene.setBackground(new Background(back));
      GameState.getInstance().setCheats(false);
    }
   @FXML
   private void exitGame(ActionEvent event) {
      App.close();
   }

   @FXML
   private void loadGame(ActionEvent event) throws IOException {
      GameState.loadGame();

      App.setRoot("DungeonScene");
   }

   @FXML
   private void switchToHeroCreation(ActionEvent event) throws IOException {
      App.setRoot("NameSelection");
   }
   @FXML
    void enableCheats(MouseEvent event) {
      if (!myCheats && myCount > 4) {
         myCheatIndicator.setText("Cheat Characters Added");
         myCheatIndicator.setVisible(true);
         myCheats = true;
         GameState.getInstance().setCheats(myCheats);
      }
      myCount++;
    }
}
