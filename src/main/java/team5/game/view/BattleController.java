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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import team5.game.App;
import team5.game.controller.BattleSample;
import team5.game.model.Archer;
import team5.game.model.Dungeon;
import team5.game.model.DungeonCharacter;
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
    private Button myItem;
    @FXML
    private Button myRetreat;

    private BattleSample myBattle;
    private int myHeroMaxHP;
    private int myMonsterMaxHP;
    private Hero myHero;
    private Monster myMonster;
    private double myHeroHP;
    private double myMonsterHP;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        //Dungeon class would get hero and monster so hp would carry over
        myHero = new Archer("Arch");
        myMonster = new Ogre("Og");

        myBattle = new BattleSample(myHero, myMonster);
        myHeroMaxHP = myHero.getHealth();
        myMonsterMaxHP = myMonster.getHealth();
        myName.setText(myHero.getName());
        myMonsterName.setText(myMonster.getName());
        myHeroHP = 100;
        myMonsterHP = 100;
        myHeroBar.setStyle("-fx-accent: green");
        myMonsterBar.setStyle("-fx-accent: green");
        setHP();
        myLog.appendText("Battles had started with " + myMonster.getName() + "\n");
    }
    private void setHP() {
        final String hero = myHero.getHealth() + "/" + myHeroMaxHP;
        myHPLevel.setText(hero);
        myHeroHP = (double) myHero.getHealth() / myHeroMaxHP;
        myHeroBar.setProgress(myHeroHP);
        if (myHeroHP < 0.25) {
            myHeroBar.setStyle("-fx-accent: red;");
        } else if (myHeroHP < 0.5) {
            myHeroBar.setStyle("-fx-accent: yellow;");
        }
        final String monster = myMonster.getHealth() + "/" + myMonsterMaxHP;
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
        myItem.setDisable(true);
        myRetreat.setDisable(true);

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

