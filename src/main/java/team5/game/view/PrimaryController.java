package team5.game.view;

import java.io.IOException;
import javafx.fxml.FXML;
import team5.game.App;

public class PrimaryController {

    @FXML
    private void switchToDungeon() throws IOException {
        App.setRoot("dungeon2");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
