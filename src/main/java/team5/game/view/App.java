package team5.game.view;

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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    /** The width of the popup */
    private static final int POPUP_WIDTH = 960;
    /** The height of the popup */
    private static final int POPUP_HEIGHT = 720;
    /** The width of the window */
    private static final int WIDTH = 960;
    /** The height of the window */
    private static final int HEIGHT = 720;
    /** The primary scene */
    private static Scene myScene;
    /** The primary stage */
    private static Stage myStage;
    /** The pop up scene */
    private static Stage myPopUp;
    /** The pop up scene */
    private static Scene myPopUpScene;

    /**
     * Starts the application and sets the root of the scene to the start screen.
     * Sets stage parameters.
     */
    @Override
    public void start(Stage stage) throws IOException {
        myScene = new Scene(loadFXML("StartScreen"), WIDTH, HEIGHT);
        stage.setScene(myScene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Dungeon Adventures");
        myStage = stage;
    }

    /**
     * Sets the root of the scene to the specified FXML file.
     * 
     * @param theFXML the FXML file name
     * @throws IOException
     */
    public static void setRoot(String theFXML) throws IOException {
        myScene.setRoot(loadFXML(theFXML));
    }

    /**
     * Sets the root of the pop up scene to the specified FXML file.
     * 
     * @param theFXML the FXML file name
     * @throws IOException
     */
    public static void setPopUpRoot(String theFXML) throws IOException {
        myPopUpScene.setRoot(loadFXML(theFXML));
    }

    /**
     * Loads the FXML file
     * 
     * @param theFXML the FXML file name
     * @return the root node of the FXML file
     * @throws IOException
     */
    private static Parent loadFXML(String theFXML) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + theFXML + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Gets the background image
     * 
     * @param theImageName the image name
     * @return the background image
     */
    public static BackgroundImage getBackgroundImage(final String theImageName) {
        BackgroundSize size = new BackgroundSize(640, 480, true, true, true, true);

        final StringBuilder builder = new StringBuilder();
        builder.append("/team5/game/");
        builder.append(theImageName);
        builder.append(".png");

        Image temp = new Image(App.class.getResource(builder.toString()).toString());
        BackgroundImage back = new BackgroundImage(temp, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, size);

        return back;
    }

    /**
     * Creates the pop up scene
     * 
     * @param theFXML the FXML file name
     * @throws IOException
     */
    public static void createPopUpScene(final String theFXML) throws IOException {
        myPopUp = new Stage();
        myPopUp.setWidth(POPUP_WIDTH);
        myPopUp.setHeight(POPUP_HEIGHT);

        myPopUpScene = new Scene(loadFXML(theFXML));
        myPopUp.setScene(myPopUpScene);

        myPopUp.initModality(Modality.APPLICATION_MODAL);
        myPopUp.initStyle(StageStyle.UNDECORATED);
        myPopUp.initStyle(StageStyle.TRANSPARENT);
        myPopUpScene.setFill(Color.TRANSPARENT);

        myPopUp.setX(myStage.getX() + myStage.getWidth() / 2 - POPUP_WIDTH / 2);
        myPopUp.setY(myStage.getY() + myStage.getHeight() / 2 + 12 - POPUP_HEIGHT / 2);

        myPopUp.initOwner(myStage);

        myPopUp.showAndWait();
    }

    /**
     * Closes the application
     */
    public static void close() {
        myStage.close();
    }

    /**
     * Closes the pop up
     */
    public static void closePopUp() {
        myPopUp.close();
    }

    /**
     * Program entry point for the application that launches the application
     * 
     * @param theArgs Expects no arguments
     */
    public static void main(String[] theArgs) {
        launch();
    }
}