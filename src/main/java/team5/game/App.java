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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final int POPUP_WIDTH = 960;
    private static final int POPUP_HEIGHT = 720;
    /** The primary scene */
    private static Scene myScene;
    private static Stage myStage;
    private static Stage myPopUp;
    private static Scene myPopUpScene;

    @Override
    public void start(Stage stage) throws IOException {
        myScene = new Scene(loadFXML("StartScreen"), 960, 720);
        stage.setScene(myScene);
        stage.centerOnScreen();
        // Makes it so that resizing the scene won't break visuals
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Dungeon Adventures");
        myStage = stage;
        // Add a icon to the scene
        // stage.getIcons().add();
    }

    public static void setRoot(String theFXML) throws IOException {
        myScene.setRoot(loadFXML(theFXML));
    }
    public static void setPopUpRoot(String theFXML) throws IOException {
        myPopUpScene.setRoot(loadFXML(theFXML));
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
        //A bit scuff to line up with battle scene
        myPopUp.setY(myStage.getY() + myStage.getHeight() / 2 + 13 - POPUP_HEIGHT / 2);
        myPopUp.initOwner(myStage);
        myPopUp.showAndWait();
    }
    public static void close() {
        myStage.close();
    }
    public static void closePopUp() {
        myPopUp.close();
    }
    public static void main(String[] theArgs) {
        launch();
    }
}