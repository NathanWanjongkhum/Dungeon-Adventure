package team5.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import team5.game.App;
import team5.game.model.Consumable;
import team5.game.model.GameState;
import team5.game.model.Inventory;
import team5.game.model.Item;
/**
 * The GUI Controller for HeroPickerController
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class ItemController implements Initializable{

    @FXML
    private BorderPane myScene;

    @FXML
    /** The attack potion use button */
    private Button myAttackButton;

    @FXML
    /** The attack potion count */
    private Label myAttackCount;

    @FXML
    /** The attack potion description*/
    private Label myAttackDescript;

    @FXML
    /** The bomb use button */
    private Button myBombButton;

    @FXML
    /** The bomb item count */
    private Label myBombCount;

    @FXML
    /** The bomb description */
    private Label myBombDescript;

    @FXML
    /** The healing potion use button */
    private Button myHealingButton;

    @FXML
    /** The healing potion count */
    private Label myHealingCount;

    @FXML
    /** The healing potion description */
    private Label myHealingDescript;
    /** The inventory of items */
    private Inventory myInventory;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
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
    /** Disables all buttons */
    private void disable() {
        myAttackButton.setDisable(true);
        myHealingButton.setDisable(true);
        myBombButton.setDisable(true);
        myAttackCount.setText("x0");
        myHealingCount.setText("x0");
        myBombCount.setText("x0");
    }
    /** Sets the text of items at current inventory index */
    private void setText(Item theItem, final int theIndex) {
        //In loop in initialize check if consumable
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
                myHealingDescript.setText(item.getDescription());
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
    /** Enables the button if there is a more than 0 consumable */
    private void setButtons(Consumable theConsumable, Button theButton) {
    if (theConsumable.getCount() < 0) {
        theButton.setDisable(true);
    } else {
        theButton.setDisable(false);
    }

    }
    @FXML
    /**
     * Attack potion use action
     * 
     * @param theEvent the button press
     */
    private void useAttackPotion(final ActionEvent theEvent) {
        Consumable con = getConsumable("AttackPotion");
        GameState.getInstance().getHero().setConsumable(con);
        close();
    }

    @FXML
    /**
     * The bomb use action
     * 
     * @param theEvent the button press
     */
    void useBomb(final ActionEvent theEvent) {
        Consumable con = getConsumable("Bomb");
        GameState.getInstance().getHero().setConsumable(con);
        close();
    }

    @FXML
    /**
     * The healing potion use action
     * 
     * @param theEvent the button press
     */
    void useHealingPotion(final ActionEvent theEvent) {
        Consumable con = getConsumable("HealingPotion");
        GameState.getInstance().getHero().setConsumable(con);
        close();
    }
    @FXML
    /**
     * The cancel button
     * 
     * @param theEvent the button press
     */
    void cancel(final ActionEvent theEvent) {
        close();
    }
    /** Closes the popup */
    private void close() {
        App.closePopUp();
    }
    /** Gets the consumable item with item name */
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

