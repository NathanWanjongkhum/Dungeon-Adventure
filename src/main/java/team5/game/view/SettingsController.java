package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import team5.game.App;
import team5.game.model.GameState;

public class SettingsController {
    @FXML
    /** The menu container */
    private VBox myScene;
    
    @FXML
    /**
     * The help action button
     * 
     * @param theEvent the button press
     * @throws IOException when the fxml file is not found
     */
    private void getHelp(final ActionEvent theEvent) throws IOException {
           App.setPopUpRoot("Help");
    }
    @FXML
    /**
     * The home button action
     * 
     * @param theEvent the button press
     * @throws IOException when the fxml file is not found
     */
    private void home(final ActionEvent theEvent) throws IOException {
        App.setRoot("StartScreen");
        close();
    }

    @FXML
    /**
     * Closes the popup and parent scene
     * 
     * @param theEvent the button press
     */
    private void quit(final ActionEvent theEvent) {
        close();
        App.close();
    }

    @FXML
    /**
     * Closes the popup action
     * 
     * @param theEvent the button press
     */
    private void resumeGame(final ActionEvent theEvent) {
        close();
    }

    @FXML
    /**
     * Saves the game then closes the popup
     * 
     * @param theEvent the button press
     */
    private void saveGame(final ActionEvent theEvent) {
        GameState.saveGame();
        //Indivator of game saved
        close();
    }
    /**Closes the popup */
    private void close() {
        App.closePopUp();
    }

}
