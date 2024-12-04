package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import team5.game.App;

public class SettingsController {
    @FXML
    private VBox myScene;
    
    @FXML
    void getHelp(ActionEvent event) throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    void quit(ActionEvent event) {
        Stage stage = (Stage) myScene.getScene().getWindow();
        stage.close();
    }

    @FXML
    void resumeGame(ActionEvent event) {
        Stage stage = (Stage) myScene.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveGame(ActionEvent event) {

    }

}
