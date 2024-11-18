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
import javafx.scene.input.MouseEvent;
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

    private Battle myBattle;
    private int myHeroMaxHP;
    private int myMonsterMaxHP;
    private Hero myHero;
    private Monster myMonster;
    private double myHeroHP;
    private double myMonsterHP;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        //Dungeon class would get hero and monster so hp would carry over
        myHero = Choices.getChoices().getHero();
        myMonster = new Ogre("Og");

        myBattle = new Battle(myHero, myMonster);
        initDungeonCharacter();
        initImages();
        setHP();
        myLog.appendText("Battles had started with " + myMonster.getName() + "\n");
    }
    
    private void initDungeonCharacter() {
        myHeroMaxHP = myHero.getHealth();
        myMonsterMaxHP = myMonster.getHealth();
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
        final Tooltip hero = new Tooltip(myHero.getStats());
        Tooltip.install(myHeroImage, hero);
        final Tooltip monster = new Tooltip(myMonster.getStats());
        Tooltip.install(myMonsterImage, monster);
        hero.setShowDelay(Duration.seconds(0));
        monster.setShowDelay(Duration.seconds(0));
    }
    private void setHP() {
        final String hero = "HP " + myHero.getHealth() + "/" + myHeroMaxHP;
        myHPLevel.setText(hero);
        myHeroHP = (double) myHero.getHealth() / myHeroMaxHP;
        myHeroBar.setProgress(myHeroHP);
        if (myHeroHP <= 0.25) {
            myHeroBar.setStyle("-fx-accent: red;");
        } else if (myHeroHP <= 0.5) {
            myHeroBar.setStyle("-fx-accent: yellow;");
        }
        
        final String monster = "HP " + myMonster.getHealth() + "/" + myMonsterMaxHP;
        myMonsterHPLevel.setText(monster);
        myMonsterHP = (double) myMonster.getHealth() / myMonsterMaxHP;
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
        myBattle.battle();
        setHP();
        displayText();
        if (myBattle.isOver()) {
            endBattleButtons();
        }
    }
    private void displayText() {
        myLog.appendText(myBattle.actionPerformed());
    }
    private void endBattleButtons() {
        myNext.setVisible(true);
        myNext.setDisable(false);
        myAttack.setDisable(true);
        mySpecial.setDisable(true);
        myItem.setDisable(true);
        myRetreat.setDisable(true);

    }
    @FXML
    void useSpecialAttack(ActionEvent event) {
        specialAction();
    }
    private void specialAction() {
        do {
            myBattle.battleSpecial();
            setHP();
            displayText();
            if (myBattle.isOver()) {
                endBattleButtons();
            }
        } while(myHero.getSpecialAttack().getTurns() > 0);
    }
    @FXML
    void item(ActionEvent event) {

    }

    @FXML
    void myHome(ActionEvent event) throws IOException{
        App.setRoot("StartScreen");
    }

    @FXML
    void retreat(ActionEvent event) {

    }
    @FXML
    void endBattle(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }
    @FXML
    void displayMonster(MouseEvent event) {
        //When clicking on image, display monster details
    }
}

