package team5.game.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import team5.game.model.GameState;
import team5.game.view.App;
/**
 * The GUI Controller for SettingsController
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class SettingsController {
    
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
    @FXML
    void heroSelect(final ActionEvent theEvent) throws IOException {
        App.setRoot("HeroSelection");
        close();
    }
    /**Closes the popup */
    private void close() {
        App.closePopUp();
    }

}
