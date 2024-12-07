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
/**
 * The GUI Controller for EndScene
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class EndController implements  Initializable{
    @FXML
    /** Label to indicate win or lose */
    private Label myMessage;

    @Override
    public void initialize(final URL theURL, final ResourceBundle theResource) {
        if (GameState.getInstance().getHero().getHealth() == 0) {
            myMessage.setText("Defeat");
        } else {
            myMessage.setText("Victory");
        }
    }
    @FXML
    /**
     * Returns to the home scene.
     * 
     * @param theEvent the button press.
     * @throws IOException when fxml file is not found.
     */
    private void setHome(final ActionEvent theEvent) throws IOException {
        App.setRoot("StartScreen");
    }
}
