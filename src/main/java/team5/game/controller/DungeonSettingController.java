package team5.game.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import team5.game.model.Difficulty;
import team5.game.model.Dungeon;
import team5.game.model.GameState;
import team5.game.view.App;

/**
 * a
 * The controller for the dungeon settings screen.
 */
public class DungeonSettingController {
    /** The choices of difficulty options */
    final static ObservableList<Difficulty> myDifficultyList = FXCollections.observableArrayList(Difficulty.values());

    /** The root pane */
    @FXML
    private BorderPane myBack;
    /** A text field for the width of the dungeon */
    @FXML
    private ChoiceBox<String> mySize;
    /** The difficulty choice box */
    @FXML
    private ChoiceBox<Difficulty> myDifficulty;

    private final String[] mySizes = { "SMALL", "MEDIUM", "LARGE" };

    /** Initialize the controller */
    @FXML
    private void initialize() {
        myBack.setStyle(("-fx-background-color: black"));
        mySize.getItems().addAll(mySizes);
        mySize.setValue("SMALL");
        myDifficulty.setItems(myDifficultyList);
        myDifficulty.setValue(Difficulty.MEDIUM);
    }

    /**
     * Pass the parameters to the next screen to start the dungeon.
     * 
     * @throws IOException if the screen can't be loaded
     */
    @FXML
    private void start() throws IOException {
        final Difficulty difficulty = myDifficulty.getValue();

        // Sets the dungeon
        final int size = getDungeonSize(mySize.getValue());
        final Dungeon myDungeon = new Dungeon(size, size, difficulty);
        GameState.getInstance().setDungeon(myDungeon);

        App.setRoot("DungeonScene");
    }

    /**
     * Go back to the previous screen hero selection.
     * 
     * @throws IOException if the screen can't be loaded
     */
    @FXML
    private void back() throws IOException {
        App.setRoot("HeroSelection");
    }

    /**
     * Sets the dungeon size
     * 
     * @param event the action event
     * @throws IOException
     */
    @FXML
    private void setCustomDungeonSettings(final ActionEvent thEvent) throws IOException {
        App.setRoot("CustomDungeonSetting");
    }

    /**
     * Gets the dungeon size
     *
     * @param theSize the size
     * @return the size
     */
    private int getDungeonSize(final String theSize) {
        int size = 0;

        switch (theSize) {
            case "SMALL":
                size = 10;
                break;
            case "MEDIUM":
                size = 15;
                break;
            case "LARGE":
                size = 20;
                break;
            default:
                break;
        }

        return size;
    }
}
