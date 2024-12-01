package team5.game.view;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import team5.game.App;
import team5.game.model.Difficulty;
import team5.game.model.Dungeon;
import team5.game.model.GameState;

/**a
 * The controller for the dungeon settings screen.
 */
public class DungeonSettingController {
    /** The choices of difficulty options */
    final static ObservableList<Difficulty> myDifficultyList = FXCollections.observableArrayList(Difficulty.values());

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

        // Sets the dungeon
        final Dungeon myDungeon = new Dungeon(width, height, difficulty);
        GameState.getInstance().setDungeon(myDungeon);

        // Loads the next screen
        final FXMLLoader loader = new FXMLLoader(App.class.getResource("/team5/game/dungeon2.fxml"));

        // Get the current stage from any node in the current scene
        final Stage currentStage = (Stage) myWidth.getScene().getWindow();

        // Set the stage to the new scene
        final Scene newScene = new Scene(loader.load());

        currentStage.setScene(newScene);
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
}
