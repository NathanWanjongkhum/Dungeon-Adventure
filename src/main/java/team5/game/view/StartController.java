package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import team5.game.App;

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

   private static boolean myCheats;


   @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
      BackgroundImage back = App.getBackgroundImage("TitleScreen");
      myScene.setBackground(new Background(back));
      myCheats = false;
    }
   @FXML
   void exitGame(ActionEvent event) {
      Stage stage = (Stage) myScene.getScene().getWindow();
      stage.close();
   }

   @FXML
   void loadGame(ActionEvent event) {

   }

   @FXML
   void switchToHeroCreation(ActionEvent event) throws IOException {
      App.setRoot("NameSelection");
   }
   @FXML
    void enableCheats(MouseEvent event) {
      if (!myCheats) {
         myCheats = true;
      }
    }
    protected static boolean isCheats() {
      return myCheats;
    }

}
