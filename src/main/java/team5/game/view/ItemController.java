package team5.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import team5.game.App;
import team5.game.model.Consumable;
import team5.game.model.GameState;
import team5.game.model.Inventory;
import team5.game.model.Item;

public class ItemController implements Initializable{

    @FXML
    private BorderPane myScene;

    @FXML
    private Button myAttackButton;

    @FXML
    private Label myAttackCount;

    @FXML
    private Label myAttackDescript;

    @FXML
    private Button myBombButton;

    @FXML
    private Label myBombCount;

    @FXML
    private Label myBombDescript;

    @FXML
    private Button myHealingButton;

    @FXML
    private Label myHealingCount;

    @FXML
    private Label myHealingDecript;

    private Inventory myInventory;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        // myScene.setStyle(("-fx-background-color: #66524d; -fx-effect: innershadow(gaussian, black, 7, 1, 0, 0)"));
        // myScene.setBorder(new Border());
        myInventory = GameState.getInstance().getHero().getInventory();
        disable();
        int index = 0;
        if (!myInventory.isEmpty()) {
            for (Item c: myInventory.getItems()) {
                if (c != null) {
                    if (c.isConsumable()) {
                        setText(c, index);
                    }
                }
                index++;
            }
        }
        if (GameState.getInstance().getHero().getStatusEffects().isDamageIncrease()) {
            myAttackButton.setDisable(true);
        }        
    }
    private void disable() {
        myAttackButton.setDisable(true);
        myHealingButton.setDisable(true);
        myBombButton.setDisable(true);
        myAttackCount.setText("x0");
        myHealingCount.setText("x0");
        myBombCount.setText("x0");
    }
    private void setText(Item theItem, final int theIndex) {
        Consumable item = ((Consumable)myInventory.getItem(theIndex));
    switch (theItem.getName()) {
        case "AttackPotion":
            myAttackCount.setText("x" + item.getCount());
            setButtons(item, myAttackButton);
            myAttackDescript.setText(item.getDescription());
            break;
        case "HealingPotion":
            myHealingCount.setText("x" + item.getCount());
            setButtons(item, myHealingButton);
            myHealingDecript.setText(item.getDescription());
            break;
        case "Bomb":
            myBombCount.setText("x" + item.getCount());
            setButtons(item, myBombButton);
            myBombDescript.setText(item.getDescription());
            break;
        default:
            break;
    }
    }
    private void setButtons(Consumable theConsumable, Button theButton) {
    if (theConsumable.getCount() == 0) {
        theButton.setDisable(true);
    } else {
        theButton.setDisable(false);
    }

    }
    @FXML
    void useAttackPotion(ActionEvent event) {
        Consumable con = getConsumable("AttackPotion");
        GameState.getInstance().getHero().setConsumable(con);
        close();
    }

    @FXML
    void useBomb(ActionEvent event) {
        Consumable con = getConsumable("Bomb");
        GameState.getInstance().getHero().setConsumable(con);
        close();
    }

    @FXML
    void useHealingPotion(ActionEvent event) {
        Consumable con = getConsumable("HealingPotion");
        GameState.getInstance().getHero().setConsumable(con);
        close();
    }
    @FXML
    void cancel(ActionEvent event) {
        close();
    }
    private void close() {
        App.closePopUp();
    }
    private Consumable getConsumable(String theItem) {
        Consumable consumable = null;
        if (myInventory != null) {
            for (Item c: myInventory.getItems()) {
                if (c != null && c.getName().equals(theItem)) {
                    consumable = (Consumable) c;
                }
            }
        }
        return consumable;
    }
    

}

