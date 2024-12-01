package team5.game;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    /** The primary scene */
    private static Scene myScene;

    @Override
    public void start(Stage stage) throws IOException {
        myScene = new Scene(loadFXML("StartScreen"), 640, 480);
        stage.setScene(myScene);
        // Makes it so that resizing the scene won't break visuals
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Dungeon Adventures");
        // Add a icon to the scene
        // stage.getIcons().add();

        // stage.setMinHeight(480);
        // stage.setMinWidth(640);
    }

    public static void setRoot(String theFXML) throws IOException {
        myScene.setRoot(loadFXML(theFXML));
    }

    private static Parent loadFXML(String theFXML) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + theFXML + ".fxml"));
        return fxmlLoader.load();
    }
    public static BackgroundImage getBackgroundImage(final String theImageName) {
        BackgroundSize size = new BackgroundSize(640, 480, true, true, true, true);
        final StringBuilder builder = new StringBuilder();
        builder.append("/team5/game/");
        builder.append(theImageName);
        builder.append(".png");
        Image temp = new Image(App.class.getResource(builder.toString()).toString());
        BackgroundImage back = new BackgroundImage(temp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        return back;
    }

    public static void main(String[] theArgs) {
        launch();
    }
}