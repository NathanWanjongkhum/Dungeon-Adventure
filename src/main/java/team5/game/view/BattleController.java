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
import team5.game.controller.Choices;
import team5.game.model.Hero;
import team5.game.model.Monster;
import team5.game.model.Ogre;

public class BattleController implements Initializable{
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
    // private int myHeroMaxHP;
    // private int myMonsterMaxHP;
    private Hero myHero;
    private Monster myMonster;
    private double myHeroHP;
    private double myMonsterHP;
    private Tooltip myHeroTooltip;
    private Tooltip myMonsterTooltip;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        //Dungeon class would get hero and monster so hp would carry over
        myHero = Choices.getChoices().getHero();
        myMonster = new Ogre("Og");

        myBackground.setStyle("-fx-background-color: black");
        myBattle = new Battle(myHero, myMonster);
        initDungeonCharacter();
        initImages();
        setHP();
        myLog.appendText("Battles had started with " + myMonster.getName() + "\n");
    }
    
    private void initDungeonCharacter() {
        // myHeroMaxHP = myHero.getHealth();
        // myMonsterMaxHP = myMonster.getHealth();
        myName.setText(myHero.getName());
        myMonsterName.setText(myMonster.getName());
        myHeroHP = 100;
        myMonsterHP = 100;
        myHeroBar.setStyle("-fx-accent: green");
        myMonsterBar.setStyle("-fx-accent: green");
    }
    private void initImages() {
        myHeroImage.setImage(myHero.getImage());
        myMonsterImage.setImage(myMonster.getImage());
        myMonsterImage.setScaleX(-1);
        myHeroTooltip = new Tooltip(myHero.getStats());
        Tooltip.install(myHeroImage, myHeroTooltip);
        myMonsterTooltip = new Tooltip(myMonster.getStats());
        Tooltip.install(myMonsterImage, myMonsterTooltip);
        myHeroTooltip.setShowDelay(Duration.seconds(0));
        myMonsterTooltip.setShowDelay(Duration.seconds(0));
    }
    private void setHP() {
        final String hero = "HP " + myHero.getHealth() + "/" + myHero.getMaxHealth();
        myHPLevel.setText(hero);
        myHeroHP = (double) myHero.getHealth() / myHero.getMaxHealth();
        myHeroBar.setProgress(myHeroHP);
        if (myHeroHP <= 0.25) {
            myHeroBar.setStyle("-fx-accent: red;");
        } else if (myHeroHP <= 0.5) {
            myHeroBar.setStyle("-fx-accent: yellow;");
        }

        final String monster = "HP " + myMonster.getHealth() + "/" + myMonster.getMaxHealth();
        myMonsterHPLevel.setText(monster);
        myMonsterHP = (double) myMonster.getHealth() / myMonster.getMaxHealth();
        myMonsterBar.setProgress(myMonsterHP);
        if (myMonsterHP < 0.25) {
            myMonsterBar.setStyle("-fx-accent: red;");
        } else if (myMonsterHP < 0.5) {
            myMonsterBar.setStyle("-fx-accent: yellow;");
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
    private void displayText() {
        myLog.appendText(myBattle.actionPerformed());
        if (myHero.getStatusEffects().isRegen() || myHero.getStatusEffects().isVulnerable()) {
            myHeroTooltip.setText(myHero.getStats() + myHero.getStatusEffects().toString());
            myHeroStatus.setText(myHero.getStatusEffects().toString());
            myHeroStatus.setVisible(true);
        } else {
            myHeroTooltip.setText(myHero.getStats());
            myHeroStatus.setVisible(false);
        }
        if (myMonster.getStatusEffects().isRegen() || myMonster.getStatusEffects().isVulnerable()) {
            myMonsterTooltip.setText(myMonster.getStats() + myMonster.getStatusEffects().toString());
            myMonsterStatus.setText(myMonster.getStatusEffects().toString());
            myMonsterStatus.setVisible(true);
        } else {
            myMonsterTooltip.setText(myMonster.getStats());
            myMonsterStatus.setVisible(false);
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
    void myHome(ActionEvent event) throws IOException{
        App.setRoot("StartScreen");
    }

    @FXML
    void retreat(ActionEvent event) throws IOException{
        App.setRoot("HeroSelection");
    }
    @FXML
    void endBattle(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }
}

