package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import team5.game.App;
import team5.game.controller.Battle;
import team5.game.model.DungeonCharacter;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.model.Monster;
/**
 * The GUI Controller for BattleScene
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class BattleController {
    @FXML
    /** The scene for background */
    private AnchorPane myBackground;

    @FXML
    /** The container for controls and text area */
    private HBox myControls;

    @FXML
    /** The Hero HP Label */
    private Label myHPLevel;

    @FXML
    /**The Hero HP Bar */
    private ProgressBar myHeroBar;

    @FXML
    /** The Hero Image */
    private ImageView myHeroImage;

    @FXML
    /** The text area for combat log */
    private TextArea myLog;

    @FXML
    /** The Monster hp bar */
    private ProgressBar myMonsterBar;

    @FXML
    /** The Monster HP label */
    private Label myMonsterHPLevel;

    @FXML
    /** The Monster Image */
    private ImageView myMonsterImage;

    @FXML
    /** The Monster name */
    private Label myMonsterName;

    @FXML
    /** The Hero name */
    private Label myName;

    @FXML
    /**The button to go next */
    private Button myNext;

    @FXML
    /** The Button for attack action */
    private Button myAttack;

    @FXML
    /** The button for special attack action */
    private Button mySpecial;

    @FXML
    /** The item for item action */
    private Button myItem;

    @FXML
    /** The button to retreat from battle */
    private Button myRetreat;

    /** The battle controller for battles */
    private Battle myBattle;
    /** The hero selected */
    private Hero myHero;
    /** The monster enemy */
    private Monster myMonster;
    /** A tooltip for hero description */
    private Tooltip myHeroTooltip;
    /** A tooltip for monster description */
    private Tooltip myMonsterTooltip;
    /** An image for Hero effects */
    private ImageView myHeroEffects;
    /** An image for Monster effects*/
    private ImageView myMonsterEffects;
    /** Tooltip for status effects on hero */
    private Tooltip myHeroEffectTooltip;
    /** Tooltip for status effects on monster */
    private Tooltip myMonsterEffectTooltip;

    @FXML
    /** Initialize the scene */
    private void initialize() {
        // Dungeon class would get hero and monster so hp would carry over
        myHero = GameState.getInstance().getHero();
        myMonster = GameState.getInstance().getDungeon().getRoom(myHero.getX(), myHero.getY()).getMonster();

        //CustomBackground code
        BackgroundImage back = App.getBackgroundImage("battle background");
        myBackground.setBackground(new Background(back));
        myControls.setStyle("-fx-background-color: black; -fx-effect: innershadow(gaussian, #66524d, 7, 0.9, 0, 0)");

        myBattle = new Battle(myHero, myMonster);
        myHeroTooltip = new Tooltip(myHero.getStats());
        myMonsterTooltip = new Tooltip(myMonster.getStats());
        initDungeonCharacter();
        initImages();
        initStatusIndication();
        setHP();
        myLog.appendText("Battles had started with " + myMonster.getName() + "\n");
    }
    /**
     * Initialize the dungeon character's names.
     */
    private void initDungeonCharacter() {
        myName.setText(myHero.getName());
        myHeroBar.setStyle("-fx-accent: green");

        myMonsterName.setText(myMonster.getName());
        myMonsterBar.setStyle("-fx-accent: green");
    }
    /**
     * Initialize the hero and monster's image properties.
     */
    private void initImages() {
        initImages(myHeroImage, myHero, myHeroTooltip);
        initImages(myMonsterImage, myMonster, myMonsterTooltip);
        myMonsterImage.setScaleX(-1);
    }
    /**
     * Initialize the dungeon character's images and adds tooltip to image.
     * 
     * @param theView an imageview of the character.
     * @param theCharacter the dungeon character being added.
     * @param theTooltip the tooltip to be added to the image.
     */
    private void initImages(final ImageView theView, final DungeonCharacter theCharacter, Tooltip theTooltip) {
        theView.setImage(theCharacter.getImage());
        theTooltip.setStyle("-fx-font-size: 15; -fx-text-fill: white;" +
                            "-fx-font-family: 'High Tower Text'");
        Tooltip.install(theView, theTooltip);

    }
    /**
     * Initialize the status indication.
     */
    private void initStatusIndication() {
        myHeroEffects = new ImageView();
        myMonsterEffects = new ImageView();
        myHeroEffectTooltip = new Tooltip();
        myMonsterEffectTooltip = new Tooltip();
        setTooltipTheme(myHeroEffectTooltip);
        setTooltipTheme(myMonsterEffectTooltip);
    }
    /**
     * Sets the tooltip theme for the game.
     * 
     * @param theTooltip the tooltip for which style is changed.
     */
    private void setTooltipTheme(final Tooltip theTooltip) {
        theTooltip.setStyle("-fx-font-size: 15; -fx-text-fill: white;" +
                            "-fx-font-family: 'High Tower Text'");
        theTooltip.setShowDelay(Duration.seconds(0.5));
    }
    /**
     * Sets the hp of hero and monster.
     */
    private void setHP() {
        setCharHP(myHero, myHPLevel, myHeroBar);
        setCharHP(myMonster, myMonsterHPLevel, myMonsterBar);
    }
    /**
     * Sets the hp of the dungeon character.
     * 
     * @param theCharacter the dungeon character to be updated.
     * @param theLabel the hp label to be changed.
     * @param theBar the hp bar for the dungeon character.
     */
    private void setCharHP(final DungeonCharacter theCharacter, final Label theLabel, final ProgressBar theBar) {
        final String character = "HP " + theCharacter.getHealth() + "/" + theCharacter.getMaxHealth();
        theLabel.setText(character);
        final double hp = (double) theCharacter.getHealth() / theCharacter.getMaxHealth();
        setHPBar(theBar, hp);
    }
    /**
     * Updates the hp bar of the character.
     * 
     * @param theBar the dungeon character's item bar.
     * @param theHP the hp percentage to update the bar.
     */
    private void setHPBar(final ProgressBar theBar, final double theHP) {
        theBar.setProgress(theHP);
        if (theHP < 0.25) {
            theBar.setStyle("-fx-accent: red;");
        } else if (theHP < 0.5) {
            theBar.setStyle("-fx-accent: yellow;");
        } else {
            theBar.setStyle("-fx-accent: green");
        }
    }
    @FXML
    /**
     * The attack button action.
     * 
     * @param theEvent the button press.
     * @throws IOException when image is not found.
     */
    private void attack(final ActionEvent theEvent) throws IOException {
        attackAction();
    }
    /**
     * The attack action.
     * 
     * @throws IOException when image is not found.
     */
    private void attackAction() throws IOException {
        myBattle.battle("attack");
        battleCondition();
    }

    @FXML
    /**
     * The special attack button action.
     * 
     * @param theEvent the button press.
     * @throws IOException when image is not found.
     */
    private void useSpecialAttack(final ActionEvent theEvent) throws IOException {
        specialAction();
    }
    /**
     * The special attack action.
     * 
     * @throws IOException when image is not found.
     */
    private void specialAction() throws IOException {
        if (myHero.getSpecialAttack().getTurns() > 0) {
            setBattleButtons(false);
            mySpecial.setDisable(false);
        } else {
            setBattleButtons(true);
        }
        myBattle.battle("special");
        battleCondition();
    }
    @FXML
    /**
     * The item button action.
     * 
     * @param theEvent the button press.
     * @throws IOException when fxml file is not found.
     */
    private void item(final ActionEvent theEvent) throws IOException {
        App.createPopUpScene("ItemBag");
        if (GameState.getInstance().getHero().isConUsed()) {
            useItem();
        }
    }
    /**
     * The item action.
     * 
     * @throws IOException when image is not found.
     */
    private void useItem() throws IOException {
        myBattle.setConsumable(GameState.getInstance().getHero().useConsumable());
        myBattle.battle("item");
        battleCondition();
    }
    @FXML
    /**
     * The retreat button action
     * 
     * @param theEvent the button press
     * @throws IOException when fxml file is not found
     */
    private void retreat(final ActionEvent theEvent) throws IOException {
        myHero.moveTo(myHero.getDirection().getOpposite());
        App.setRoot("DungeonScene");
        GameState.getInstance().setBattling(false);
    }

    @FXML
        /**
     * The end battle button action
     * 
     * @param theEvent the button press
     * @throws IOException when fxml file is not found
     */
    private void endBattle(final ActionEvent theEvent) throws IOException {
        if (myBattle.isOver() && myHero.getHealth() == 0) {
            App.setRoot("EndScene");
        } else {
            GameState.getInstance().getDungeon().getRoom(myHero.getX(), myHero.getY()).removeMonster();
            App.setRoot("DungeonScene3");
        }
        GameState.getInstance().setBattling(false);
    }
    /**
     * Adds text to the end of battle and updates status when necessary
     * 
     * @throws IOException when image is not found.
     */
    private void displayText() throws IOException {
        myLog.appendText(myBattle.actionPerformed());
        displayEffect(myHero, myHeroTooltip, myHPLevel, myHeroEffectTooltip, myHeroEffects);
        displayEffect(myMonster, myMonsterTooltip, myMonsterHPLevel, myMonsterEffectTooltip, myMonsterEffects);
    }
    /**
     * The battle updates after action
     * 
     * @throws IOException when image is not found
     */
    private void battleCondition() throws IOException {
        setHP();
        displayText();
        if (myBattle.isOver()) {
            over();
        }
    }
    /**
     * Updates effects and displays if necessary.
     * 
     * @param theCharacter the dungeon character.
     * @param theTooltip the tooltip of the character.
     * @param theLabel the hp label for dungeon character.
     * @param theStatus the tooltip for status effects.
     * @param theIcon the status effect icon.
     * @throws IOException when image is not found.
     */
    private void displayEffect(final DungeonCharacter theCharacter, final Tooltip theTooltip, 
                                final Label theLabel, final Tooltip theStatus, final ImageView theIcon) throws IOException {
        if (theCharacter.getStatusEffects().hasEffect()) {
            theTooltip.setText(theCharacter.getStats() + "\n" + theCharacter.getStatusEffects().toString());
            
            theStatus.setText(theCharacter.getStatusEffects().toString());
            theLabel.setTooltip(theStatus);
            Image icon = null;
            //Adding image to label depending on status
            if (theCharacter.getStatusEffects().hasMultipleStatus()) {
                 icon = getImage("bothStatus");
            } else if (theCharacter.getStatusEffects().isVulnerable()) {
                icon = getImage("debuff");
            } else {
                icon = getImage("buff");
            }
            theIcon.setImage(icon);
            theLabel.setGraphic(theIcon);
        } else {
            theTooltip.setText(theCharacter.getStats());
            theLabel.setGraphic(null);
            theLabel.setTooltip(null);
        }
    }
    /**
     * Gets the image from resources with the correct file name.
     * 
     * @param theImageName the image name for the file.
     * @return an Image of the string name.
     * @throws IOException when inputted string name is not found.
     */
    private Image getImage(final String theImageName) throws IOException {
        final StringBuilder builder = new StringBuilder();
        builder.append("/team5/game/");
        builder.append(theImageName);
        builder.append(".png");
        final Image temp = new Image(getClass().getResource(builder.toString()).toString());
        return temp;
    }
    /**
     * Set buttons when battle is over
     */
    private void over() {
        setBattleButtons(false);
        setNextButton(true);
    }
    /**
     * Sets all buttons to desired state.
     * True sets buttons to be on and false turns them off.
     * 
     * @param theBoolean true to turn all buttons on, false otherwise.
     */
    private void setBattleButtons(final boolean theBoolean) {
        myAttack.setDisable(!theBoolean);
        mySpecial.setDisable(!theBoolean);
        myItem.setDisable(!theBoolean);
        myRetreat.setDisable(!theBoolean);

    }
    /**
     * Determines if the next button should be turned on or off.
     * 
     * @param theBoolean true to turn on, false otherwise.
     */
    private void setNextButton(final boolean theBoolean) {
        myNext.setVisible(theBoolean);
        myNext.setDisable(!theBoolean);
    }
}
