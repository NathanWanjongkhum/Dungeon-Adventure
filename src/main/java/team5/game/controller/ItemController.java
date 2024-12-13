package team5.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import team5.game.model.AttackPotion;
import team5.game.model.Bomb;
import team5.game.model.Consumable;
import team5.game.model.GameState;
import team5.game.model.HealingPotion;
import team5.game.model.Hero;
import team5.game.model.Inventory;
import team5.game.model.Item;
import team5.game.view.App;
/**
 * The GUI Controller for HeroPickerController
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class ItemController {

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
    private boolean myBattling;

    @FXML
    private void initialize() {
        myInventory = GameState.getInstance().getHero().getInventory();
        disable();
        setDescriptions();
        int index = 0;
        if (!myInventory.isEmpty()) {
            for (Item c: myInventory.getItems()) {
                if (c != null && c.isConsumable()) {
                    setText(c, index);                 
                }
                index++;
            }
        }
        if (GameState.getInstance().getHero().getStatusEffects().isDamageIncrease()) {
            myAttackButton.setDisable(true);
        }
        myBattling = GameState.getInstance().isBattling();
        if (!myBattling) {
            myAttackButton.setDisable(true);
            myBombButton.setDisable(true);
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
                break;
            case "HealingPotion":
                myHealingCount.setText("x" + item.getCount());
                setButtons(item, myHealingButton);
                final Hero hero = GameState.getInstance().getHero();
                if (hero.getHealth() == hero.getMaxHealth()) {
                    myHealingButton.setDisable(true);
                }
                break;
            case "Bomb":
                myBombCount.setText("x" + item.getCount());
                setButtons(item, myBombButton);
                break;
            default:
                break;
        }
    }
    /** Enables the button if there is a more than 0 consumable */
    private void setButtons(Consumable theConsumable, Button theButton) {
        if (theConsumable.getCount() < 1) {
            theButton.setDisable(true);
        } else {
            theButton.setDisable(false);
        }
    }
    private void setDescriptions() {
        AttackPotion attack = new AttackPotion();
        HealingPotion heal = new HealingPotion();
        Bomb bomb = new Bomb();
        myAttackDescript.setText(attack.getDescription());
        myHealingDescript.setText(heal.getDescription());
        myBombDescript.setText(bomb.getDescription());
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

