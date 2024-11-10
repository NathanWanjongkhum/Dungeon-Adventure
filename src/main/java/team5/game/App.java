package team5.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    /** The primary scene */
    private static Scene scene;

    /**
     * Start the view application
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dungeon2"));
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
        launch();
    }
}