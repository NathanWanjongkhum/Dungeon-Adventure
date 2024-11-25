package team5.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import team5.game.model.Consumable;
import team5.game.model.Inventory;
import team5.game.model.Item;

public class ItemController implements Initializable{

    @FXML
    private VBox myScene;

    @FXML
    private Button myAttackButton;

    @FXML
    private Label myAttackCount;

    @FXML
    private Button myBombButton;

    @FXML
    private Label myBombCount;

    @FXML
    private Button myHealingButton;

    @FXML
    private Label myHealingCount;

    private Inventory myInventory;
    private BattleController myController;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleScene.fxml"));
        myController = loader.getController();
    }
       private void setButtons(Button theButton, boolean theBoolean) {
        theButton.setDisable(theBoolean);
    }
    @FXML
    void useAttackPotion(ActionEvent event) {
        Consumable con = getConsumable("AttackPotion");
        myController.setConsumable(con);
        close();
    }

    @FXML
    void useBomb(ActionEvent event) {

    }

    @FXML
    void useHealingPotion(ActionEvent event) {

    }
    private void close() {
        Stage stage = (Stage) myScene.getScene().getWindow();
        stage.close();
    }
    private Consumable getConsumable(String theItem) {
        Consumable consumable = null;
        for (Item c: myInventory.getItems()) {
            if (c.getName().equals(theItem)) {
                consumable = (Consumable) c;
            }
        }
        return consumable;
    }
    

}

