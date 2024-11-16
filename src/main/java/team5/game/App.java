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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("StartScreen"), 640, 480);
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
        launch();
    }
}