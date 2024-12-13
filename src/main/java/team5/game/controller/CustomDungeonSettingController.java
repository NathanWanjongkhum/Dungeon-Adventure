package team5.game.controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import team5.game.model.Difficulty;
import team5.game.model.Dungeon;
import team5.game.model.GameState;
import team5.game.view.App;

/**
 * a
 * The controller for the dungeon settings screen.
 */
public class CustomDungeonSettingController {
    /** The choices of difficulty options */
    final static ObservableList<Difficulty> myDifficultyList = FXCollections.observableArrayList(Difficulty.values());

    /** The root pane */
    @FXML
    private BorderPane myBack;
    /** A text field for the width of the dungeon */
    @FXML
    private TextField myWidth;
    /** A text field for the height of the dungeon */
    @FXML
    private TextField myHeight;
    /** The difficulty choice box */
    @FXML
    private ChoiceBox<Difficulty> myDifficulty;

    /** Initialize the controller */
    @FXML
    private void initialize() {
        myBack.setStyle(("-fx-background-color: black"));
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
        final int width = Integer.parseInt(myWidth.getText());
        final int height = Integer.parseInt(myHeight.getText());
        final Difficulty difficulty = myDifficulty.getValue();

        if (width <= 0 || height <= 0 || difficulty == null) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        if (width < 10 || height < 10) {
            // Needs enough space for the pillars to be safley placed
            throw new IllegalArgumentException("Dungeon size too small");
        }

        // Sets the dungeon
        final Dungeon myDungeon = new Dungeon(width, height, difficulty);
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
        App.setRoot("DungeonSetting");
    }
}
