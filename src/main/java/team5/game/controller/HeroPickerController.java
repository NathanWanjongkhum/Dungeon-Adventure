package team5.game.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.model.HeroFactory;
import team5.game.view.App;
/**
 * The GUI Controller for HeroPickerController
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class HeroPickerController {

    @FXML
    /** ChoiceBox that contains the hero choices */
    private ChoiceBox<String> myChoice;

    @FXML
    /** The ImageView for Hero */
    private ImageView myImage;

    @FXML
    /** Button to go next scene*/
    private Button myNext;

    @FXML
    /** The stats of the hero */
    private Label myStats;

    @FXML
    /** The back container of the scene */
    private VBox myBack;
    
    /** The Characters for normal gameplay */
    private final String[] myCharacters = { "Archer", "Mage", "Priestess", "Warrior" };
    /** The Character for cheat gameplay */
    private final String[] myCheatCharacters = {"Slime", "Noob"};

    @FXML
    private void initialize() {
        BackgroundImage back = App.getBackgroundImage("selection background");
        myBack.setBackground(new Background(back));
        myChoice.getItems().addAll(myCharacters);
        if (GameState.getInstance().isCheats()) {
            myChoice.getItems().addAll(myCheatCharacters);
        }
        myChoice.setOnAction(this::displayImage); 
    }
    /**
     * Displays the Image of the hero and stats and 
     * sets the hero to gamestate singleton.
     * 
     * @param theEvent the choice selection.
     */
    private void displayImage(final ActionEvent theEvent) {
        String choice = myChoice.getValue();
        Hero hero = HeroFactory.createHero(choice);
        myImage.setImage(hero.getImage());
        GameState.getInstance().setHero(hero);
        myStats.setText(hero.getStats());
        myStats.setVisible(true);
        myNext.setDisable(false);
    }

    @FXML
    /**
     * The back button action.
     * 
     * @throws IOException when fxml is not found.
     */
    private void back() throws IOException {
        App.setRoot("NameSelection");
    }

    @FXML
    /**
     * The next button action
     * 
     * @throws IOException when fxml is not found.
     */
    private void next() throws IOException {
        App.setRoot("DungeonSetting"); 
    }

}
