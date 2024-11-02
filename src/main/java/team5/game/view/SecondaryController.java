package team5.game.view;

import java.io.IOException;
import javafx.fxml.FXML;
import team5.game.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}