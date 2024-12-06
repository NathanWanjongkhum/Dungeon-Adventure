package team5.game.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import team5.game.App;

public class HelpController {

    @FXML
    private VBox myMenu;
    @FXML
    private Label myGoblinDesciption;

    @FXML
    private Label myOgreDescription;

    @FXML
    private Label mySkeletonDesciption;

    @FXML
    void exit(ActionEvent event) {
        App.closePopUp();
    }

}
