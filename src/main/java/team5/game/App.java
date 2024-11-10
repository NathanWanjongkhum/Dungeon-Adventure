package team5.game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import team5.game.model.Dungeon;
import team5.game.model.Hero;
import team5.game.model.Mage;
import team5.game.view.Input;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.security.Key;

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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("BattleScene"), 640, 480);
        //Parent root = FXMLLoader.load(getClass().getResource("testscene.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("testscene.fxml"));
		// Parent root = loader.load();	
		// Input controller = loader.getController();
        // scene = new Scene(root);
        // scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        //     @Override
        //     public void handle(KeyEvent event) {
        //         switch(event.getCode()) {
        //         case W:
        //             controller.up();
        //             break;
        //         case S:
        //             controller.down();
        //             break;
        //         case A:
        //             controller.left();
        //             break;
        //         case D:
        //             controller.right();
        //             break;
        //         default:
        //             break;
        //             }
        //     }
        // });
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // dungeon = new Dungeon(10, 10, Dungeon.Difficulty.EASY);
        // dungeon.init();

           // hero = new Mage("Merlin");

        launch();
    }
}