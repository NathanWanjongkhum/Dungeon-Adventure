package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import team5.game.App;
import team5.game.controller.Battle;
import team5.game.model.DungeonCharacter;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.model.Monster;
import team5.game.model.Ogre;

public class BattleController implements Initializable {
    @FXML
    private AnchorPane myBackground;

    @FXML
    private Label myHPLevel;

    @FXML
    private ProgressBar myHeroBar;

    @FXML
    private ImageView myHeroImage;

    @FXML
    private TextArea myLog;

    @FXML
    private ProgressBar myMonsterBar;

    @FXML
    private Label myMonsterHPLevel;

    @FXML
    private ImageView myMonsterImage;

    @FXML
    private Label myMonsterName;

    @FXML
    private Label myName;
    @FXML
    private Button myNext;
    @FXML
    private Button myAttack;
    @FXML
    private Button mySpecial;
    @FXML
    private Button myItem;
    @FXML
    private Button myRetreat;
    @FXML
    private Label myHeroStatus;
    @FXML
    private Label myMonsterStatus;

    private Battle myBattle;
    private Hero myHero;
    private Monster myMonster;
    private double myHeroHP;
    private double myMonsterHP;
    private Tooltip myHeroTooltip;
    private Tooltip myMonsterTooltip;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        // Dungeon class would get hero and monster so hp would carry over
        myHero = GameState.getInstance().getHero();
        myMonster = new Ogre("Og");

        myBackground.setStyle("-fx-background-color: black");
        myBattle = new Battle(myHero, myMonster);

        myHeroTooltip = new Tooltip(myHero.getStats());
        myMonsterTooltip = new Tooltip(myMonster.getStats());

        initDungeonCharacter();
        initImages();
        setHP();
        myLog.appendText("Battles had started with " + myMonster.getName() + "\n");
    }

    private void initDungeonCharacter() {
        myName.setText(myHero.getName());
        myHeroHP = 100;
        myHeroBar.setStyle("-fx-accent: green");

        myMonsterName.setText(myMonster.getName());
        myMonsterHP = 100;
        myMonsterBar.setStyle("-fx-accent: green");
    }

    private void initImages() {
        initImages(myHeroImage, myHero, myHeroTooltip);
        initImages(myMonsterImage, myMonster, myMonsterTooltip);
        myMonsterImage.setScaleX(-1);
    }

    private void initImages(final ImageView theView, final DungeonCharacter theCharacter, Tooltip theTooltip) {
        theView.setImage(theCharacter.getImage());
        Tooltip.install(theView, theTooltip);
        theTooltip.setShowDelay(Duration.seconds(0));
    }

    private void setHP() {
        setHP(myHero, myHPLevel, myHeroHP);
        setHPBar(myHeroBar, myHeroHP);

        setHP(myMonster, myMonsterHPLevel, myMonsterHP);
        setHPBar(myMonsterBar, myMonsterHP);
    }

    private void setHP(final DungeonCharacter theCharacter, final Label theLabel, double theHP) {
        final String character = "HP " + theCharacter.getHealth() + "/" + theCharacter.getMaxHealth();
        theLabel.setText(character);
        theHP = (double) theCharacter.getHealth() / theCharacter.getMaxHealth();
    }

    private void setHPBar(final ProgressBar theBar, final double theHP) {
        theBar.setProgress(theHP);
        checkHPBar(theBar, theHP);
    }

    private void checkHPBar(final ProgressBar theBar, final double theHP) {
        if (theHP < 0.25) {
            theBar.setStyle("-fx-accent: red;");
        } else if (theHP < 0.5) {
            theBar.setStyle("-fx-accent: yellow;");
        } else {
            theBar.setStyle("-fx-accent: green");
        }
    }

    @FXML
    void attack(ActionEvent event) {
        attackAction();
    }

    private void attackAction() {
        myBattle.battle("attack");
        setHP();
        displayText();
        if (myBattle.isOver()) {
            over();
        }
    }

    @FXML
    void useSpecialAttack(ActionEvent event) {
        specialAction();
    }

    private void specialAction() {
        if (myHero.getSpecialAttack().getTurns() > 0) {
            setBattleButtons(false);
            mySpecial.setDisable(false);
        } else {
            setBattleButtons(true);
        }
        myBattle.battle("special");
        setHP();
        displayText();
        if (myBattle.isOver()) {
            over();
        }
    }

    @FXML
    void item(ActionEvent event) {

    }

    @FXML
    void myHome(ActionEvent event) throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    void retreat(ActionEvent event) throws IOException {
        App.setRoot("HeroSelection");
    }

    @FXML
    void endBattle(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    private void displayText() {
        myLog.appendText(myBattle.actionPerformed());
        displayEffect(myHero, myHeroTooltip, myHeroStatus);
        displayEffect(myMonster, myMonsterTooltip, myMonsterStatus);
    }

    private void displayEffect(final DungeonCharacter theCharacter, final Tooltip theTooltip, final Label theLabel) {
        if (theCharacter.getStatusEffects().isRegen() || theCharacter.getStatusEffects().isVulnerable()) {
            theTooltip.setText(theCharacter.getStats() + theCharacter.getStatusEffects().toString());
            theLabel.setText(theCharacter.getStatusEffects().toString());
            theLabel.setVisible(true);
        } else {
            theTooltip.setText(theCharacter.getStats());
            theLabel.setVisible(false);
        }
    }

    private void over() {
        setBattleButtons(false);
        setNextButton(true);
    }

    private void setBattleButtons(boolean theBoolean) {
        myAttack.setDisable(!theBoolean);
        mySpecial.setDisable(!theBoolean);
        myItem.setDisable(!theBoolean);
        myRetreat.setDisable(!theBoolean);

    }

    private void setNextButton(boolean theBoolean) {
        myNext.setVisible(theBoolean);
        myNext.setDisable(!theBoolean);
    }

}
