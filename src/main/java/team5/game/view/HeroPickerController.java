package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import team5.game.App;
import team5.game.controller.HeroFactory;
import team5.game.model.GameState;
import team5.game.model.Hero;

public class HeroPickerController implements Initializable {

    @FXML
    private Label myName;

    @FXML
    private ChoiceBox<String> myChoice;

    @FXML
    private ImageView myImage;

    @FXML
    private Button myNext;

    @FXML
    private Label myStats;

    @FXML
    private VBox myBack;

    private final String[] myCharacters = { "Archer", "Mage", "Priestess", "Warrior" };
    private final String[] myCheatCharacters = {"Slime", "Noob"};

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        BackgroundImage back = App.getBackgroundImage("selection background");
        myBack.setBackground(new Background(back));
        myChoice.getItems().addAll(myCharacters);
        if (StartController.isCheats()) {
            myChoice.getItems().addAll(myCheatCharacters);
        }
        myChoice.setOnAction(this::displayImage); 
    }

    private void displayImage(ActionEvent event) {
        String choice = myChoice.getValue();
        Hero hero = HeroFactory.createHero(choice);
        myImage.setImage(hero.getImage());
        GameState.getInstance().setHero(hero);
        myStats.setText(hero.getStats());
        myStats.setVisible(true);
        myNext.setDisable(false);
    }

    @FXML
    void back() throws IOException {
        App.setRoot("NameSelection");
    }

    @FXML
    void next() throws IOException {
        App.setRoot("DungeonSetting"); 
    }

}
