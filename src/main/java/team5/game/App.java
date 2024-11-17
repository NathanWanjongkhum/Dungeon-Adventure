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
    private static Scene myScene;

    @Override
    public void start(Stage myStage) throws IOException {
        myScene = new Scene(loadFXML("DungeonSetting"), 640, 480);
        myStage.setScene(myScene);
        myStage.show();
    }

    public static void setRoot(String theFXML) throws IOException {
        myScene.setRoot(loadFXML(theFXML));
    }

    private static Parent loadFXML(String theFXML) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + theFXML + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] theArgs) {
        launch();
    }
}