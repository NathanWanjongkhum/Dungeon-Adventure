package team5.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import team5.game.model.Dungeon;
import team5.game.model.Hero;
import team5.game.model.Mage;
import team5.game.model.Monster;
import team5.game.model.Room;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    /** The primary scene */
    private static Scene scene;
    /** The dungeon */
    private static Dungeon dungeon;
    /** The hero */
    private static Hero hero;

    /**
     * Start the view application
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set the root of the scene
     * 
     * @param fxml the fxml file
     * @throws IOException if the fxml file cannot be found
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load the fxml file
     * 
     * @param fxml the fxml file
     * @return the root node
     * @throws IOException if the fxml file cannot be found
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Get the dungeon
     * 
     * @return the dungeon
     */
    public static void main(String[] args) {
        final Monster[] monsters = DatabaseHandler.deserialize();

        dungeon = new Dungeon(10, 10, Dungeon.Difficulty.EASY);
        dungeon.init();

        Mage hero = new Mage("Merlin");

        // TODO: Place the hero in the dungeon
        // TODO: Place the items in the dungeon
        // TODO: Place the monsters in the dungeon

        launch();
    }
}