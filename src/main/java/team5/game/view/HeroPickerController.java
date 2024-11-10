package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import team5.game.App;

import javafx.event.ActionEvent;

public class HeroPickerController implements Initializable {
    
    @FXML
    private Label myName;

    @FXML
    private ChoiceBox<String> myChoice;

    @FXML
    private ImageView myImage;

    @FXML
    private Button myNext;

    private String[] myCharacters = {"Archer", "Mage", "Priestess", "Warrior"};

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        myChoice.getItems().addAll(myCharacters);
        myChoice.setOnAction(this::displayImage);
    }
    public void displayImage(ActionEvent event) {
        String choice = myChoice.getValue(); //Gets the hero selection
        myNext.setDisable(false);
        //Need to add a "custom" image for each class in view
    }
    
    @FXML
    void back() throws IOException{
        App.setRoot("StartScreen");
    }

    @FXML
    void next() throws IOException {
        App.setRoot("NameSelection");
    }

}

