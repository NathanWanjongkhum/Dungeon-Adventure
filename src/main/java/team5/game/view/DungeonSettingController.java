package team5.game.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import team5.game.App;
import team5.game.model.Difficulty;
import team5.game.model.Dungeon;
import team5.game.model.GameState;

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

    private final String[] mySizes = {"SMALL", "MEDIUM", "LARGE"};

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
        // final int width = Integer.parseInt(myWidth.getText());
        // final int height = Integer.parseInt(myHeight.getText());
        final Difficulty difficulty = myDifficulty.getValue();

        // if (width <= 0 || height <= 0 || difficulty == null) {
        //     throw new IllegalArgumentException("Invalid parameters");
        // }

        // if (width < 10 || height < 10) {
        //     // Needs enough space for the pillars to be safley placed
        //     throw new IllegalArgumentException("Dungeon size too small");
        // }

        // Sets the dungeon
        final int size = getDungeonSize(mySize.getValue());
        final Dungeon myDungeon = new Dungeon(size, size, difficulty);
        GameState.getInstance().setDungeon(myDungeon);

        App.setRoot("DungeonScene3");
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
    private int getDungeonSize(final String theSize) {
        int size = 0;
        switch(theSize) {
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
