package team5.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import team5.game.controller.Choices;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    /** The primary scene */
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("StartScreen"), 640, 480);
        stage.setScene(scene);
        //Makes it so that resizing the scene won't break visuals
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Dungeon Adventures");
        //Add a icon to the scene
        //stage.getIcons().add();
        
        // stage.setMinHeight(480);
        // stage.setMinWidth(640);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}