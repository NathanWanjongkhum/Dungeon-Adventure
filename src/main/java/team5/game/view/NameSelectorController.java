package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import team5.game.App;
import team5.game.model.GameState;

public class NameSelectorController implements Initializable{
    @FXML
    private BorderPane myBack;

    @FXML
    private TextField myName;

    @FXML
    private Label myNameDisplay;

    @FXML
    private Button myNext;

    private String myConfirmedName;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        myBack.setStyle(("-fx-background-color: black"));
    }
    @FXML
    void confirm(ActionEvent event) {
        myConfirmedName = myName.getText();
        if (myConfirmedName != "") {
            GameState.getInstance().setName(myConfirmedName);
            myNameDisplay.setText("Welcome " + myConfirmedName);
            myNameDisplay.setVisible(true);
            myNext.setDisable(false);
        }
    }

    @FXML
    void back() throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    void next() throws IOException {
        App.setRoot("HeroSelection");
    }
}