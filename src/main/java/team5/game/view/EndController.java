package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import team5.game.App;
import team5.game.model.GameState;
/**
 * The GUI Controller for EndScene
 * 
 * @author Holden Tsang
 * @version December 10 2024
 */
public class EndController {
    @FXML
    private BorderPane myBack;

    @FXML
    /** Label to indicate win or lose */
    private Label myMessage;

    @FXML
    private void initialize() {
        BackgroundImage back = App.getBackgroundImage("maze background");
        myBack.setBackground(new Background(back));
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
