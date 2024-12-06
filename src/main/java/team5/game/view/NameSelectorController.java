package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import team5.game.App;
import team5.game.model.GameState;
/**
 * The GUI Controller for NameSelector
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class NameSelectorController implements Initializable{
    @FXML
    /** The backmost containter */
    private BorderPane myBack;

    @FXML
    /** The text field to input name */
    private TextField myName;

    @FXML
    /** Displays the name inputed to text field */
    private Label myNameDisplay;

    @FXML
    /** The button to go next */
    private Button myNext;
    /** The string of the name inputted */
    private String myConfirmedName;

    @Override
    public void initialize(final URL theURL, final ResourceBundle theResource) {
        myBack.setStyle(("-fx-background-color: black"));
    }
    @FXML
    /**
     * The confirm button action
     * 
     * @param theEvent the button press
     */
    private void confirm(final ActionEvent theEvent) {
        myConfirmedName = myName.getText();
        //Only goes next if name is inputted
        if (!myConfirmedName.equals("")) {
            GameState.getInstance().setName(myConfirmedName);
            myNameDisplay.setText("Welcome " + myConfirmedName);
            myNameDisplay.setVisible(true);
            myNext.setDisable(false);
        }
    }

    @FXML
    /**
     * The back button.
     * 
     * @throws IOException when fxml file is not found.
     */
    private void back() throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    /**
     * The next button action
     * 
     * @throws IOException when the fxml file is not found
     */
    private void next() throws IOException {
        App.setRoot("HeroSelection");
    }
}