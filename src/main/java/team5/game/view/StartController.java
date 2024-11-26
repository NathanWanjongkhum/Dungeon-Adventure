package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import team5.game.App;

public class StartController implements Initializable{

   @FXML
   private VBox myScene;

   @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
      // BackgroundSize size = new BackgroundSize(640, 480, true, true, true, false);
      // final StringBuilder builder = new StringBuilder();
      //   builder.append("/team5/game/");
      //   builder.append("dungeonBackground");
      //   builder.append(".png");
      //   Image temp = new Image(getClass().getResource(builder.toString()).toString());
      // BackgroundImage back = new BackgroundImage(temp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
      // myScene.setBackground(new Background(back));
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

}
