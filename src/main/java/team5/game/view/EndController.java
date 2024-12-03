package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import team5.game.App;
import team5.game.model.GameState;

public class EndController implements  Initializable{
    @FXML
    private Label myMessage;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        if (GameState.getInstance().getHero().getHealth() == 0) {
            myMessage.setText("Defeat");
        } else {
            myMessage.setText("Victory");
        }
    }
    @FXML
    void setHome(ActionEvent event) throws IOException {
        App.setRoot("StartScreen");
    }
}
