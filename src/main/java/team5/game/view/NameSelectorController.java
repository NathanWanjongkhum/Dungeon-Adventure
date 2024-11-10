package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import team5.game.App;

public class NameSelectorController {

    @FXML
    private TextField myName;

    @FXML
    private Button myNext;
    
    private String myConfirmedName;

    @FXML
    void confirm(ActionEvent event) {
        myConfirmedName = myName.getText();
        if (myConfirmedName != "") {
            myNext.setDisable(false);
        }
        System.out.println(myConfirmedName);
    }

    @FXML
    void back() throws IOException{
        App.setRoot("HeroSelection");
    }

    @FXML
    void next() throws IOException {
        App.setRoot("primary");
    }

}