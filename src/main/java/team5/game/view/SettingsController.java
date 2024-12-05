package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import team5.game.App;
import team5.game.model.GameState;

public class SettingsController {
    @FXML
    private VBox myScene;
    
    @FXML
    void getHelp(ActionEvent event) throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    void quit(ActionEvent event) {
        close();
        App.close();
    }

    @FXML
    void resumeGame(ActionEvent event) {
        close();
    }

    @FXML
    void saveGame(ActionEvent event) {
        GameState.saveGame();
        //Indivator of game saved
    }
    private void close() {
        App.closePopUp();
    }

}
